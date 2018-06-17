package org.vermeg;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.vermeg.entities.CodeQualitySettings;
import org.vermeg.entities.GloabalVioaltionSonar;
import org.vermeg.entities.JenkinsBuild;
import org.vermeg.entities.Module;
import org.vermeg.entities.PackageIssue;
import org.vermeg.entities.SeverityByModule;
import org.vermeg.entities.SonarSeverity;
import org.vermeg.repository.CodeQualitySettingsRepository;
import org.vermeg.services.CodeQualityService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SoLifeKpiApplication.class)
public class CodeQualityServiceTest {

    @Autowired
    private ElasticsearchTemplate esTemplate;
	
	@Autowired
	private CodeQualitySettingsRepository codeQualitySettingsRepository;
    
	@Autowired
	private CodeQualityService codeQualityService;
	
	
    @Before
    public void before() {
        esTemplate.deleteIndex(CodeQualitySettings.class);
        esTemplate.createIndex(CodeQualitySettings.class);
        esTemplate.putMapping(CodeQualitySettings.class);
        esTemplate.refresh(CodeQualitySettings.class);

    }
    
	
	@Test
	public void testCodeQualitySettingssave() {

	   CodeQualitySettings c =	new CodeQualitySettings(200L, 55,77 );
	   
	   CodeQualitySettings testc = codeQualitySettingsRepository.save(c);
	      assertNotNull(testc.getId());
	      assertEquals(testc.getAlert(), c.getAlert());
	      assertEquals(testc.getInfo(), c.getInfo());
	
	}
	
	
	@Test
	public void testFindcodeQsettingsById() {

	   CodeQualitySettings c =	new CodeQualitySettings(200L, 55,77 );
	   codeQualitySettingsRepository.save(c);
	   
	   Optional<CodeQualitySettings> testc = codeQualitySettingsRepository.findById(200L);

	   	  assertNotNull(testc.get().getId());
	      assertEquals(testc.get().getAlert(), c.getAlert());
	      assertEquals(testc.get().getInfo(), c.getInfo());
	
	}
	
	
	@Test
	public void testtotalSeverity() {

		SonarSeverity sonar = new SonarSeverity(0.0f, 0.0f, 100.0f);

		SonarSeverity testsonar =  codeQualityService.totalSeverity();
	   
		System.out.println(testsonar.getMajor() +"ddddddd");
        assertThat(testsonar.getMajor(), is(100.0f));
        assertThat(testsonar.getBlocker(), is(0.0f));
        assertThat(testsonar.getCritical(), is(0.0f));

	}
	
	
	@Test
	public void testJenkinsLastBuild() {

		JenkinsBuild jenkinslast = new JenkinsBuild(3, "solifeproject #3", 84127, "SUCCESS","2018-05-02");

		JenkinsBuild testjenkinslast = codeQualityService.JenkinsLastBuild();


	      assertEquals(jenkinslast, testjenkinslast);


		
	}
	
	@Test
	public void testissueByModule() {

        List<Module> ModuleList = new ArrayList<Module>();

        //ModuleList.add(new Module("SoLifeSatellite-SvnService", 1, 0, 13, 45.16129f, 0, 0, 4, 44.444447f));

        List<Module> testModuleList = codeQualityService.issueByModule();
        assertEquals(ModuleList.get(0), testModuleList.get(0));
	
	}
	
	
	@Test
	public void testSeverityByModule() {

        List<SeverityByModule> SeverityByModuleList = new ArrayList<SeverityByModule>();

        SeverityByModuleList.add(new SeverityByModule("SoLifeSatellite-SvnService", 44.444447f));

        List<SeverityByModule> testSeverityByModuleList = codeQualityService.severityByModule("Major");

        assertThat(testSeverityByModuleList.size(), is(4));
        assertEquals(SeverityByModuleList.get(0),testSeverityByModuleList.get(0) );
  
	}
	
	@Test
	public void testissueModuleByPackage() {

       List<PackageIssue> PackageIssueList = new ArrayList<PackageIssue>();

        //PackageIssueList.add(new PackageIssue("SoLifeSatellite-JenkinsService", "org.vermeg.entities",0,0,1,1));
        //PackageIssueList.add(new PackageIssue("SoLifeSatellite-JenkinsService", "org.vermeg.services", 0, 2, 4, 6));
       // PackageIssueList.add(new PackageIssue("SoLifeSatellite-JenkinsService", "org.vermeg.web",0, 0, 0, 0));
       // PackageIssueList.add(new PackageIssue("SoLifeSatellite-JenkinsService", "org.vermeg",0,0,0,0));

        List<PackageIssue> testPackageIssueList = codeQualityService.issueModuleByPackage("SoLifeSatellite-JenkinsService");

        assertThat(testPackageIssueList.size(), is(4));
        assertEquals(PackageIssueList.get(0), testPackageIssueList.get(0));
        assertEquals(PackageIssueList.get(1), testPackageIssueList.get(1));
        assertEquals(PackageIssueList.get(2), testPackageIssueList.get(2));
        assertEquals(PackageIssueList.get(3), testPackageIssueList.get(3));

  
	}
	
	@Test
	public void testViolationSonar() {

       List<GloabalVioaltionSonar> GloabalVioaltionSonarList = new ArrayList<GloabalVioaltionSonar>();

       GloabalVioaltionSonarList.add(new GloabalVioaltionSonar("BUG", 0, 0, 1, 0, 0));

        List<GloabalVioaltionSonar> testGloabalVioaltionSonarList = codeQualityService.violationSonar();

        assertThat(testGloabalVioaltionSonarList.size(), is(3));
        assertEquals(GloabalVioaltionSonarList.get(0), testGloabalVioaltionSonarList.get(0));
      

  
	}
	

}
