package org.vermeg.entities;

public class NombreDeCommit {

	private String namedModule;
	private int jiraIssue;
	private int svnCommit;
	private long sonarissue;
	
	public NombreDeCommit() {
		super();
	}
	public NombreDeCommit(String namedModule, int jiraIssue, int svnCommit) {
		super();
		this.namedModule = namedModule;
		this.jiraIssue = jiraIssue;
		this.svnCommit = svnCommit;
	}
	
	public NombreDeCommit(String namedModule, int jiraIssue, int svnCommit, long sonarissue) {
		super();
		this.namedModule = namedModule;
		this.jiraIssue = jiraIssue;
		this.svnCommit = svnCommit;
		this.sonarissue = sonarissue;
	}
	public long getSonarissue() {
		return sonarissue;
	}
	public void setSonarissue(long sonarissue) {
		this.sonarissue = sonarissue;
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
