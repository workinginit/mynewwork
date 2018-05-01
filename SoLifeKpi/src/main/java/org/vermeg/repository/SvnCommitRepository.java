package org.vermeg.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.vermeg.entities.SvnCommit;

public interface SvnCommitRepository extends ElasticsearchRepository<SvnCommit, String>{

}
