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
}
