package org.vermeg.services;

import java.util.Collection;
import java.util.List;

import org.vermeg.entities.SonarIssue;

public interface SonarService {
	
    Iterable<SonarIssue> findAll();
    
    List<SonarIssue> findByComponentContaining(String component);
    
    long totalIssue();
    
	List<SonarIssue> findBySeverity(String severity);
	
	List<SonarIssue> findByPack(String pack);

    List<SonarIssue> findByModuleAndPackAndType(String module,String pack, String type);
    
	List<SonarIssue> findByModule(String module);
	
	List<SonarIssue> findBySeverityIn(Collection<String>Severitys);
	 
	List<SonarIssue> findByModuleAndSeverity(String module,String severity);
	
	long countByModule(String module);

}
