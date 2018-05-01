package org.vermeg.web;

import java.io.IOException;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.vermeg.entities.JiraIssue;
import org.vermeg.services.JiraService;

@RestController
@CrossOrigin("*")
public class JiraRestService {
	
	@Autowired
	private JiraService jiraService;
	
	@RequestMapping(value="/jiraissues", method=RequestMethod.GET)
	public List<JiraIssue> getcommits(@PathParam("project") String project) throws IOException {
		return jiraService.allofjira(project);
	}

}
