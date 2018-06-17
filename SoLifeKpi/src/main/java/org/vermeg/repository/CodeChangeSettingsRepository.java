package org.vermeg.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.vermeg.entities.CodeChangeSettings;

public interface CodeChangeSettingsRepository extends ElasticsearchRepository<CodeChangeSettings, Long>{

}
