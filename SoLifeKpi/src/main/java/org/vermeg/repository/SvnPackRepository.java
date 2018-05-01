package org.vermeg.repository;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.vermeg.entities.PackageByModule;

public interface SvnPackRepository  extends ElasticsearchRepository <PackageByModule, String> {

	 PackageByModule findByModule(String module);

}
