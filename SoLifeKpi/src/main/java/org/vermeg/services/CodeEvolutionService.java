package org.vermeg.services;

import java.util.List;

import org.vermeg.entities.CodeEvo;
import org.vermeg.entities.SvnCommit;

public interface CodeEvolutionService {

	List<CodeEvo> listOfEvolution(String module,String startDate, String endDate);
	
	CodeEvo projectEvolution();
	
	List<CodeEvo> listcodeevolution();
	
	int countSvnCommit(String module, List<SvnCommit> list);
}
