package org.vermeg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.vermeg.entities.RepositoryTree;
import org.vermeg.entities.SeverityByModule;
import org.vermeg.entities.SonarIssue;
import org.vermeg.services.SonarService;
import org.vermeg.services.SvnPackService;
import org.vermeg.services.SvnModuleService;
import org.vermeg.entities.Module;
import org.vermeg.entities.PackageIssue;


@SpringBootApplication
public class SoLifeKpiApplication {

    @Autowired
    private ElasticsearchOperations es;
    
    @Autowired
    private SvnModuleService svnService;
    
    @Autowired
    private SonarService sonarService;
    
    @Autowired
    private SvnPackService svnPackService;
	
	public static void main(String[] args) {
		SpringApplication.run(SoLifeKpiApplication.class, args);
	}
	
	
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            printElasticSearchInfo();
            float result = (float) 7 / 3;
       	 		System.out.println(result);
           System.out.println(sonarService.totalIssue());
           
       	int major = sonarService.findBySeverity("MAJOR").size();
		int minor = sonarService.findBySeverity("MINOR").size();
		int info = sonarService.findBySeverity("INFO").size();
		int critical = sonarService.findBySeverity("CRITICAL").size();
		int blocker = sonarService.findBySeverity("BLOCKER").size();

		int total = major + minor + info + critical + blocker;
		
		
		
		
		
		
		/*List<String> listaa = svnService.findById("1000").get().getModule();

		List<SonarIssue> listaa2 = sonarService.findBySeverity("MAJOR");    
		int size = listaa2.size();
		*/
		



        List<String> listaa = svnPackService.findByModule("SoLifeSatellite-SvnService").getListPackage();
       
        int totalpack = sonarService.findByModule("SoLifeSatellite-SonarService").size();
    	System.out.println("toto"+totalpack);
        List<PackageIssue> listPack = new ArrayList<PackageIssue>();

        
        for(String pbm : listaa) {
        	
        	int size = sonarService.findByModuleAndPack("SoLifeSatellite-SvnService",pbm).size();
        	System.out.println("size"+size + " "+pbm );
        	PackageIssue packageIssue = new PackageIssue("SoLifeSatellite-SvnService",pbm, size, totalpack);
        	listPack.add(packageIssue);
        }

        for(PackageIssue l: listPack) {
            for(SonarIssue la: sonarService.findByModuleAndPack("SoLifeSatellite-SvnService",l.getNamePackage())) {
            	System.out.println("test:"+la.getPack() +la.getComponent() +"  "+la.getModule());
            }       
          }
        
        Collection<String> Severitys = Arrays.asList("BLOCKER","CRITICAL","");
        
        
		//System.out.println("dd"+sonarService.findByModuleAndSeverity("SoLifeSatellite-SonarService", "MINOR").size());

    
        
       /* for(String rt : svnPackService.findByModule("SoLifeSatellite-SvnService").getListPackage()) {
        	int i = 0;
        	for (SonarIssue  lm : listaa2) {
        		
        		if( lm.getComponent().startsWith(rt)) {
        			i++;
        		}
        		
        	}
        	float per = (float) i/size  * 100;

        	System.out.println(per);
        	SeverityByModule severityByModule = new SeverityByModule(rt, 00);
        	System.out.println("dd");
        }*/
            
        
            /*Iterator<RepositoryTree> it = svnService.findAll().iterator();
            while(it.hasNext()) {
            System.out.println(it.next().getId());
            }
            
            Iterator<SonarIssue> ito = sonarService.findAll().iterator();
            while(ito.hasNext()) {
            	
            System.out.println(ito.next().getComponent());
            }*/
           

            
            
           // System.out.println(sonarService.findByComponentContaining("SoLifeSatellite-Jira").size());
           // List<SonarIssue> ss = (List<SonarIssue>) sonarService.findAll();
            

            /*
            for(int i=0; i<ss.size();i++) {
            	System.out.println("mabrouk : "+ss.get(i));
            }*/
        /*	List<String> listaa = svnService.findById("1000").get().getModule();
        	List<Module> listModule = new ArrayList<Module>();
        	
        	System.out.println("lista :::"+listaa.size());
            for(String rt : listaa) {
            	int v = 0;
            	int b = 0;
            	int c = 0;
            	System.out.println("number "+1);
            	for ( Iterator<SonarIssue> entries2 =  sonarService.findAll().iterator( ); entries2.hasNext( ); ) {
                	SonarIssue si =  entries2.next( );
                	if(si.getComponent().startsWith(rt)) {
                		System.out.println(si.getComponent().contains(rt) );
                		System.out.println("ddd"+si.getComponent() +" "+rt);
                		if(si.getType().equals("VULNERABILITY")) {
                			v++;
                			System.out.println("vuln"+v);
                		}
                		else if(si.getType().equals("BUG")) {
                			b++;
                			System.out.println("bug"+b);
                		}
                		else if(si.getType().equals("CODE_SMELL")) {
                			c++;
                			System.out.println("code"+c);
                		}
                	}
            	}
            	System.out.println("-----------------------------");
            	Module m = new Module(rt,b,v,c);

            	listModule.add(m);	
            }
            
            
            System.out.println(listModule.get(0).getBug()+" "+listModule.get(0).getVulnerability()+" "+listModule.get(0).getNameModule());
            */
            
        	
           /* for ( Iterator<RepositoryTree> entries = svnService.findAll().iterator( ); entries.hasNext( ); ) {
            	
            	RepositoryTree logEntry =  entries.next( );
            	System.out.println("ss"+logEntry.getModule());
            }
            
            Iterator<RepositoryTree> it = svnService.findAll().iterator();
            
            System.out.println("angoot"+it.next().getModule());
            
            List<String> mama= it.next().getModule();
            System.out.println("hilye"+mama.size());*/
            
        	
        	
            
            
            

            
            
          /*  for ( Iterator<RepositoryTree> entries = svnService.findAll().iterator( ); entries.hasNext( ); ) {
            	
            	
                for ( Iterator<SonarIssue> entries2 =  sonarService.findAll().iterator( ); entries2.hasNext( ); ) {
                
                	SonarIssue si =  entries2.next( );
                	
                
                	
                }

            
            }*/
            
            
        };
    }
	
	
    private void printElasticSearchInfo() {

        System.out.println("--ElasticSearchiciiiiiiiiiiiiii-->");
        Client client = es.getClient();
        Map<String, String> asMap = client.settings().getAsMap();

        asMap.forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });
        System.out.println("<--ElasticSearchendddddddddddd--");
    }
}
