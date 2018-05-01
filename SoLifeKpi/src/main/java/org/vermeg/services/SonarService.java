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

    List<SonarIssue> findByModuleAndPack(String module,String pack);
    
	List<SonarIssue> findByModule(String module);
	
	List<SonarIssue> findBySeverityIn(Collection<String>Severitys);
	 
	List<SonarIssue> findByModuleAndSeverity(String module,String severity);


}
