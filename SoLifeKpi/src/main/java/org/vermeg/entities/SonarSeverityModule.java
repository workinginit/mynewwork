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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(nbrSeverity);
		result = prime * result + ((severity == null) ? 0 : severity.hashCode());
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
		SonarSeverityModule other = (SonarSeverityModule) obj;
		if (Float.floatToIntBits(nbrSeverity) != Float.floatToIntBits(other.nbrSeverity))
			return false;
		if (severity != other.severity)
			return false;
		return true;
	}
	
}
