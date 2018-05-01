package org.vermeg.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vermeg.entities.Module;
import org.vermeg.entities.PackageIssue;
import org.vermeg.entities.SeverityByModule;
import org.vermeg.entities.SonarIssue;
import org.vermeg.entities.SonarSeverity;

@Service
public class CodeQualityServiceImpl implements CodeQualityService{

	@Autowired
	private SvnModuleService svnService;
	    
	@Autowired
	private SonarService sonarService;	
	
    @Autowired
    private SvnPackService svnPackService;

	@Override
	public List<Module> issueByModule() {
		
		List<String> listaa = svnService.findById("1000").get().getModule();
    	List<Module> listModule = new ArrayList<Module>();
    	long total = sonarService.totalIssue();
    	
        Collection<String> Severitys = Arrays.asList("BLOCKER", "CRITICAL", "MAJOR");
    	int totalseverity = sonarService.findBySeverityIn(Severitys).size();
    	
        for(String rt : listaa) {
        	int v = 0;
        	int b = 0;
        	int c = 0;
        	
        	for ( Iterator<SonarIssue> entries2 =  sonarService.findAll().iterator( ); entries2.hasNext( ); ) {
            	SonarIssue si =  entries2.next( );
            	if(si.getComponent().startsWith(rt)) {

            		if(si.getType().equals("VULNERABILITY")) {
            			v++;
            		}
            		else if(si.getType().equals("BUG")) {
            			b++;
            		}
            		else if(si.getType().equals("CODE_SMELL")) {
            			c++;
            		}
            		
            	}
        	}
        	
        	int totalByModule = v+b+c;
        	float per = (float) totalByModule/total  * 100;

        	int blokcer = sonarService.findByModuleAndSeverity(rt, "BLOCKER").size();
        	int critical = sonarService.findByModuleAndSeverity(rt, "CRITICAL").size();
        	int major = sonarService.findByModuleAndSeverity(rt, "MAJOR").size();

        	int totalsev = blokcer+critical+major;
        	float persev = (float) totalsev/totalseverity  * 100;
        	
        	Module m = new Module(rt, b, v, c, per, blokcer, critical, major, persev);

        	listModule.add(m);	
        }
        
        return listModule;
	}

	@Override
	public SonarSeverity totalSeverity() {

		int major = sonarService.findBySeverity("MAJOR").size();
		int minor = sonarService.findBySeverity("MINOR").size();
		int info = sonarService.findBySeverity("INFO").size();
		int critical = sonarService.findBySeverity("CRITICAL").size();
		int blocker = sonarService.findBySeverity("BLOCKER").size();

		int total = major + minor + info + critical + blocker;
		
		float majorf = 0.0f;
		float minorf = 0.0f;
		float infof = 0.0f;
		float criticalf = 0.0f;
		float blockerf = 0.0f;
		
		if(total !=0 ) {
			majorf = (float) major / total * 100 ;
			minorf = (float) minor / total * 100 ;
			infof = (float) info / total * 100 ;
			criticalf = (float) critical / total * 100 ;
			blockerf = (float) blocker / total * 100 ;
		}
	
		
		SonarSeverity sonarSeverity =  new SonarSeverity(blockerf, criticalf, majorf, minorf, infof);
		
		return sonarSeverity;
	}

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

        	SeverityByModule severityByModule = new SeverityByModule(rt, per);
        	listSeverityByModule.add(severityByModule);
        }	
		
		return listSeverityByModule;
	}

	@Override
	public List<PackageIssue> issueModuleByPackage(String namemodule) {
		
        List<String> listaa = svnPackService.findByModule(namemodule).getListPackage();
        int totalpack = sonarService.findByModule(namemodule).size();
    	List<PackageIssue> listPack = new ArrayList<PackageIssue>();

        
        for(String pbm : listaa) {
        	
        	int size = sonarService.findByModuleAndPack(namemodule, pbm).size();
        	PackageIssue packageIssue = new PackageIssue(namemodule, pbm, size, totalpack);
        	listPack.add(packageIssue);
        }

		return listPack;
	}
	
	
	
}
