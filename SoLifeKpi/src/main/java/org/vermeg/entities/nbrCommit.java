package org.vermeg.entities;

public class nbrCommit {

	private String namedModule;
	private long svnCommit;
	private int bug;
	private int task;
	private int story;
	private int epic;
		
	public nbrCommit() {
		super();
	}
	public nbrCommit(String namedModule, long svnCommit, int bug, int task, int story, int epic) {
		super();
		this.namedModule = namedModule;
		this.svnCommit = svnCommit;
		this.bug = bug;
		this.task = task;
		this.story = story;
		this.epic = epic;
	}
	public String getNamedModule() {
		return namedModule;
	}
	public void setNamedModule(String namedModule) {
		this.namedModule = namedModule;
	}
	public long getSvnCommit() {
		return svnCommit;
	}
	public void setSvnCommit(long svnCommit) {
		this.svnCommit = svnCommit;
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


}
