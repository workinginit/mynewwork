package org.vermeg.unittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.vermeg.entities.ServerConfig;
import org.vermeg.services.SettingsServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class SettingsServiceUnitTest {

	@InjectMocks
	private SettingsServiceImpl settingsservice;
	
	ServerConfig sc = new ServerConfig("C:\\Users\\dell\\newgit", "hedi", "hunter", "http://localhost:4200", "http://localhost:4300", "http://localhost:4400", "http://localhost:4500", "http://localhost:4200");

	ServerConfig sc2 = new ServerConfig("C:\\Users\\dell\\Desktop\\testing", "hedi", "hunter", "http://localhost:4200", "http://localhost:4300", "http://localhost:4400", "http://localhost:4500", "http://localhost:4200");

	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testsavesettings() {

		assertTrue(settingsservice.saveSettings(sc));
		assertFalse(settingsservice.saveSettings(sc2));
		
	}
	
	@Test
	public void testsaveSvnSettings() {

		assertTrue(settingsservice.saveSvnSettings(sc));
		assertFalse(settingsservice.saveSvnSettings(sc2));

	}
	
	@Test
	public void testsaveSonarSettings() {

		assertTrue(settingsservice.saveSonarSettings(sc));
		assertFalse(settingsservice.saveSonarSettings(sc2));

	}
	
	@Test
	public void testsaveJiraSettings() {

		assertTrue(settingsservice.saveJiraSettings(sc));
		assertFalse(settingsservice.saveJiraSettings(sc2));

	}
	
	@Test
	public void testsavejenkinsSettings() {

		assertTrue(settingsservice.savejenkinsSettings(sc));
		assertTrue(settingsservice.savejenkinsSettings(sc));

	}

}
