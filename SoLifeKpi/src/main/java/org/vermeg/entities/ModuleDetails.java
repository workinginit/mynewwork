package org.vermeg.entities;

public class ModuleDetails {

	private String namePack;
	private float perc;
	
	public ModuleDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ModuleDetails(String namePack, float perc) {
		super();
		this.namePack = namePack;
		this.perc = perc;
	}
	public String getNamePack() {
		return namePack;
	}
	public void setNamePack(String namePack) {
		this.namePack = namePack;
	}
	public float getPerc() {
		return perc;
	}
	public void setPerc(float perc) {
		this.perc = perc;
	}
	
}
