package org.vermeg.entities;

public class SeverityByModule {
	
	private String moduleName;
	private float value;
	
	public SeverityByModule() {
		super();
		// TODO Auto-generated constructor stub
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

}
