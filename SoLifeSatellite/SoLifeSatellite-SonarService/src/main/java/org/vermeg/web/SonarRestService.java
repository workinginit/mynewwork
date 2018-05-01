package org.vermeg.web;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.vermeg.entities.SonarIssue;
import org.vermeg.services.SonarIssueService;

@RestController
@CrossOrigin("*")
public class SonarRestService {
	@Autowired
	private SonarIssueService sonarIssueService;
	
	@RequestMapping(value="/allissue", method=RequestMethod.GET)
	public List<SonarIssue> getIssue(@PathParam("ProjectName") String ProjectName){
		
		return sonarIssueService.getIssue(ProjectName);
		
	}
	
}
