package org.vermeg.services;

import java.util.List;

import org.vermeg.entities.JiraIssue;

import com.atlassian.jira.rest.client.api.JiraRestClient;


public interface JiraService {
	
	List<JiraIssue> allofjira(String project);
	List<JiraIssue> getallIssues(JiraRestClient jrc,int i);
}
