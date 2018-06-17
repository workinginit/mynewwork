package org.vermeg.entities;

import java.util.List;

public class PackageIssue {
	
	private String namePackage;
	private float totalnumber;
	private List<SonarTypeModule> listSonarType;

	public PackageIssue() {
		super();
	}
	public PackageIssue(String namePackage, float totalnumber, List<SonarTypeModule> listSonarType) {
		super();
		this.namePackage = namePackage;
		this.totalnumber = totalnumber;
		this.listSonarType = listSonarType;
	}
	public String getNamePackage() {
		return namePackage;
	}
	public void setNamePackage(String namePackage) {
		this.namePackage = namePackage;
	}
	public float getTotalnumber() {
		return totalnumber;
	}
	public void setTotalnumber(float totalnumber) {
		this.totalnumber = totalnumber;
	}
	public List<SonarTypeModule> getListSonarType() {
		return listSonarType;
	}
	public void setListSonarType(List<SonarTypeModule> listSonarType) {
		this.listSonarType = listSonarType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listSonarType == null) ? 0 : listSonarType.hashCode());
		result = prime * result + ((namePackage == null) ? 0 : namePackage.hashCode());
		result = prime * result + Float.floatToIntBits(totalnumber);
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
		PackageIssue other = (PackageIssue) obj;
		if (listSonarType == null) {
			if (other.listSonarType != null)
				return false;
		} else if (!listSonarType.equals(other.listSonarType))
			return false;
		if (namePackage == null) {
			if (other.namePackage != null)
				return false;
		} else if (!namePackage.equals(other.namePackage))
			return false;
		if (Float.floatToIntBits(totalnumber) != Float.floatToIntBits(other.totalnumber))
			return false;
		return true;
	}
	
	
}
