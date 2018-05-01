package org.vermeg.entities;

public class JiraIssue {
	
	private Long id;
	private String projectName;
	private String key;
	private String issueType;
	private String status;
	private String priority;
	private String resolution;
	private String assigne;
	private String reporter;
	private String creationDate;
	private String updateDate;
		
	public JiraIssue() {
		super();
	}
	public JiraIssue(Long id, String projectName, String key, String issueType, String status,
			String priority, String resolution, String assigne, String reporter, String creationDate, String updateDate
			) {
		super();
		this.id = id;
		this.projectName = projectName;
		this.key = key;
		this.issueType = issueType;
		this.status = status;
		this.priority = priority;
		this.resolution = resolution;
		this.assigne = assigne;
		this.reporter = reporter;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getAssigne() {
		return assigne;
	}
	public void setAssigne(String assigne) {
		this.assigne = assigne;
	}
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
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
}
