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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(blocker);
		result = prime * result + Float.floatToIntBits(critical);
		result = prime * result + Float.floatToIntBits(major);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SonarSeverity other = (SonarSeverity) obj;
		if (Float.floatToIntBits(blocker) != Float.floatToIntBits(other.blocker))
			return false;
		if (Float.floatToIntBits(critical) != Float.floatToIntBits(other.critical))
			return false;
		if (Float.floatToIntBits(major) != Float.floatToIntBits(other.major))
			return false;
		return true;
	}
	
}
