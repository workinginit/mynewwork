package org.vermeg.entities;

public class SonarIssue {
	
	private String key;
	private String projectName;
	private String component;
	private String type;
	private String severity;
	private String status;
	private String creationDate;
	private String updateDate;
	private String pack;
	private String module;
	
	public SonarIssue() {
		super();
	}
	
	public SonarIssue(String key, String projectName, String component, String type, String severity, String status,
			 String creationDate, String updateDate, String pack, String module) {
		super();
		this.key = key;
		this.projectName = projectName;
		this.component = component;
		this.type = type;
		this.severity = severity;
		this.status = status;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
		this.pack = pack;
		this.module = module;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	
}
