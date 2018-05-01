package org.vermeg.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vermeg.entities.PackageByModule;
import org.vermeg.repository.SvnPackRepository;

@Service
public class SvnPackServiceImpl implements SvnPackService {

	@Autowired
    private SvnPackRepository svnPackRepository;	
	
	@Override
	public PackageByModule findByModule(String module) {
		return svnPackRepository.findByModule(module);
	}

}
