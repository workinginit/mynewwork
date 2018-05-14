package org.vermeg.entities;

public class test {

	private String module;
	private int totalcommit;
	private int totalticket;
	
	public test() {
		super();
	}
	public test(String module, int totalcommit, int totalticket) {
		super();
		this.module = module;
		this.totalcommit = totalcommit;
		this.totalticket = totalticket;
	}

	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public int getTotalcommit() {
		return totalcommit;
	}
	public void setTotalcommit(int totalcommit) {
		this.totalcommit = totalcommit;
	}
	public int getTotalticket() {
		return totalticket;
	}
	public void setTotalticket(int totalticket) {
		this.totalticket = totalticket;
	}
	
	
}
