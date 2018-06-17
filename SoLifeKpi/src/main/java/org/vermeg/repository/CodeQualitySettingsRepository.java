package org.vermeg.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.vermeg.entities.CodeQualitySettings;

public interface CodeQualitySettingsRepository extends ElasticsearchRepository<CodeQualitySettings, Long>{

}
