package org.vermeg.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.vermeg.entities.JenkinsBuild;

public interface JenkinsBuildRepository  extends ElasticsearchRepository<JenkinsBuild, String>{

}
