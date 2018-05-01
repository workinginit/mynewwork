package org.vermeg.entities;

import java.util.HashSet;

public class PackageByModule {

	private String module;
	private HashSet<String> listPackage;
	
	public PackageByModule() {
		super();
	}
	public PackageByModule(String module, HashSet<String> listPackage) {
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
	public HashSet<String> getListPackage() {
		return listPackage;
	}
	public void setListPackage(HashSet<String> listPackage) {
		this.listPackage = listPackage;
	}

}
