package org.vermeg.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.vermeg.entities.SonarIssue;

public interface SonarRepository extends ElasticsearchRepository<SonarIssue, String>{

	 List<SonarIssue> findByComponentContaining(String component);
	 
	 List<SonarIssue> findBySeverity(String severity);

	 List<SonarIssue> findByPack(String pack);
	 
	 List<SonarIssue> findByModuleAndPackAndType(String module,String pack, String type);

	 List<SonarIssue> findByModule(String module);
	 
	 List<SonarIssue> findBySeverityIn(Collection<String>Severitys);
	 
	 List<SonarIssue> findByModuleAndSeverity(String module,String severity);

	 int countByModule(String module);
	 
	 long countBySeverityIn(Collection<String>Severitys);

	 int countByModuleAndSeverity(String module, String severity);

	 int countByModuleAndType(String module, String type);
	 
	 int countByTypeAndSeverity(String type, String severity);

	 long countByModuleAndSeverityIn(String module,Collection<String>Severitys);

	 int countByModuleAndPackAndType(String module,String pack, String type);

	 int countByModuleAndPack(String module,String pack);
	 
	 int countByModuleAndPackIn(String module, Collection<String>packs);

	 int countBySeverity(String severity);


}
