package org.vermeg.entities;

public class SonarTypeModule {
	
	private SonarType type; 
	private float nbrType;
	
	public SonarTypeModule() {
		super();
	}
	public SonarTypeModule(SonarType type, float nbrType) {
		super();
		this.type = type;
		this.nbrType = nbrType;
	}
	public SonarType getType() {
		return type;
	}
	public void setType(SonarType type) {
		this.type = type;
	}
	public float getNbrType() {
		return nbrType;
	}
	public void setNbrType(float nbrType) {
		this.nbrType = nbrType;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(nbrType);
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
		SonarTypeModule other = (SonarTypeModule) obj;
		if (Float.floatToIntBits(nbrType) != Float.floatToIntBits(other.nbrType))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SonarTypeModule [type=" + type + ", nbrType=" + nbrType + "]";
	}
	
	
}
