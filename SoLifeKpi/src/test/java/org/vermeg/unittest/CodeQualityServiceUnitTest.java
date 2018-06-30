package org.vermeg.unittest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;
import org.vermeg.entities.GloabalVioaltionSonar;
import org.vermeg.entities.JenkinsBuild;
import org.vermeg.entities.Module;
import org.vermeg.entities.PackageByModule;
import org.vermeg.entities.PackageIssue;
import org.vermeg.entities.RepositoryTree;
import org.vermeg.entities.SeverityByModule;
import org.vermeg.entities.SonarIssue;
import org.vermeg.entities.SonarSeverity;
import org.vermeg.entities.SonarSeverityModule;
import org.vermeg.entities.SonarSeveritye;
import org.vermeg.entities.SonarType;
import org.vermeg.entities.SonarTypeModule;
import org.vermeg.repository.JenkinsBuildRepository;
import org.vermeg.repository.SonarRepository;
import org.vermeg.repository.SvnModuleRepository;
import org.vermeg.repository.SvnPackRepository;
import org.vermeg.services.CodeQualityServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CodeQualityServiceUnitTest {

	@InjectMocks
	private CodeQualityServiceImpl codeQualityService;
	
	@Mock
	private SvnModuleRepository svnService;
	    
	@Mock
	private SonarRepository sonarService;	
	
	@Mock
    private SvnPackRepository svnPackService;
    
	@Mock
    private JenkinsBuildRepository jenkinsBuildRepository;
	
	
	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public void testListSonarType() {
		
		
	    Mockito.when(sonarService.countByModule("SoLifeSatellite-SvnService")).thenReturn(14);

	    Mockito.when(sonarService.countByModuleAndType("SoLifeSatellite-SvnService", "VULNERABILITY")).thenReturn(0);
	    Mockito.when(sonarService.countByModuleAndType("SoLifeSatellite-SvnService", "BUG")).thenReturn(1);
	    Mockito.when(sonarService.countByModuleAndType("SoLifeSatellite-SvnService", "CODE_SMELL")).thenReturn(13);

	    SonarTypeModule st1 = new SonarTypeModule(SonarType.VULNERABILITY, 0);
	    SonarTypeModule st2 = new SonarTypeModule(SonarType.BUG, 7.1428576f);
	    SonarTypeModule st3 = new SonarTypeModule(SonarType.CODE_SMELL, 92.85714f);

    	List<SonarTypeModule> listOfSonarTypetest = new ArrayList<SonarTypeModule>();
    	listOfSonarTypetest.add(st1);
    	listOfSonarTypetest.add(st2);
    	listOfSonarTypetest.add(st3);
    	
	    List<SonarTypeModule> listOfSonarType = codeQualityService.listSonarType("SoLifeSatellite-SvnService");


	    assertEquals(listOfSonarType, listOfSonarTypetest);

	}
	
	@Test
	public void listSonarSeverity() {
		
        Collection<String> Severitys = Arrays.asList(SonarSeveritye.BLOCKER.name(), SonarSeveritye.CRITICAL.name(), SonarSeveritye.MAJOR.name());

	    Mockito.when(sonarService.countByModuleAndSeverityIn("SoLifeSatellite-SvnService", Severitys)).thenReturn(4L);

	    Mockito.when(sonarService.countByModuleAndSeverity("SoLifeSatellite-SvnService", "BLOCKER")).thenReturn(0);
	    Mockito.when(sonarService.countByModuleAndSeverity("SoLifeSatellite-SvnService", "CRITICAL")).thenReturn(0);
	    Mockito.when(sonarService.countByModuleAndSeverity("SoLifeSatellite-SvnService", "MAJOR")).thenReturn(4);

	    SonarSeverityModule ssm1 = new SonarSeverityModule(SonarSeveritye.BLOCKER, 0);
	    SonarSeverityModule ssm2 = new SonarSeverityModule(SonarSeveritye.CRITICAL, 0);
	    SonarSeverityModule ssm3 = new SonarSeverityModule(SonarSeveritye.MAJOR, 100f);

    	List<SonarSeverityModule> listOfSonarSeverityModuletest = new ArrayList<SonarSeverityModule>();
    	listOfSonarSeverityModuletest.add(ssm1);
    	listOfSonarSeverityModuletest.add(ssm2);
    	listOfSonarSeverityModuletest.add(ssm3);
    	
	    List<SonarSeverityModule> listOfSonarSeverity = codeQualityService.listSonarSeverity("SoLifeSatellite-SvnService");

	    assertEquals(listOfSonarSeverity, listOfSonarSeverityModuletest);

	}
	
	
	@Test
	public void testIssueByModule() {
	
		testListSonarType();
		listSonarSeverity();
		
	    SonarTypeModule st1 = new SonarTypeModule(SonarType.VULNERABILITY, 0);
	    SonarTypeModule st2 = new SonarTypeModule(SonarType.BUG, 7.1428576f);
	    SonarTypeModule st3 = new SonarTypeModule(SonarType.CODE_SMELL, 92.85714f);

    	List<SonarTypeModule> listOfSonarTypetest = new ArrayList<SonarTypeModule>();
    	listOfSonarTypetest.add(st1);
    	listOfSonarTypetest.add(st2);
    	listOfSonarTypetest.add(st3);
    	
	    SonarSeverityModule ssm1 = new SonarSeverityModule(SonarSeveritye.BLOCKER, 0);
	    SonarSeverityModule ssm2 = new SonarSeverityModule(SonarSeveritye.CRITICAL, 0);
	    SonarSeverityModule ssm3 = new SonarSeverityModule(SonarSeveritye.MAJOR, 100f);

    	List<SonarSeverityModule> listOfSonarSeverityModuletest = new ArrayList<SonarSeverityModule>();
    	listOfSonarSeverityModuletest.add(ssm1);
    	listOfSonarSeverityModuletest.add(ssm2);
    	listOfSonarSeverityModuletest.add(ssm3);
    	
		
		List<String> listOfModule = new ArrayList<>();
		listOfModule.add("SoLifeSatellite-SvnService");
		RepositoryTree rt = new RepositoryTree("1000", "ee", 5, listOfModule);

		Optional<RepositoryTree> tt = Optional.of(rt);


		
        Collection<String> Severitys = Arrays.asList(SonarSeveritye.BLOCKER.name(), SonarSeveritye.CRITICAL.name(), SonarSeveritye.MAJOR.name());

	    Mockito.when(svnService.findById("1000")).thenReturn(tt);
	    Mockito.when(sonarService.count()).thenReturn(31L);
	    Mockito.when(sonarService.countBySeverityIn(Severitys)).thenReturn(9L);
	    Mockito.when(sonarService.countByModuleAndSeverityIn("SoLifeSatellite-SvnService", Severitys)).thenReturn(4L);
	    Mockito.when(sonarService.countByModule("SoLifeSatellite-SvnService")).thenReturn(14);

	    
	    Module m = new Module("SoLifeSatellite-SvnService", 45.16129f, 44.444447f, listOfSonarTypetest, listOfSonarSeverityModuletest);
    	List<Module> list = new ArrayList<Module>();
    	list.add(m);
	    
	    
    	List<Module> listModuleTest = new ArrayList<Module>();
    	listModuleTest = codeQualityService.issueByModule() ;
	    

    	
	    assertEquals(listModuleTest.get(0), list.get(0));

	}
	
	@Test
	public void testIssueModuleByPackage() {
		
		List<String> pack = Arrays.asList("org.vermeg.entities");
		
		PackageByModule pbm = new PackageByModule("SoLifeSatellite-SvnService", pack);
		
	    Mockito.when(svnPackService.findByModule("SoLifeSatellite-SvnService")).thenReturn(pbm);
	    Mockito.when(sonarService.countByModuleAndPackIn("SoLifeSatellite-SvnService", pack)).thenReturn(13);
	    
	    Mockito.when(sonarService.countByModuleAndPackAndType("SoLifeSatellite-SvnService", "org.vermeg.entities", "VULNERABILITY")).thenReturn(0);
	    Mockito.when(sonarService.countByModuleAndPackAndType("SoLifeSatellite-SvnService", "org.vermeg.entities", "BUG" )).thenReturn(0);
	    Mockito.when(sonarService.countByModuleAndPackAndType("SoLifeSatellite-SvnService", "org.vermeg.entities", "CODE_SMELL" )).thenReturn(4);

	    Mockito.when(sonarService.countByModuleAndPack("SoLifeSatellite-SvnService", "org.vermeg.entities")).thenReturn(4);
	    
	    SonarTypeModule st1 = new SonarTypeModule(SonarType.VULNERABILITY, 0f);
	    SonarTypeModule st2 = new SonarTypeModule(SonarType.BUG, 0f);
	    SonarTypeModule st3 = new SonarTypeModule(SonarType.CODE_SMELL, 4f);
	    
    	List<SonarTypeModule> listOfSonarTypetest = new ArrayList<SonarTypeModule>();
    	listOfSonarTypetest.add(st1);
    	listOfSonarTypetest.add(st2);
    	listOfSonarTypetest.add(st3);
	    
	    PackageIssue p = new PackageIssue("org.vermeg.entities", 30.769232f, listOfSonarTypetest);
    	List<PackageIssue> listPack = new ArrayList<PackageIssue>();
    	listPack.add(p);
    	
	    List<PackageIssue> listPacktest = codeQualityService.issueModuleByPackage("SoLifeSatellite-SvnService");



    	
	    assertEquals(listPacktest.get(0).getNamePackage(), listPack.get(0).getNamePackage());
	}	
	
	@Test
	public void testTotalSeverity() {
		
	    Mockito.when(sonarService.countBySeverity("BLOCKER")).thenReturn(0);
	    Mockito.when(sonarService.countBySeverity("CRITICAL")).thenReturn(0);
	    Mockito.when(sonarService.countBySeverity("MAJOR")).thenReturn(100);
	    
		SonarSeverity sonartest = new SonarSeverity(0f,0f,100f);

		SonarSeverity ss = codeQualityService.totalSeverity();

	    assertEquals(sonartest, ss);
	}
	
	@Test
	public void testJenkinsLastBuild() {
		JenkinsBuild j2 = new JenkinsBuild(4, "solifeproject #5", 84127L, "SUCCESS", "2018-05-02");
		JenkinsBuild j = new JenkinsBuild(5, "solifeproject #5", 84127L, "SUCCESS", "2018-05-02");
	    ArrayList jobs = new ArrayList();
	    jobs.add(j);
	    jobs.add(j2);

	    Iterable<JenkinsBuild> it = jobs;
	    Mockito.when(jenkinsBuildRepository.findAll(new Sort(Sort.Direction.DESC, "number"))).thenReturn(it);

	    
	    JenkinsBuild jtest = codeQualityService.JenkinsLastBuild();
	    
	    assertEquals(jtest, j);
	}
	
	@Test
	public void testSeverityByModule() {

		List<String> listOfModule = new ArrayList<>();
		listOfModule.add("SoLifeSatellite-SvnService");
		RepositoryTree rt = new RepositoryTree("1000", "ee", 5, listOfModule);

		Optional<RepositoryTree> tt = Optional.of(rt);
		
	    Mockito.when(svnService.findById("1000")).thenReturn(tt);
	    
	    List<SonarIssue> listIssue = new ArrayList<SonarIssue>();
	    
	    SonarIssue si = new SonarIssue("AWMSyGdYrxaAW8gnEUTg", "test", "SoLifeSatellite-SvnService", "Blocker", "Major", "open", "2010-05-05", "2015-06-06", "SoLifeSatellite-SvnService", "SoLifeSatellite-SvnService");
	    listIssue.add(si);
	    Mockito.when(sonarService.findBySeverity("Major")).thenReturn(listIssue);


	    
	    List<SeverityByModule> listIssuetest  = codeQualityService.severityByModule("Major");

	    SeverityByModule sbm = new SeverityByModule("SoLifeSatellite-SvnService", 100f);

	    
	    assertEquals(listIssuetest.get(0), sbm);

	    
	}

	@Test
	public void testViolationSonar() {


		Mockito.when(sonarService.countByTypeAndSeverity("BUG",SonarSeveritye.BLOCKER.name())).thenReturn(5);

		List<GloabalVioaltionSonar> list= codeQualityService.violationSonar();

		GloabalVioaltionSonar gs = new GloabalVioaltionSonar("BUG", 5, 0, 0, 0, 0);
		
	    assertEquals(list.get(0), gs);

	    
	}

	
}
