package org.vermeg.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.vermeg.entities.CodeEvolution;

public interface CodeEvolutionRepository extends ElasticsearchRepository<CodeEvolution, String>{
	 List<CodeEvolution> findByNamedModule(String namedModule);
}
