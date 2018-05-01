package org.vermeg.services;

import java.net.URI;
import java.net.URISyntaxException;

import com.offbytwo.jenkins.JenkinsServer;

public class ConnexionJenkinsService {
	
	private static JenkinsServer jenkinsServer = null;

	private ConnexionJenkinsService(String url, String userName, String password) {
		
		try {
			 jenkinsServer = new JenkinsServer(new URI(url), userName, password);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}	
		
	}
	
	public static JenkinsServer getInstance(String url, String userName, String password) {
		if (jenkinsServer==null) 
		{
			new ConnexionJenkinsService(url,userName,password);			
		}
		   return jenkinsServer;
	}

}
