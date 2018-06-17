package org.vermeg.services;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.springframework.stereotype.Service;
import org.vermeg.entities.ServerConfig;

@Service
public class SettingsServiceImpl implements SettingsService {

	@Override
	public boolean saveSettings(ServerConfig sc) {
		boolean test = false;
		
		if(saveSvnSettings(sc) == true && savejenkinsSettings(sc)== true && saveSonarSettings(sc) == true && saveJiraSettings(sc)== true ) {
			test =  true;
		}
		else {
			test = false;
		}
		
		return test;
	}

	@Override
	public boolean saveSvnSettings(ServerConfig sc) {
				
    	final Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream(sc.getPath().replaceAll("\\\\", "\\\\\\\\")+"\\SoLifeSatellite\\SoLifeSatellite-SvnService\\src\\main\\resources\\application.properties");

			// set the properties value
			prop.setProperty("spring.main.banner-mode", "off");
			prop.setProperty("server.port", "8084");
			prop.setProperty("repository.url", sc.getSvnUrl());
			prop.setProperty("repository.userName", sc.getUsername());
			prop.setProperty("repository.password", encrypt(sc.getPassword()));

			// save properties to project root folder
			prop.store(output, null);
			return true;
		} catch (final IOException io) {
			io.printStackTrace();
			return false;

		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

	@Override
	public boolean saveSonarSettings(ServerConfig sc) {
    	final Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream(sc.getPath().replaceAll("\\\\", "\\\\\\\\")+"\\SoLifeSatellite\\SoLifeSatellite-SonarService\\src\\main\\resources\\application.properties");

			// set the properties value
			prop.setProperty("spring.main.banner-mode", "off");
			prop.setProperty("server.port", "8087");
			prop.setProperty("sonar.url", sc.getSonarUrl());
			prop.setProperty("sonar.userName", sc.getUsername());
			prop.setProperty("sonar.password", encrypt(sc.getPassword()));
			prop.setProperty("sonar.token", sc.getSonarToken());
			// save properties to project root folder
			prop.store(output, null);
			return true;
		} catch (final IOException io) {
			io.printStackTrace();
			return false;

		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	@Override
	public boolean saveJiraSettings(ServerConfig sc) {
    	final Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream(sc.getPath().replaceAll("\\\\", "\\\\\\\\")+"\\SoLifeSatellite\\SoLifeSatellite-JiraService\\src\\main\\resources\\application.properties");

			// set the properties value
			prop.setProperty("spring.main.banner-mode", "off");
			prop.setProperty("server.port", "8082");
			prop.setProperty("jira.url", sc.getJiraUrl());
			prop.setProperty("jira.userName", sc.getUsername());
			prop.setProperty("jira.password", encrypt(sc.getPassword()));

			// save properties to project root folder
			prop.store(output, null);
			return true;
		} catch (final IOException io) {
			io.printStackTrace();
			return false;

		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	@Override
	public boolean savejenkinsSettings(ServerConfig sc) {
    	final Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream(sc.getPath().replaceAll("\\\\", "\\\\\\\\")+"\\SoLifeSatellite\\SoLifeSatellite-JenkinsService\\src\\main\\resources\\application.properties");

			// set the properties value
			prop.setProperty("spring.main.banner-mode", "off");
			prop.setProperty("server.port", "8085");
			prop.setProperty("jenkins.url", sc.getJenkinsUrl());
			prop.setProperty("jenkins.userName", sc.getUsername());
			prop.setProperty("jenkins.password", encrypt(sc.getPassword()));

			// save properties to project root folder
			prop.store(output, null);
			return true;
		} catch (final IOException io) {
			io.printStackTrace();
			return false;

		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	   public String encrypt(String password){
	        String crypte= "";
	        for (int i=0; i<password.length();i++)  {
	            int c=password.charAt(i)^48;  
	            crypte=crypte+(char)c; 
	        }
	        return crypte;
	    }
	
	

}
