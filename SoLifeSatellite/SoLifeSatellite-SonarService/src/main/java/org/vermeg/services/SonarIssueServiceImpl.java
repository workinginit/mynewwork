package org.vermeg.services;

import java.util.ArrayList;
import java.util.List;

import org.sonarqube.ws.Issues;
import org.sonarqube.ws.client.WsClient;
import org.sonarqube.ws.client.issue.SearchWsRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.vermeg.entities.SonarIssue;

@Service
public class SonarIssueServiceImpl implements SonarIssueService{

	@Value("${sonar.url:http://localhost:7223}")
    private String url;
	
    @Value("${sonar.userName}")
    private String userName;
    
    @Value("${sonar.password}")
    private String password;

    @Value("${sonar.token}")
    private String token;
	
	@Override
	public List<SonarIssue> getIssue(String projectName) {
		WsClient wsClient= ConnexionSonarService.getInstance(url, userName, decrypt(password), token);
		
        SearchWsRequest searchWsRequest = new SearchWsRequest();
        ArrayList<String> projectKeys = new ArrayList<>();
        projectKeys.add(projectName);
        searchWsRequest.setProjectKeys(projectKeys);
        
        Issues.SearchWsResponse response = wsClient.issues().search(searchWsRequest);

        List<Issues.Issue> issuesList = response.getIssuesList();
        
    	ArrayList<SonarIssue> listofissue = new ArrayList<SonarIssue>();

    	//Attention on doit remplacer
        for (Issues.Issue issue : issuesList) {
        	if(issue.getComponent().contains("/src/main/java/")) {
        		String[] module = issue.getComponent().substring(issue.getComponent().indexOf(":")+1).split("/");
            	SonarIssue s = new SonarIssue(issue.getKey(),issue.getProject(), issue.getComponent().substring(issue.getComponent().indexOf(":")+1) ,issue.getType().toString(), issue.getSeverity().toString(), issue.getStatus(),
            			issue.getCreationDate().substring(0, 10), issue.getUpdateDate().substring(0, 10), 
            			(issue.getComponent().substring(issue.getComponent().indexOf("/java")+6, issue.getComponent().lastIndexOf("/"))).replaceAll("/", "."),
            			module[0] );
            	listofissue.add(s);	
        	}
        }	
        
  
		return listofissue;
	}


	@Override
	public String decrypt(String password) {
		String aCrypter="";
        for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^48;  
            aCrypter=aCrypter+(char)c; 
        }
        return aCrypter;
	}

}
