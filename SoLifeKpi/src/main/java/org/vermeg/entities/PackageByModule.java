package org.vermeg.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "svnpack", type = "packagebymodule")
public class PackageByModule {
	
	@Id
	private String module;
	private List<String> listPackage;
	
	public PackageByModule() {
		super();
	}
	public PackageByModule(String module, List<String> listPackage) {
		super();
		this.module = module;
		this.listPackage = listPackage;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public List<String> getListPackage() {
		return listPackage;
	}
	public void setListPackage(List<String> listPackage) {
		this.listPackage = listPackage;
	}
}
