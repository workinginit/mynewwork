package org.vermeg.entities;

public class SonarSeverityModule {
	
	private SonarSeveritye severity;
	private float nbrSeverity;
	
	public SonarSeverityModule() {
		super();
	}	
	public SonarSeverityModule(SonarSeveritye severity, float nbrSeverity) {
		super();
		this.severity = severity;
		this.nbrSeverity = nbrSeverity;
	}
	public SonarSeveritye getSeverity() {
		return severity;
	}
	public void setSeverity(SonarSeveritye severity) {
		this.severity = severity;
	}
	public float getNbrSeverity() {
		return nbrSeverity;
	}
	public void setNbrSeverity(float nbrSeverity) {
		this.nbrSeverity = nbrSeverity;
	}
}
