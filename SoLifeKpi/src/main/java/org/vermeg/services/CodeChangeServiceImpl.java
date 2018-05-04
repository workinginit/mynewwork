package org.vermeg.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

        for(String rt : listmodule) {
            int nbCommit= 0;
            int jira = 0;
            
        	for ( Iterator<SvnCommit> entries2 =  svnCommitRepository.findAll().iterator( ); entries2.hasNext( ); ) {
            	SvnCommit si =  entries2.next( );
                int test = 0;

                if(jiraIssueRepository.findById((si.getMessage().equals("")? "no": si.getMessage())).isPresent()) {
    				System.out.println(si.getMessage());
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
        	System.out.println("name : "+rt+" jira : "+jira+" commit : "+nbCommit);	
        	NombreDeCommit n = new NombreDeCommit(rt, jira, nbCommit);
        	listOfCommit.add(n);
        }
		return listOfCommit;
        
	}

	@Override
	public List<nbrCommit> allListCommit() {
		List<String> listmodule = svnService.findById("1000").get().getModule();
    	List<nbrCommit> listOfCommit = new ArrayList<nbrCommit>();
    	
    	String startdate = "2018-02-15";
    	String enddate = "208-05-18";
    	//spliting the date
    	String[] startc = startdate.split("-");
    	String[] endc = enddate.split("-");
    	
    	//formate date to yyyy-MM-dd
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    	//instance of calendat from start and end
    	Calendar start = new GregorianCalendar(ConvertToInt(startc[0]), ConvertToInt(startc[1])-01, ConvertToInt(startc[2]));
    	Calendar end = new GregorianCalendar(ConvertToInt(endc[0]), ConvertToInt(endc[1])-01, ConvertToInt(endc[2]));
 
    	boolean test = start.getTime().compareTo(end.getTime())<=0;
    	System.out.println(sdf.format(start.getTime())+test);
        for(String rt : listmodule) {
            long nbCommit= 0;
            
        	for ( Iterator<SvnCommit> entries2 =  svnCommitRepository.findAll().iterator( ); entries2.hasNext( ); ) {
            	SvnCommit si =  entries2.next( );
            		for(String path: si.getPaths()) {
            			if(path.startsWith(rt)) {
            				nbCommit++;
            			}
            		}
            	
        	}

        	nbrCommit n = new nbrCommit(rt,nbCommit);
        	listOfCommit.add(n);
        }
		return listOfCommit;
	}
	
	
	//converting string to int
	public int ConvertToInt(String chainetoconv){
		return Integer.parseInt(chainetoconv);
	}

	
	
}
