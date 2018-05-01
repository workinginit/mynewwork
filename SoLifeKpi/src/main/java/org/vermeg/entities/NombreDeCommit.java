package org.vermeg.entities;

public class NombreDeCommit {

	private String namedModule;
	private int jiraIssue;
	private int svnCommit;
	
	public NombreDeCommit() {
		super();
	}
	public NombreDeCommit(String namedModule, int jiraIssue, int svnCommit) {
		super();
		this.namedModule = namedModule;
		this.jiraIssue = jiraIssue;
		this.svnCommit = svnCommit;
	}
	public String getNamedModule() {
		return namedModule;
	}
	public void setNamedModule(String namedModule) {
		this.namedModule = namedModule;
	}
	public int getJiraIssue() {
		return jiraIssue;
	}
	public void setJiraIssue(int jiraIssue) {
		this.jiraIssue = jiraIssue;
	}
	public int getSvnCommit() {
		return svnCommit;
	}
	public void setSvnCommit(int svnCommit) {
		this.svnCommit = svnCommit;
	}
}
