package org.vermeg.services;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.vermeg.entities.JiraIssue;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.util.concurrent.Promise;

@Service
public class JiraServiceImpl implements JiraService {

	@Value("${jira.url:http://localhost:7223}")
    private String url;
	
    @Value("${jira.userName}")
    private String userName;
    
    @Value("${jira.password}")
    private String password;
    
    private ArrayList<JiraIssue> listOfIssues;
	private int j;
	private String name = "";
	
	@Override
	public List<JiraIssue> allofjira(String project) {
		
		listOfIssues = new ArrayList<>();
		j = 0;
		name = project;
		
		JiraRestClient jrc = ConnexionJiraService.getInstance(url, userName, decrypt(password));
		getallIssues(jrc, 0);

		return listOfIssues;
	}

	@Override
	public List<JiraIssue> getallIssues(JiraRestClient jrc, int i) {
 		Promise<SearchResult> sr = jrc.getSearchClient().searchJql("project = "+name+" ORDER BY KEY ASC", 100, i, null); 

 	 	for (Issue issue : sr.claim().getIssues()) {
 	 	 		JiraIssue jira = new JiraIssue( issue.getKey(), issue.getProject().getName(), issue.getIssueType().getName(),issue.getStatus().getName(), issue.getPriority().getName(), 
 	 	 					(issue.getResolution() == null ? "NoResolution" : issue.getResolution().getDescription()), 
 	 	 					(issue.getAssignee() == null ? "NotAssigned" : issue.getAssignee().getDisplayName()), 
 	 	 					(issue.getReporter() == null ? "NoReporter" : issue.getReporter().getDisplayName()), 
 	 	 					DateTimeFormat.forPattern("yyyy-MM-dd").print(issue.getCreationDate()), 
 	 	 					DateTimeFormat.forPattern("yyyy-MM-dd").print(issue.getUpdateDate()));
 	 	 		listOfIssues.add(jira); 
 	 	 		j++; 	

 	 	 		if((j % 100) == 0) {
 	 	 			getallIssues(jrc, j);
 	 	 		}
 	      }
 	 	
		return listOfIssues;	
	}

	public String decrypt(String password) {
		String aCrypter="";
        for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^48;  
            aCrypter=aCrypter+(char)c; 
        }
        return aCrypter;
	}


}
