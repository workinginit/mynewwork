package org.vermeg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vermeg.repository.JiraIssueRepository;

@Service
public class JiraIssueServiceImpl implements JiraIssueService{

	@Autowired
    private JiraIssueRepository jiraIssueRepository;
	
	@Override
	public boolean existsById(String primaryKey) {

		return jiraIssueRepository.existsById(primaryKey);
	}

}
