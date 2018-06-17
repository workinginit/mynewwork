package org.vermeg.web;



import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.vermeg.entities.CodeQualitySettings;
import org.vermeg.entities.GloabalVioaltionSonar;
import org.vermeg.entities.JenkinsBuild;
import org.vermeg.entities.Module;
import org.vermeg.entities.PackageIssue;
import org.vermeg.entities.RepositoryTree;
import org.vermeg.entities.SeverityByModule;
import org.vermeg.entities.SonarSeverity;
import org.vermeg.repository.CodeQualitySettingsRepository;
import org.vermeg.repository.SvnModuleRepository;
import org.vermeg.services.CodeQualityService;

@RestController
@CrossOrigin("*")
public class CodeQualityRestService {
	
	@Autowired
	private CodeQualityService moduleService;
	
	@Autowired
	private SvnModuleRepository svnModuleService;

	@Autowired
	private CodeQualitySettingsRepository codeQualitySettingsRepository;
	
	@RequestMapping(value="/getIssueByModule", method=RequestMethod.GET)
	public List<Module> getModule(){
      
		return moduleService.issueByModule(); 
	}

	@RequestMapping(value="/getSeverity", method=RequestMethod.GET)
	public SonarSeverity getSeverity(){
      
		return moduleService.totalSeverity();
	}
	
	@RequestMapping(value="/getSeverityByModule", method=RequestMethod.GET)
	public List<SeverityByModule> getSeverityByModule(@PathParam("severity") String severity){
      
		return moduleService.severityByModule(severity);
	}
	
	@RequestMapping(value="/getIssueModuleByPackage", method=RequestMethod.GET)
	public List<PackageIssue> getIssueModuleByPackage(@PathParam("nameModule") String nameModule){
      
		return moduleService.issueModuleByPackage(nameModule);
	}
	
	@RequestMapping(value="/getRepositoryInfo", method=RequestMethod.GET)
	public Optional<RepositoryTree> getRepositoryInfo(){
		return svnModuleService.findById("1000");
	}
	
	@RequestMapping(value="/getJenkinsLastBuild", method=RequestMethod.GET)
	public JenkinsBuild getJenkinsLastBuild(){
		return moduleService.JenkinsLastBuild();
	}
	
	@RequestMapping(value="/updatecodequality", method=RequestMethod.PUT)
	public CodeQualitySettings updateCodeQuality(@RequestBody CodeQualitySettings c) {
		c.setId(200L);
		System.out.println("ded");
		return codeQualitySettingsRepository.save(c);
	}


	@RequestMapping(value="/getcodequalitysettings", method=RequestMethod.GET)
	public Optional<CodeQualitySettings> getcodequalitysettings() {
		
		return codeQualitySettingsRepository.findById(200L);
	}
	
	
	@RequestMapping(value="/sonarviolation", method=RequestMethod.GET)
	public List<GloabalVioaltionSonar> sonarviolation() {
		
		return moduleService.violationSonar();
	}
}
