package org.vermeg.entities;

public class GloabalVioaltionSonar {

	private String type;
	private int blocker;
	private int critical;
	private int major;
	private int minor;
	private int info;
	
	public GloabalVioaltionSonar() {
		super();
	}
	public GloabalVioaltionSonar(String type, int blocker, int critical, int major, int minor, int info) {
		super();
		this.type = type;
		this.blocker = blocker;
		this.critical = critical;
		this.major = major;
		this.minor = minor;
		this.info = info;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getBlocker() {
		return blocker;
	}
	public void setBlocker(int blocker) {
		this.blocker = blocker;
	}
	public int getCritical() {
		return critical;
	}
	public void setCritical(int critical) {
		this.critical = critical;
	}
	public int getMajor() {
		return major;
	}
	public void setMajor(int major) {
		this.major = major;
	}
	public int getMinor() {
		return minor;
	}
	public void setMinor(int minor) {
		this.minor = minor;
	}
	public int getInfo() {
		return info;
	}
	public void setInfo(int info) {
		this.info = info;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + blocker;
		result = prime * result + critical;
		result = prime * result + info;
		result = prime * result + major;
		result = prime * result + minor;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		GloabalVioaltionSonar other = (GloabalVioaltionSonar) obj;
		if (blocker != other.blocker)
			return false;
		if (critical != other.critical)
			return false;
		if (info != other.info)
			return false;
		if (major != other.major)
			return false;
		if (minor != other.minor)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	
}
