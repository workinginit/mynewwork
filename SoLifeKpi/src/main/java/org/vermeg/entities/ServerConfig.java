package org.vermeg.entities;

public class ServerConfig {
	
	private String path;
	private String username;
	private String password;
	private String svnUrl;
	private String jenkinsUrl;
	private String sonarUrl;
	private String jiraUrl;
	private String sonarToken;
	
	public ServerConfig() {
		super();
	}
	public ServerConfig(String path, String username, String password, String svnUrl, String jenkinsUrl,
			String sonarUrl, String jiraUrl, String sonarToken) {
		super();
		this.path = path;
		this.username = username;
		this.password = password;
		this.svnUrl = svnUrl;
		this.jenkinsUrl = jenkinsUrl;
		this.sonarUrl = sonarUrl;
		this.jiraUrl = jiraUrl;
		this.sonarToken = sonarToken;
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSvnUrl() {
		return svnUrl;
	}
	public void setSvnUrl(String svnUrl) {
		this.svnUrl = svnUrl;
	}
	public String getJenkinsUrl() {
		return jenkinsUrl;
	}
	public void setJenkinsUrl(String jenkinsUrl) {
		this.jenkinsUrl = jenkinsUrl;
	}
	public String getSonarUrl() {
		return sonarUrl;
	}
	public void setSonarUrl(String sonarUrl) {
		this.sonarUrl = sonarUrl;
	}
	public String getJiraUrl() {
		return jiraUrl;
	}
	public void setJiraUrl(String jiraUrl) {
		this.jiraUrl = jiraUrl;
	}
	public String getSonarToken() {
		return sonarToken;
	}
	public void setSonarToken(String sonarToken) {
		this.sonarToken = sonarToken;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "ServerConfig [username=" + username + ", password=" + password + ", svnUrl=" + svnUrl + ", jenkinsUrl="
				+ jenkinsUrl + ", sonarUrl=" + sonarUrl + ", jiraUrl=" + jiraUrl + ", sonarToken=" + sonarToken + "]";
	}

}
