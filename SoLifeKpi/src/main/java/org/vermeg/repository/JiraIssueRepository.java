package org.vermeg.repository;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.vermeg.entities.JiraIssue;

public interface JiraIssueRepository extends ElasticsearchRepository<JiraIssue, String>{

	 int countByIssueType(String issueType);
	 
	 int countByIssueTypeAndPriority(String issueType, String priority);

}
