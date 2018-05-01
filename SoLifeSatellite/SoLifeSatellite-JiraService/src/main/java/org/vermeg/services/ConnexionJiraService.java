package org.vermeg.services;

import java.net.URI;
import java.net.URISyntaxException;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;


public class ConnexionJiraService {
	
	private static JiraRestClient jiraRestClient = null;

	private ConnexionJiraService(String url, String userName, String password) {

		try {
			URI baseUri = new URI(url);
			JiraRestClientFactory restClientFactory = new AsynchronousJiraRestClientFactory();
			jiraRestClient = restClientFactory.createWithBasicHttpAuthentication(baseUri, userName, password);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	
	}
	
	public static JiraRestClient getInstance(String url, String userName, String password) {
		if (jiraRestClient==null) 
		{
			new ConnexionJiraService(url, userName, password);			
		}
		   return jiraRestClient;
	}
	

}
