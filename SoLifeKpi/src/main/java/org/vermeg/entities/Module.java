package org.vermeg.entities;

public class Module {
	
	private String nameModule;
	private int bug;
	private int vulnerability;
	private int codeSmell;
	private float percentage;
	private int blocker;
	private int critical;
	private int major;
	private float totalIssueBySeverity;
	
	public Module() {
		super();
	}
	public Module(String nameModule, int bug, int vulnerability, int codeSmell, float percentage, int blocker,
			int critical, int major, float totalIssueBySeverity) {
		super();
		this.nameModule = nameModule;
		this.bug = bug;
		this.vulnerability = vulnerability;
		this.codeSmell = codeSmell;
		this.percentage = percentage;
		this.blocker = blocker;
		this.critical = critical;
		this.major = major;
		this.totalIssueBySeverity = totalIssueBySeverity;
	}
	public float getPercentage() {
		return percentage;
	}
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
	public String getNameModule() {
		return nameModule;
	}
	public void setNameModule(String nameModule) {
		this.nameModule = nameModule;
	}
	public int getBug() {
		return bug;
	}
	public void setBug(int bug) {
		this.bug = bug;
	}
	public int getVulnerability() {
		return vulnerability;
	}
	public void setVulnerability(int vulnerability) {
		this.vulnerability = vulnerability;
	}
	public int getCodeSmell() {
		return codeSmell;
	}
	public void setCodeSmell(int codeSmell) {
		this.codeSmell = codeSmell;
	}
	public int getBlocker() {
		return blocker;
	}
	public void setBlocker(int blocker) {
		this.blocker = blocker;
	}
	public int getCritical() {
		return critical;
	}
	public void setCritical(int critical) {
		this.critical = critical;
	}
	public int getMajor() {
		return major;
	}
	public void setMajor(int major) {
		this.major = major;
	}
	public float getTotalIssueBySeverity() {
		return totalIssueBySeverity;
	}
	public void setTotalIssueBySeverity(int totalIssueBySeverity) {
		this.totalIssueBySeverity = totalIssueBySeverity;
	}
	
}
