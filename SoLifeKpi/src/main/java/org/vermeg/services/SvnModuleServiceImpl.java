package org.vermeg.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vermeg.entities.RepositoryTree;
import org.vermeg.repository.SvnModuleRepository;


@Service
public class SvnModuleServiceImpl implements SvnModuleService{

	@Autowired
    private SvnModuleRepository svnRepository;

	
	@Override
	public Iterable<RepositoryTree> findAll() {
		return svnRepository.findAll();
	}


	@Override
	public Optional<RepositoryTree> findById(String id) {
		return svnRepository.findById(id);
	}

}
