package org.vermeg.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vermeg.entities.JiraIssue;
import org.vermeg.entities.JiraPriority;
import org.vermeg.entities.JiraProjectPriority;
import org.vermeg.entities.NombreDeCommit;
import org.vermeg.entities.SvnCommit;
import org.vermeg.entities.CommitByModule;
import org.vermeg.repository.JiraIssueRepository;
import org.vermeg.repository.SonarRepository;
import org.vermeg.repository.SvnCommitRepository;
import org.vermeg.repository.SvnModuleRepository;

@Service
public class CodeChangeServiceImpl implements CodeChangeService{

	@Autowired
    private JiraIssueRepository jiraIssueRepository;
	
	@Autowired
    private SvnCommitRepository svnCommitRepository;
	
    @Autowired
    private SvnModuleRepository svnService;
	
	@Autowired
	private SonarRepository sonarService;
	
    /**
     * cette méthode retourne une list du NombreDeCommit par module
     */
	@Override
	public List<NombreDeCommit> listcommit() {
    	List<NombreDeCommit> listOfCommit = new ArrayList<NombreDeCommit>();
        List<String> listmodule = svnService.findById("1000").get().getModule();
    	List<SvnCommit> listsvncommit = IteratorUtils.toList(svnCommitRepository.findAll().iterator( ));

        for(String rt : listmodule) {
        	listOfCommit.add(numberOfCommitByModule(rt, listsvncommit));
        }
        
		return listOfCommit;
	}
	
	/**
	 * cette méthode retourne le nombre de commit pour un module donné
	 */
	@Override
	public NombreDeCommit numberOfCommitByModule(String module, List<SvnCommit> listOfSvnCommit) {
		int nbCommit= 0;
        int jira = 0;
         
     	for (SvnCommit si :  listOfSvnCommit ) {
            if(jiraIssueRepository.findById((si.getMessage().equals("")? "no": si.getMessage())).isPresent()) {
            	if(numberOfCommit(module, si.getPaths()) != 0) {
                	nbCommit += numberOfCommit(module, si.getPaths());
         			jira++;
            	}
         	}
     	}
     	
     	System.out.println("dddrre"+ sonarService.countByModule("module"));
     	
		return new NombreDeCommit(module, jira, nbCommit, sonarService.countByModule(module));
	}
	
	/**
	 * cette méthode retourne le nombre de commit pour un module elle prend en parametre :
	 * module : le nom du module
	 * list : la liste des path d'une revision
	 */
	@Override
	public long numberOfCommit(String module, List<String> list) {
		long count = 0;
		for(String path: list) {
			if(path.startsWith(module)) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * 
	 */
	@Override
	public List<CommitByModule> allListCommit(String startDate, String endDate) throws ParseException {
	   	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
    	Date startDate1 = null ;
	    Date endDate1 = null ;

		List<String> listmodule = svnService.findById("1000").get().getModule();
    	List<CommitByModule> listOfCommit = new ArrayList<CommitByModule>();
    	List<SvnCommit> listOfSvnCommit = IteratorUtils.toList(svnCommitRepository.findAll().iterator( ));
    	List<JiraIssue> listOfJiraIssue = IteratorUtils.toList(jiraIssueRepository.findAll().iterator());
	    
        for(String rt : listmodule) {

	    	if(startDate.equals("") && endDate.equals("")) {
	    		listOfCommit.add(nbrCommitByModule(rt, listOfSvnCommit, listOfJiraIssue));
	    	}
	    	else {
	    		startDate1 = sdf.parse(startDate);
	    		endDate1 = sdf.parse(endDate);
	    		listOfCommit.add(nbrCommitByModule(rt, listOfSvnCommit, listOfJiraIssue, startDate1, endDate1));
	    	}
        }
		
		return listOfCommit;
	}

	/**
	 * 
	 */
	@Override
	public CommitByModule nbrCommitByModule(String module, List<SvnCommit> listOfSvnCommit,
			List<JiraIssue> listOfJiraIssue) {

		long nbCommit= 0;
        int bug = 0;
        int task = 0;
        int story = 0;
        int epic = 0;
        
    	for (SvnCommit si : listOfSvnCommit) {

        	nbCommit += numberOfCommit(module, si.getPaths());
   			
        	if(numberOfCommit(module, si.getPaths())!=0) {
         	   for(JiraIssue jr: listOfJiraIssue) {
            	   if(si.getMessage().contentEquals(jr.getKey())) {

            		   if(jr.getIssueType().equals("Bug")) {
            			   bug++;
            		   }else if(jr.getIssueType().equals("Task")) {
            			   task++;
            		   }else if(jr.getIssueType().equals("Story")) {
            			   story++;
            		   }else if(jr.getIssueType().equals("Epic")) {
            			   epic++;
            		   }
            		   	   
            	   }   
        	   }
			}
    	}

		return new CommitByModule(module, nbCommit, bug, task, story, epic, new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()) );
	}

	/**
	 * 
	 */
	@Override
	public CommitByModule nbrCommitByModule(String module, List<SvnCommit> listOfSvnCommit, List<JiraIssue> listOfJiraIssue,
			Date startDate, Date endDate) throws ParseException {
	   	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

		long nbCommit= 0;
        int bug = 0;
        int task = 0;
        int story = 0;
        int epic = 0;
        
    	for (SvnCommit si : listOfSvnCommit) {
        	Date theDate = sdf.parse(si.getDate().replaceAll("-", "."));
        	if(theDate.compareTo(startDate) >= 0 && theDate.compareTo(endDate) <= 0) {
            	nbCommit += numberOfCommit(module, si.getPaths());
    
            	if(numberOfCommit(module, si.getPaths())!=0) {
            		
             	   for(JiraIssue jr: listOfJiraIssue) {
                	   if(si.getMessage().contentEquals(jr.getKey())) {

                		   if(jr.getIssueType().equals("Bug")) {
                			   bug++;
                		   }else if(jr.getIssueType().equals("Task")) {
                			   task++;
                		   }else if(jr.getIssueType().equals("Story")) {
                			   story++;
                		   }else if(jr.getIssueType().equals("Epic")) {
                			   epic++;
                		   }
                		   	   
                	   }   
            	   }
             	   
    			}
        	}

    	}
		
		
		return new CommitByModule(module, nbCommit, bug, task, story, epic);
	}

	/**
	 *
	 */
	@Override
	public List<JiraProjectPriority> listOfJiraProjectPriority(String jiraType) {

    	List<JiraProjectPriority> listOfJiraProjectPriority = new ArrayList<JiraProjectPriority>();

        for (JiraPriority pr : JiraPriority.values()) {
        	JiraProjectPriority j = new JiraProjectPriority(pr, jiraIssueRepository.countByIssueTypeAndPriority(jiraType, pr.name()));
        	listOfJiraProjectPriority.add(j);
        }
		
		return listOfJiraProjectPriority;
	}
}
