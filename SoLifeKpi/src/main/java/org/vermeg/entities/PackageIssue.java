package org.vermeg.entities;

public class PackageIssue {
	
	private String nameModule;
	private String namePackage;
	private int bugP;
	private int vulnerabilityP;
	private int codeSmellP;
	private int totalnumber;
	
	public PackageIssue() {
		super();
	}
	public PackageIssue(String nameModule, String namePackage, int bugP, int vulnerabilityP, int codeSmellP,
			int totalnumber) {
		super();
		this.nameModule = nameModule;
		this.namePackage = namePackage;
		this.bugP = bugP;
		this.vulnerabilityP = vulnerabilityP;
		this.codeSmellP = codeSmellP;
		this.totalnumber = totalnumber;
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
	public int getBugP() {
		return bugP;
	}
	public void setBugP(int bugP) {
		this.bugP = bugP;
	}
	public int getVulnerabilityP() {
		return vulnerabilityP;
	}
	public void setVulnerabilityP(int vulnerabilityP) {
		this.vulnerabilityP = vulnerabilityP;
	}
	public int getCodeSmellP() {
		return codeSmellP;
	}
	public void setCodeSmellP(int codeSmellP) {
		this.codeSmellP = codeSmellP;
	}
	public int getTotalnumber() {
		return totalnumber;
	}
	public void setTotalnumber(int totalnumber) {
		this.totalnumber = totalnumber;
	}
}
