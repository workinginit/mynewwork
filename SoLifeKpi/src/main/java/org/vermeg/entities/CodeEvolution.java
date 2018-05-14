package org.vermeg.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "codeevolution", type = "commitevo")
public class CodeEvolution {
	@Id
	private String _id;
	private String namedModule;
	private int svnCommit;
	private int story;
	private int epic;
	private int bug;
	private int task;
	private String dateWeek;
	
	public CodeEvolution() {
		super();
	}
	public CodeEvolution(String _id, String namedModule, int svnCommit, int story, int epic, int bug, int task,
			String dateWeek) {
		super();
		this._id = _id;
		this.namedModule = namedModule;
		this.svnCommit = svnCommit;
		this.story = story;
		this.epic = epic;
		this.bug = bug;
		this.task = task;
		this.dateWeek = dateWeek;
	}

	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public int getStory() {
		return story;
	}
	public void setStory(int story) {
		this.story = story;
	}
	public int getEpic() {
		return epic;
	}
	public void setEpic(int epic) {
		this.epic = epic;
	}
	public int getBug() {
		return bug;
	}
	public void setBug(int bug) {
		this.bug = bug;
	}
	public int getTask() {
		return task;
	}
	public void setTask(int task) {
		this.task = task;
	}
	public int getSvnCommit() {
		return svnCommit;
	}
	public void setSvnCommit(int svnCommit) {
		this.svnCommit = svnCommit;
	}
	public String getDateWeek() {
		return dateWeek;
	}
	public void setDateWeek(String dateWeek) {
		this.dateWeek = dateWeek;
	}
	public String getNamedModule() {
		return namedModule;
	}
	public void setNamedModule(String namedModule) {
		this.namedModule = namedModule;
	}
	
}
