package org.vermeg.entities;

public class PackageIssue {
	
	private String nameModule;
	private String namePackage;
	private int number;
	private long total;
	
	public PackageIssue() {
		super();
	}
	public PackageIssue(String nameModule, String namePackage, int number, long total) {
		super();
		this.nameModule = nameModule;
		this.namePackage = namePackage;
		this.number = number;
		this.total = total;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public String getNameModule() {
		return nameModule;
	}
	public void setNameModule(String nameModule) {
		this.nameModule = nameModule;
	}
	public String getNamePackage() {
		return namePackage;
	}
	public void setNamePackage(String namePackage) {
		this.namePackage = namePackage;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

}
