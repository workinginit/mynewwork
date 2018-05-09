package org.vermeg.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vermeg.entities.JiraIssue;
import org.vermeg.entities.NombreDeCommit;
import org.vermeg.entities.SvnCommit;
import org.vermeg.entities.nbrCommit;
import org.vermeg.repository.JiraIssueRepository;
import org.vermeg.repository.SvnCommitRepository;

@Service
public class CodeChangeServiceImpl implements CodeChangeService{

	@Autowired
    private JiraIssueRepository jiraIssueRepository;
	
	@Autowired
    private SvnCommitRepository svnCommitRepository;
	
    @Autowired
    private SvnModuleService svnService;
	
	@Override
	public List<NombreDeCommit> listcommit() {
        List<String> listmodule = svnService.findById("1000").get().getModule();
    	List<NombreDeCommit> listOfCommit = new ArrayList<NombreDeCommit>();
    	Iterator<SvnCommit> lista = svnCommitRepository.findAll().iterator( );
    	
        for(String rt : listmodule) {
            int nbCommit= 0;
            int jira = 0;
            
        	for ( Iterator<SvnCommit> entries2 =  svnCommitRepository.findAll().iterator( ); entries2.hasNext( ); ) {
            	SvnCommit si =  entries2.next( );
                int test = 0;
                System.out.println("dd"+si.getRevision());
                if(jiraIssueRepository.findById((si.getMessage().equals("")? "no": si.getMessage())).isPresent()) {

            		for(String path: si.getPaths()) {
            			if(path.startsWith(rt)) {
            				nbCommit++;
            				test = 1;
            			}
            		}
            	}
        		
        		if(test!=0) {
        			jira++;
        		}
        	}

        	NombreDeCommit n = new NombreDeCommit(rt, jira, nbCommit);
        	listOfCommit.add(n);
        }
		return listOfCommit;
        
	}

	
	@Override
	public List<nbrCommit> allListCommit(String startDate, String endDate) throws ParseException {
		List<String> listmodule = svnService.findById("1000").get().getModule();
    	List<nbrCommit> listOfCommit = new ArrayList<nbrCommit>();
    	Iterator<SvnCommit> listOfSvnCommit = svnCommitRepository.findAll().iterator();
    	Iterator<JiraIssue> listOfJiraIssue = jiraIssueRepository.findAll().iterator();

    	List<SvnCommit> copy = new ArrayList<SvnCommit>();
        while (listOfSvnCommit.hasNext()) {
            copy.add(listOfSvnCommit.next());
        }
        
    	List<JiraIssue> copyOfJira = new ArrayList<JiraIssue>();
        while (listOfJiraIssue.hasNext()) {
        	copyOfJira.add(listOfJiraIssue.next());
        }
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

    	Date startDate1 = null ;
	    Date endDate1 = null ;
	    
	    int test = 0;
	    
    	if(startDate.equals("") && endDate.equals("")) {
    		test = 1;
    	}
    	else {
    		startDate1 = sdf.parse(startDate);
    		endDate1 = sdf.parse(endDate);
    	}
		
	    	    
        for(String rt : listmodule) {
            long nbCommit= 0;
            int bug = 0;
            int task = 0;
            int story = 0;
            int epic = 0;
        	for (SvnCommit si : copy) {

        		Date theDate = sdf.parse(si.getDate().replaceAll("-", "."));

               if(test == 1){
            	   nbCommit += numberOfCommit(rt, si.getPaths());
       			if(numberOfCommit(rt, si.getPaths())!=0) {
             	   for(JiraIssue jr: copyOfJira) {
                	   if(si.getMessage().equals(jr.getKey())) {

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
               else if(theDate.compareTo(startDate1) >= 0 && theDate.compareTo(endDate1) <= 0) {
            	   nbCommit += numberOfCommit(rt, si.getPaths());
            	}
     
        	}

        	nbrCommit n = new nbrCommit(rt, nbCommit, bug, task, story, epic);
        	listOfCommit.add(n);
        }
		return listOfCommit;
	}


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
	

	
}
