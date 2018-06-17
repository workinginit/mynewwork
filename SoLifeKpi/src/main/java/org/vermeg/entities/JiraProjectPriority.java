package org.vermeg.entities;

public class JiraProjectPriority {

	private JiraPriority priority;
	private int nbrPriority;
	
	public JiraProjectPriority() {
		super();
	}
	public JiraProjectPriority(JiraPriority priority, int nbrPriority) {
		super();
		this.priority = priority;
		this.nbrPriority = nbrPriority;
	}
	public JiraPriority getPriority() {
		return priority;
	}
	public void setPriority(JiraPriority priority) {
		this.priority = priority;
	}
	public int getNbrPriority() {
		return nbrPriority;
	}
	public void setNbrPriority(int nbrPriority) {
		this.nbrPriority = nbrPriority;
	}

}
