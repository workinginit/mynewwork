package org.vermeg.services;


import java.util.List;

import org.vermeg.entities.GloabalVioaltionSonar;
import org.vermeg.entities.JenkinsBuild;
import org.vermeg.entities.Module;
import org.vermeg.entities.PackageIssue;
import org.vermeg.entities.SeverityByModule;
import org.vermeg.entities.SonarSeverity;
import org.vermeg.entities.SonarSeverityModule;
import org.vermeg.entities.SonarTypeModule;

public interface CodeQualityService {
	

    List<Module> issueByModule(); 
    
    List<SonarSeverityModule> listSonarSeverity(String moduleName);
    
    List<SonarTypeModule> listSonarType(String moduleName);    

    List<PackageIssue> issueModuleByPackage(String namemodule); 
      
    JenkinsBuild JenkinsLastBuild();
    
  
    SonarSeverity totalSeverity();
    
    List<SeverityByModule> severityByModule(String severity); 

    List<GloabalVioaltionSonar> violationSonar(); 


}
