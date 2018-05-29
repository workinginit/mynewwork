package org.vermeg.services;

import java.util.List;

import org.vermeg.entities.SonarIssue;

public interface SonarIssueService {

	List<SonarIssue> getIssue(String projectName);
	 	 
	String decrypt(String password);
	 
}
