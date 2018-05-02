package org.vermeg.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.vermeg.entities.SonarIssue;

public interface SonarRepository  extends ElasticsearchRepository<SonarIssue, String>{

	 List<SonarIssue> findByComponentContaining(String component);
	 
	 List<SonarIssue> findBySeverity(String severity);

	 List<SonarIssue> findByPack(String pack);
	 
	 List<SonarIssue> findByModuleAndPack(String module,String pack);

	 List<SonarIssue> findByModule(String module);
	 
	 List<SonarIssue> findBySeverityIn(Collection<String>Severitys);
	 
	 List<SonarIssue> findByModuleAndSeverity(String module,String severity);

	 long countByModule(String module);


}
