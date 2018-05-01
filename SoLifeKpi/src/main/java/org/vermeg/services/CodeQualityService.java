package org.vermeg.services;

import java.util.List;

import org.vermeg.entities.Module;
import org.vermeg.entities.PackageIssue;
import org.vermeg.entities.SeverityByModule;
import org.vermeg.entities.SonarSeverity;

public interface CodeQualityService {
	
    List<Module> issueByModule(); 
    
    SonarSeverity totalSeverity();
    
    List<SeverityByModule> severityByModule(String severity); 

    List<PackageIssue> issueModuleByPackage(String namemodule); 

}
