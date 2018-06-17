package org.vermeg.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "codequalitysettings", type = "settingsquality")
public class CodeQualitySettings {

	@Id
	private Long id;
	private int info;
	private int alert;
	
	public CodeQualitySettings() {
		super();
	}
	public CodeQualitySettings(Long id, int info, int alert) {
		super();
		this.id = id;
		this.info = info;
		this.alert = alert;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getInfo() {
		return info;
	}
	public void setInfo(int info) {
		this.info = info;
	}
	public int getAlert() {
		return alert;
	}
	public void setAlert(int alert) {
		this.alert = alert;
	}
}
