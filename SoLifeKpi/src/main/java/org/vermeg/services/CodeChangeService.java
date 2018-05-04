package org.vermeg.services;

import java.util.List;

import org.vermeg.entities.NombreDeCommit;
import org.vermeg.entities.nbrCommit;

public interface CodeChangeService {
	
    List<NombreDeCommit> listcommit(); 
    List<nbrCommit> allListCommit(); 

}
