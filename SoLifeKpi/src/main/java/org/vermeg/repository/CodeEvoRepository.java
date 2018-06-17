package org.vermeg.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.vermeg.entities.CodeEvo;

public interface CodeEvoRepository extends ElasticsearchRepository<CodeEvo, String> {

	 List<CodeEvo> findByDateWeekBetween(String startdate,String enddate);

	 List<CodeEvo> findByModuleAndDateWeekBetweenOrderByDateWeekAsc(String module,String startdate,String enddate);

}
