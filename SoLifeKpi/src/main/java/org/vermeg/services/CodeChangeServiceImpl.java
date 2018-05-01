package org.vermeg.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vermeg.entities.Module;
import org.vermeg.entities.NombreDeCommit;
import org.vermeg.entities.SvnCommit;
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

	
	
}
