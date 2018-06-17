package org.vermeg.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.vermeg.entities.GloabalVioaltionSonar;
import org.vermeg.entities.JenkinsBuild;
import org.vermeg.entities.JiraPriority;
import org.vermeg.entities.JiraProjectPriority;
import org.vermeg.entities.Module;
import org.vermeg.entities.PackageIssue;
import org.vermeg.entities.SonarSeveritye;
import org.vermeg.entities.SeverityByModule;
import org.vermeg.entities.SonarIssue;
import org.vermeg.entities.SonarSeverity;
import org.vermeg.entities.SonarSeverityModule;
import org.vermeg.entities.SonarType;
import org.vermeg.entities.SonarTypeModule;
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
    	long totalIssue = sonarService.count();
    	
        Collection<String> Severitys = Arrays.asList(SonarSeveritye.BLOCKER.name(), SonarSeveritye.CRITICAL.name(), SonarSeveritye.MAJOR.name());
    	long totalIssueSeverity = sonarService.countBySeverityIn(Severitys);
    	
        for(String rt : listaa) {
        	long severitByModule = sonarService.countByModuleAndSeverityIn(rt, Severitys);
        	int count = sonarService.countByModule(rt);
        	
        	Module module = new Module(rt, (float) count /totalIssue * 100 , (float) severitByModule / totalIssueSeverity *100,listSonarType(rt), listSonarSeverity(rt));
            listModule.add(module);
        }
        
        return listModule;
	}
	
	@Override
	public List<SonarTypeModule> listSonarType(String moduleName) {
    	int count = sonarService.countByModule(moduleName);
    	List<SonarTypeModule> listOfSonarType = new ArrayList<SonarTypeModule>();
    	int i = 0;

        for (SonarType st : SonarType.values()) {
        	if(i<3) {
            	SonarTypeModule stm = new SonarTypeModule(st, (float) sonarService.countByModuleAndType(moduleName, st.name()) / count * 100);
            	listOfSonarType.add(stm); 		
            	i++;
        	}

        }
		return listOfSonarType;
	}
	
	@Override
	public List<SonarSeverityModule> listSonarSeverity(String moduleName) {
    	List<SonarSeverityModule> listOfSonarSeverity = new ArrayList<SonarSeverityModule>();
        Collection<String> Severitys = Arrays.asList(SonarSeveritye.BLOCKER.name(), SonarSeveritye.CRITICAL.name(), SonarSeveritye.MAJOR.name());
    	long totalIssueSeverity = sonarService.countByModuleAndSeverityIn(moduleName, Severitys);
    	int i = 0;
    	
        for (SonarSeveritye ss : SonarSeveritye.values()) {
        	if(i<3) {
            	SonarSeverityModule ssm = new SonarSeverityModule(ss, (float) sonarService.countByModuleAndSeverity(moduleName, ss.name()) / totalIssueSeverity *100);
            	listOfSonarSeverity.add(ssm);
            	i++;	
        	}

        }
		return listOfSonarSeverity;
	}
	
	/**
	 * cette méthode returne la liste des issue par package d'un module donnée
	 */
	@Override
	public List<PackageIssue> issueModuleByPackage(String namemodule) {
		
        List<String> listaa = svnPackService.findByModule(namemodule).getListPackage();
    	List<PackageIssue> listPack = new ArrayList<PackageIssue>();
    	int totalIssueForModule = sonarService.countByModuleAndPackIn(namemodule, listaa);

        for(String pbm : listaa) {
        	List<SonarTypeModule> listOfSonarType = new ArrayList<SonarTypeModule>();
	
            for (SonarType st : SonarType.values()) {
                SonarTypeModule stm = new SonarTypeModule(st, sonarService.countByModuleAndPackAndType(namemodule, pbm, st.name()) );
                listOfSonarType.add(stm); 		            	
            }
        	
        	PackageIssue packageIssue = new PackageIssue(pbm, (float) sonarService.countByModuleAndPack(namemodule, pbm)/totalIssueForModule *100 ,listOfSonarType);
        	listPack.add(packageIssue);
        }

		return listPack;
	}


	/**
	 * cette méthode retourne le pourcentage de severite d'un projet par type
	 */
	@Override
	public SonarSeverity totalSeverity() {
		int blocker = sonarService.findBySeverity(SonarSeveritye.BLOCKER.name()).size();
		int critical = sonarService.findBySeverity(SonarSeveritye.CRITICAL.name()).size();
		int major = sonarService.findBySeverity(SonarSeveritye.MAJOR.name()).size();

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
        		if( lm.getModule().equals(rt)) {
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
	 * cette méthode retourne l'etat du dernier build 
	 */
	@Override
	public JenkinsBuild JenkinsLastBuild() {
		Iterator<JenkinsBuild> ito = jenkinsBuildRepository.findAll(new Sort(Sort.Direction.DESC, "number")).iterator();
		JenkinsBuild jB = ito.next();
		return jB;
	}

	@Override
	public List<GloabalVioaltionSonar> violationSonar() {

    	List<GloabalVioaltionSonar> listViolation = new ArrayList<GloabalVioaltionSonar>();
		String[] type = {"BUG", "VULNERABILITY", "CODE_SMELL"};
    	
		for(int i = 0; i<type.length; i++) {
			int blocker = sonarService.countByTypeAndSeverity(type[i] ,SonarSeveritye.BLOCKER.name());
			int critical = sonarService.countByTypeAndSeverity(type[i],SonarSeveritye.CRITICAL.name());
			int major = sonarService.countByTypeAndSeverity(type[i],SonarSeveritye.MAJOR.name());
			int minor = sonarService.countByTypeAndSeverity(type[i],SonarSeveritye.MINOR.name());
			int info = sonarService.countByTypeAndSeverity(type[i],SonarSeveritye.INFO.name());
			
			GloabalVioaltionSonar gvs = new GloabalVioaltionSonar(type[i],blocker,critical,major,minor,info);
			listViolation.add(gvs);
		}


		return listViolation;
	}

}
