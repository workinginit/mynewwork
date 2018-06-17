package org.vermeg.entities;

public class SeverityByModule {
	
	private String moduleName;
	private float value;
	
	public SeverityByModule() {
		super();
	}
	public SeverityByModule(String moduleName, float value) {
		super();
		this.moduleName = moduleName;
		this.value = value;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((moduleName == null) ? 0 : moduleName.hashCode());
		result = prime * result + Float.floatToIntBits(value);
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
		SeverityByModule other = (SeverityByModule) obj;
		if (moduleName == null) {
			if (other.moduleName != null)
				return false;
		} else if (!moduleName.equals(other.moduleName))
			return false;
		if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value))
			return false;
		return true;
	}
	
}
