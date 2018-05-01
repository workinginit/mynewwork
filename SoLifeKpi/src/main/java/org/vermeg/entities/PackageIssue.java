package org.vermeg.entities;

public class PackageIssue {
	
	private String nameModule;
	private String namePackage;
	private int number;
	private int total;
	
	public PackageIssue() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PackageIssue(String nameModule, String namePackage, int number, int total) {
		super();
		this.nameModule = nameModule;
		this.namePackage = namePackage;
		this.number = number;
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
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

}
