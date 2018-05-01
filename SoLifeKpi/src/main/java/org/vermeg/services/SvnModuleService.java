package org.vermeg.services;


import java.util.Optional;

import org.vermeg.entities.RepositoryTree;


public interface SvnModuleService {
	
    Iterable<RepositoryTree> findAll();
    
    Optional<RepositoryTree> findById(String id);


}
