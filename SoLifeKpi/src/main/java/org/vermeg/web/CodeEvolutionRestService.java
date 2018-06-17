package org.vermeg.web;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.vermeg.entities.CodeChangeSettings;
import org.vermeg.entities.CodeEvo;
import org.vermeg.entities.RepositoryTree;
import org.vermeg.repository.CodeEvoRepository;
import org.vermeg.repository.SvnModuleRepository;
import org.vermeg.services.CodeEvolutionService;

@RestController
@CrossOrigin("*")
@RequestMapping("/codeevolution")
public class CodeEvolutionRestService {

	@Autowired
    private CodeEvolutionService codeEvolutionService;

	@Autowired
    private SvnModuleRepository svnModuleRepository;
	
	@Autowired
    private CodeEvoRepository codeEvoRepository;
	
	@RequestMapping(value="/listModule", method=RequestMethod.GET)
	public Optional<RepositoryTree> listModule() throws ParseException{
		return svnModuleRepository.findById("1000");
	}
	
	@RequestMapping(value="/Projectevolution", method=RequestMethod.GET)
	public CodeEvo Projectevolution() throws ParseException{
		return codeEvolutionService.projectEvolution();
	}
	
	@RequestMapping(value="/saving", method=RequestMethod.PUT)
	public CodeEvo saving(@RequestBody CodeEvo c) {
		System.out.println("ddde");
		return codeEvoRepository.save(c);
	}
	
	@RequestMapping(value="/lista", method=RequestMethod.GET)
	public List<CodeEvo> lista() throws ParseException{
		return codeEvolutionService.listOfEvolution("", "", "");
	}
	
	@RequestMapping(value="/getlist", method=RequestMethod.GET)
	public List<CodeEvo> getlist() throws ParseException{
		return codeEvolutionService.listcodeevolution();
	}
	
}
