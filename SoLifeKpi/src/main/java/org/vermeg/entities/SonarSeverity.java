package org.vermeg.entities;

public class SonarSeverity {

	private float blocker;
	private float critical;
	private float major;
	private float minor;
	private float info;
	
	public SonarSeverity() {
		super();
	}
	public SonarSeverity(float blocker, float critical, float major, float minor, float info) {
		super();
		this.blocker = blocker;
		this.critical = critical;
		this.major = major;
		this.minor = minor;
		this.info = info;
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
	public float getMinor() {
		return minor;
	}
	public void setMinor(float minor) {
		this.minor = minor;
	}
	public float getInfo() {
		return info;
	}
	public void setInfo(float info) {
		this.info = info;
	}

}
