package org.vermeg.services;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.vermeg.entities.JiraIssue;
import org.vermeg.entities.JiraProjectPriority;
import org.vermeg.entities.NombreDeCommit;
import org.vermeg.entities.SvnCommit;
import org.vermeg.entities.CommitByModule;

public interface CodeChangeService {
	
    List<NombreDeCommit> listcommit(); 
    
    List<CommitByModule> allListCommit(String starDate, String endDate) throws ParseException; 
    
    long numberOfCommit(String module, List<String> list);
    
    NombreDeCommit numberOfCommitByModule(String module, List<SvnCommit> listOfSvnCommit );
    
    CommitByModule nbrCommitByModule(String module, List<SvnCommit> listOfSvnCommit, List<JiraIssue> listOfJiraIssue);

    CommitByModule nbrCommitByModule(String module, List<SvnCommit> listOfSvnCommit, List<JiraIssue> listOfJiraIssue, Date startDate, Date endDate) throws ParseException;

    
    List<JiraProjectPriority> listOfJiraProjectPriority(String jiraType);

}
