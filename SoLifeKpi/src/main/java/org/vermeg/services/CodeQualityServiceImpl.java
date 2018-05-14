package org.vermeg.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.vermeg.entities.JenkinsBuild;
import org.vermeg.entities.Module;
import org.vermeg.entities.PackageIssue;
import org.vermeg.entities.Severity;
import org.vermeg.entities.SeverityByModule;
import org.vermeg.entities.SonarIssue;
import org.vermeg.entities.SonarSeverity;
import org.vermeg.entities.TypeSonar;
import org.vermeg.repository.JenkinsBuildRepository;
import org.vermeg.repository.SonarRepository;
import org.vermeg.repository.SvnModuleRepository;
import org.vermeg.repository.SvnPackRepository;


@Service
public class CodeQualityServiceImpl implements CodeQualityService{

	@Autowired
	private SvnModuleRepository svnService;
	    
	@Autowired
	private SonarRepository sonarService;	
	
    @Autowired
    private SvnPackRepository svnPackService;
    
	@Autowired
    private JenkinsBuildRepository jenkinsBuildRepository;

	@Override
	public List<Module> issueByModule() {
		
		List<String> listaa = svnService.findById("1000").get().getModule();
    	List<Module> listModule = new ArrayList<Module>();
    	long totalIssueType = sonarService.count();
    	
        Collection<String> Severitys = Arrays.asList(Severity.BLOCKER.name(), Severity.CRITICAL.name(), Severity.MAJOR.name());
    	long totalIssueSeverity = sonarService.countBySeverityIn(Severitys);
    	
        for(String rt : listaa) {
             	
        	int v = sonarService.countByModuleAndType(rt, TypeSonar.VULNERABILITY.name());
        	int b = sonarService.countByModuleAndType(rt, TypeSonar.BUG.name());
        	int c = sonarService.countByModuleAndType(rt, TypeSonar.CODE_SMELL.name());
        	
        	int totalByModule = v+b+c;
        	float per = (float) totalByModule/totalIssueType  * 100;

        	int blokcer = sonarService.countByModuleAndSeverity(rt, Severity.BLOCKER.name()); 
        	int critical = sonarService.countByModuleAndSeverity(rt, Severity.CRITICAL.name());
        	int major = sonarService.countByModuleAndSeverity(rt, Severity.MAJOR.name());

        	int totalsev = blokcer+critical+major;
        	float persev = (float) totalsev/totalIssueSeverity  * 100;
        
        	listModule.add(new Module(rt, b, v, c, per, blokcer, critical, major, persev));	
        }
        
        return listModule;
	}

	/**
	 * cette méthode retourne le pourcentage de severite d'un projet par type
	 */
	@Override
	public SonarSeverity totalSeverity() {
		int blocker = sonarService.findBySeverity(Severity.BLOCKER.name()).size();
		int critical = sonarService.findBySeverity(Severity.CRITICAL.name()).size();
		int major = sonarService.findBySeverity(Severity.MAJOR.name()).size();

		int total = blocker + critical + major;
		
		float blockerf = 0.0f;
		float criticalf = 0.0f;
		float majorf = 0.0f;

		if(total !=0 ) {
			blockerf = (float) blocker / total * 100 ;
			criticalf = (float) critical / total * 100 ;
			majorf = (float) major / total * 100 ;
		}
	
		SonarSeverity sonarSeverity =  new SonarSeverity(blockerf, criticalf, majorf);
		
		return sonarSeverity;
	}

	/**
	 * méthode <<roue de secour>> à enlever peut-être
	 */
	@Override
	public List<SeverityByModule> severityByModule(String severity) {
    	List<SeverityByModule> listSeverityByModule = new ArrayList<SeverityByModule>();
		
		List<String> listaa = svnService.findById("1000").get().getModule();
		List<SonarIssue> listaa2 = sonarService.findBySeverity(severity);    
		int size = listaa2.size();
		
        for(String rt : listaa) {
        	int i = 0;
        	for (SonarIssue  lm : listaa2) {
        		if( lm.getComponent().startsWith(rt)) {
        			i++;
        		}
        	}
        	
        	float per = 0;
        	if(size!=0) {
            	 per = (float) i/size  * 100;
        	}

        	listSeverityByModule.add(new SeverityByModule(rt, per));
        }	
		
		return listSeverityByModule;
	}

	/**
	 * cette méthode returne la liste des issue par package d'un module donnée
	 */
	@Override
	public List<PackageIssue> issueModuleByPackage(String namemodule) {
		
        List<String> listaa = svnPackService.findByModule(namemodule).getListPackage();
    	List<PackageIssue> listPack = new ArrayList<PackageIssue>();

        for(String pbm : listaa) {
        	
        	int bug = sonarService.findByModuleAndPackAndType(namemodule, pbm, "BUG").size();
        	int vulnerability = sonarService.findByModuleAndPackAndType(namemodule, pbm, "VULNERABILITY").size();
        	int codeSmell = sonarService.findByModuleAndPackAndType(namemodule, pbm, "CODE_SMELL").size();
        	
        	int total = bug + vulnerability + codeSmell;
        	PackageIssue packageIssue = new PackageIssue(namemodule, pbm, bug, vulnerability, codeSmell, total);
        	listPack.add(packageIssue);
        }

		return listPack;
	}

	/**
	 * cette méthode retourne l'etat du dernier build 
	 */
	@Override
	public JenkinsBuild JenkinsLastBuild() {
		Iterator<JenkinsBuild> ito = jenkinsBuildRepository.findAll(new Sort(Sort.Direction.DESC, "number")).iterator();
		JenkinsBuild jB = ito.next();
		return jB;
	}
}
