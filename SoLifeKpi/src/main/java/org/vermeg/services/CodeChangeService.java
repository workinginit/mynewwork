package org.vermeg.services;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.vermeg.entities.JiraIssue;
import org.vermeg.entities.NombreDeCommit;
import org.vermeg.entities.SvnCommit;
import org.vermeg.entities.nbrCommit;
import org.vermeg.entities.test;

public interface CodeChangeService {
	
    List<NombreDeCommit> listcommit(); 
    
    List<nbrCommit> allListCommit(String starDate, String endDate) throws ParseException; 
    
    long numberOfCommit(String module, List<String> list);
    
    NombreDeCommit numberOfCommitByModule(String module, List<SvnCommit> listOfSvnCommit );
    
    nbrCommit nbrCommitByModule(String module, List<SvnCommit> listOfSvnCommit, List<JiraIssue> listOfJiraIssue);

    nbrCommit nbrCommitByModule(String module, List<SvnCommit> listOfSvnCommit, List<JiraIssue> listOfJiraIssue, Date startDate, Date endDate) throws ParseException;

    List<test> listoftest(); 

}
