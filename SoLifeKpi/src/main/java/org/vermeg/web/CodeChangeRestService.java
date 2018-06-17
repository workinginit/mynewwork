package org.vermeg.web;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.vermeg.entities.CodeChangeSettings;
import org.vermeg.entities.NombreDeCommit;
import org.vermeg.entities.ServerConfig;
import org.vermeg.entities.CommitByModule;
import org.vermeg.entities.JiraProjectPriority;
import org.vermeg.repository.CodeChangeSettingsRepository;
import org.vermeg.services.CodeChangeService;
import org.vermeg.services.SettingsService;

@RestController
@CrossOrigin("*")
public class CodeChangeRestService {
	
	@Autowired
	private CodeChangeService codeChangeService;
	
	@Autowired
    private CodeChangeSettingsRepository codeChangeSettingsRepository;
	
	@Autowired
	private SettingsService settingsService;
	
	@RequestMapping(value="/getCommit", method=RequestMethod.GET)
	public List<NombreDeCommit> getCommit(){
      
		return codeChangeService.listcommit(); 
	}
	
	@RequestMapping(value="/allListCommit", method=RequestMethod.GET)
	public List<CommitByModule> allListCommit(@PathParam("starDate") String starDate, @PathParam("endDate") String endDate) throws ParseException{
      
		return codeChangeService.allListCommit(starDate, endDate);
	}
	
	@RequestMapping(value="/getCodeChangeSettings", method=RequestMethod.GET)
	public Optional<CodeChangeSettings> getCodeChangeSettings() {
      
		return codeChangeSettingsRepository.findById(100L);
	}
	
	@RequestMapping(value="/updateCodeChangeSettings", method=RequestMethod.PUT)
	public CodeChangeSettings updateCodeChangeSettings(@RequestBody CodeChangeSettings c) {
		c.setId(100L);
		return codeChangeSettingsRepository.save(c);
	}
	
	@RequestMapping(value="/savesettings", method=RequestMethod.POST)
	public boolean savesettings(@RequestBody ServerConfig c) {
		System.out.println("ded"+c.toString());
		return settingsService.saveSettings(c);
	}
	
	@RequestMapping(value="/listJiraPriority", method=RequestMethod.GET)
	public List<JiraProjectPriority> listJiraPriority(@PathParam("jiratype") String jiratype) throws ParseException{
      
		return codeChangeService.listOfJiraProjectPriority(jiratype);
	}

}
