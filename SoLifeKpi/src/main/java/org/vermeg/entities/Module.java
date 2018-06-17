package org.vermeg.entities;

import java.util.List;

public class Module {
	
	private String nameModule;
	private float totalIssueByType;
	private float totalIssuebySeverity;
	private List<SonarTypeModule> listSonarType;
	private List<SonarSeverityModule> listSonarSeverity;
	
	public Module() {
		super();
	}
	public Module(String nameModule, float totalIssueByType, float totalIssuebySeverity,
			List<SonarTypeModule> listSonarType, List<SonarSeverityModule> listSonarSeverity) {
		super();
		this.nameModule = nameModule;
		this.totalIssueByType = totalIssueByType;
		this.totalIssuebySeverity = totalIssuebySeverity;
		this.listSonarType = listSonarType;
		this.listSonarSeverity = listSonarSeverity;
	}
	public String getNameModule() {
		return nameModule;
	}
	public void setNameModule(String nameModule) {
		this.nameModule = nameModule;
	}
	public float getTotalIssueByType() {
		return totalIssueByType;
	}
	public void setTotalIssueByType(float totalIssueByType) {
		this.totalIssueByType = totalIssueByType;
	}
	public float getTotalIssuebySeverity() {
		return totalIssuebySeverity;
	}
	public void setTotalIssuebySeverity(float totalIssuebySeverity) {
		this.totalIssuebySeverity = totalIssuebySeverity;
	}
	public List<SonarTypeModule> getListSonarType() {
		return listSonarType;
	}
	public void setListSonarType(List<SonarTypeModule> listSonarType) {
		this.listSonarType = listSonarType;
	}
	public List<SonarSeverityModule> getListSonarSeverity() {
		return listSonarSeverity;
	}
	public void setListSonarSeverity(List<SonarSeverityModule> listSonarSeverity) {
		this.listSonarSeverity = listSonarSeverity;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listSonarSeverity == null) ? 0 : listSonarSeverity.hashCode());
		result = prime * result + ((listSonarType == null) ? 0 : listSonarType.hashCode());
		result = prime * result + ((nameModule == null) ? 0 : nameModule.hashCode());
		result = prime * result + Float.floatToIntBits(totalIssueByType);
		result = prime * result + Float.floatToIntBits(totalIssuebySeverity);
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
		Module other = (Module) obj;
		if (listSonarSeverity == null) {
			if (other.listSonarSeverity != null)
				return false;
		} else if (!listSonarSeverity.equals(other.listSonarSeverity))
			return false;
		if (listSonarType == null) {
			if (other.listSonarType != null)
				return false;
		} else if (!listSonarType.equals(other.listSonarType))
			return false;
		if (nameModule == null) {
			if (other.nameModule != null)
				return false;
		} else if (!nameModule.equals(other.nameModule))
			return false;
		if (Float.floatToIntBits(totalIssueByType) != Float.floatToIntBits(other.totalIssueByType))
			return false;
		if (Float.floatToIntBits(totalIssuebySeverity) != Float.floatToIntBits(other.totalIssuebySeverity))
			return false;
		return true;
	}
}
