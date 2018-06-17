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
	
	//tested
    List<Module> issueByModule(); 
    
    List<SonarSeverityModule> listSonarSeverity(String moduleName);
    
    List<SonarTypeModule> listSonarType(String moduleName);
    
    //tested
    List<PackageIssue> issueModuleByPackage(String namemodule); 
    
    //tested
    SonarSeverity totalSeverity();
    
    //tested
    List<SeverityByModule> severityByModule(String severity); 

    //tested
    JenkinsBuild JenkinsLastBuild();
    
    //tested
    List<GloabalVioaltionSonar> violationSonar(); 


}
