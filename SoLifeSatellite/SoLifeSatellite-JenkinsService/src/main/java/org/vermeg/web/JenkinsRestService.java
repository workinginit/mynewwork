package org.vermeg.web;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.vermeg.entities.JenkinsBuild;
import org.vermeg.services.JenkinsServiceImpl;

@RestController
@CrossOrigin("*")
public class JenkinsRestService {
	
	@Autowired
	private JenkinsServiceImpl js;
	
	@RequestMapping(value="/allbuilds", method=RequestMethod.GET)
	public List<JenkinsBuild> getBuilds(@PathParam("projectName") String projectName) throws URISyntaxException, IOException{
				
		return js.allJenkinsBuild(projectName);
	}
	
}
