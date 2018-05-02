package org.vermeg.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vermeg.entities.SonarIssue;
import org.vermeg.repository.SonarRepository;

@Service
public class SonarServiceImpl implements SonarService{
	
	@Autowired
    private SonarRepository sonarRepository;
	
	@Override
	public Iterable<SonarIssue> findAll() {
		return sonarRepository.findAll();
	}

	@Override
	public List<SonarIssue> findByComponentContaining(String component) {
		return sonarRepository.findByComponentContaining(component);
	}

	@Override
	public long totalIssue() {
		return sonarRepository.count();
	}

	@Override
	public List<SonarIssue> findBySeverity(String severity) {
		return sonarRepository.findBySeverity(severity);
	}

	@Override
	public List<SonarIssue> findByPack(String pack) {
		return sonarRepository.findByPack(pack);
	}

	@Override
	public List<SonarIssue> findByModuleAndPack(String module, String pack) {
		return sonarRepository.findByModuleAndPack(module, pack);
	}

	@Override
	public List<SonarIssue> findByModule(String module) {
		return sonarRepository.findByModule(module);
	}

	@Override
	public List<SonarIssue> findBySeverityIn(Collection<String> Severitys) {
		return sonarRepository.findBySeverityIn(Severitys);
	}

	@Override
	public List<SonarIssue> findByModuleAndSeverity(String module, String severity) {
		return sonarRepository.findByModuleAndSeverity(module, severity);
	}

	@Override
	public long countByModule(String module) {
		return sonarRepository.countByModule(module);
	}

}
