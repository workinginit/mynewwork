package org.vermeg.web;

import java.text.ParseException;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.vermeg.entities.NombreDeCommit;
import org.vermeg.entities.nbrCommit;
import org.vermeg.services.CodeChangeService;

@RestController
@CrossOrigin("*")
public class CodeChangeRestService {
	
	@Autowired
	private CodeChangeService codeChangeService;
	
	@RequestMapping(value="/getCommit", method=RequestMethod.GET)
	public List<NombreDeCommit> getCommit(){
      
		return codeChangeService.listcommit(); 
	}
	
	@RequestMapping(value="/allListCommit", method=RequestMethod.GET)
	public List<nbrCommit> allListCommit(@PathParam("starDate") String starDate, @PathParam("endDate") String endDate) throws ParseException{
      
		return codeChangeService.allListCommit(starDate, endDate);
	}

}
