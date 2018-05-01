package org.vermeg.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.vermeg.entities.RepositoryTree;


public interface SvnModuleRepository extends ElasticsearchRepository<RepositoryTree, String>{

}
