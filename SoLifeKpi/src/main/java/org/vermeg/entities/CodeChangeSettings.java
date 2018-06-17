package org.vermeg.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "codechangesettings", type = "settingschange")
public class CodeChangeSettings {

	@Id
	private Long id;
	private int info;
	private int alert;
	private String bugcolor;
	private String taskcolor;
	private String strotycolor;
	private String epiccolor;
	
	public CodeChangeSettings() {
		super();
	}
	public CodeChangeSettings(Long id, int info, int alert, String bugcolor, String taskcolor, String strotycolor,
			String epiccolor) {
		super();
		this.id = id;
		this.info = info;
		this.alert = alert;
		this.bugcolor = bugcolor;
		this.taskcolor = taskcolor;
		this.strotycolor = strotycolor;
		this.epiccolor = epiccolor;
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
	public String getBugcolor() {
		return bugcolor;
	}
	public void setBugcolor(String bugcolor) {
		this.bugcolor = bugcolor;
	}
	public String getTaskcolor() {
		return taskcolor;
	}
	public void setTaskcolor(String taskcolor) {
		this.taskcolor = taskcolor;
	}
	public String getStrotycolor() {
		return strotycolor;
	}
	public void setStrotycolor(String strotycolor) {
		this.strotycolor = strotycolor;
	}
	public String getEpiccolor() {
		return epiccolor;
	}
	public void setEpiccolor(String epiccolor) {
		this.epiccolor = epiccolor;
	}
}

