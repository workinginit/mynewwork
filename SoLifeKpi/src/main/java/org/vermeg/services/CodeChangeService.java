package org.vermeg.services;

import java.text.ParseException;
import java.util.List;

import org.vermeg.entities.NombreDeCommit;
import org.vermeg.entities.nbrCommit;

public interface CodeChangeService {
	
    List<NombreDeCommit> listcommit(); 
    
    List<nbrCommit> allListCommit(String starDate, String endDate) throws ParseException; 
    
    long numberOfCommit(String module, List<String> list);

}
