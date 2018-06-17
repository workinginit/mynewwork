package org.vermeg.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "codeevolution", type = "commitevo")
public class CodeEvo {
	
	@Id
	private String id;
	private String module;
	private int scnCommit;
	private int sonarIssue;
	private String dateWeek;
	
	public CodeEvo() {
		super();
	}
	public CodeEvo(String id, int scnCommit, int sonarIssue, String dateWeek) {
		super();
		this.id = id;
		this.scnCommit = scnCommit;
		this.sonarIssue = sonarIssue;
		this.dateWeek = dateWeek;
	}
	public CodeEvo(int scnCommit, int sonarIssue, String dateWeek) {
		super();
		this.scnCommit = scnCommit;
		this.sonarIssue = sonarIssue;
		this.dateWeek = dateWeek;
	}
	public CodeEvo(String id, String module, int scnCommit, int sonarIssue, String dateWeek) {
		super();
		this.id = id;
		this.module = module;
		this.scnCommit = scnCommit;
		this.sonarIssue = sonarIssue;
		this.dateWeek = dateWeek;
	}
	public int getScnCommit() {
		return scnCommit;
	}
	public void setScnCommit(int scnCommit) {
		this.scnCommit = scnCommit;
	}
	public int getSonarIssue() {
		return sonarIssue;
	}
	public void setSonarIssue(int sonarIssue) {
		this.sonarIssue = sonarIssue;
	}
	public String getDateWeek() {
		return dateWeek;
	}
	public void setDateWeek(String dateWeek) {
		this.dateWeek = dateWeek;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}

}
