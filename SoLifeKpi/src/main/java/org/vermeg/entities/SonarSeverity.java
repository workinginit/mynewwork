package org.vermeg.entities;

public class SonarSeverity {

	private float blocker;
	private float critical;
	private float major;
	
	public SonarSeverity() {
		super();
	}
	public SonarSeverity(float blocker, float critical, float major) {
		super();
		this.blocker = blocker;
		this.critical = critical;
		this.major = major;
	}

	public float getBlocker() {
		return blocker;
	}
	public void setBlocker(float blocker) {
		this.blocker = blocker;
	}
	public float getCritical() {
		return critical;
	}
	public void setCritical(float critical) {
		this.critical = critical;
	}
	public float getMajor() {
		return major;
	}
	public void setMajor(float major) {
		this.major = major;
	}
}
