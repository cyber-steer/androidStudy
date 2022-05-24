package com.model;

public class MeterialDto {
	String recipesname;
	String meterialname;
	int voluem;
	
	public MeterialDto(String recipesname, String meterialname, int voluem) {
		super();
		this.recipesname = recipesname;
		this.meterialname = meterialname;
		this.voluem = voluem;
	}
	public String getRecipesname() {
		return recipesname;
	}
	public void setRecipesname(String recipesname) {
		this.recipesname = recipesname;
	}
	public String getMeterialname() {
		return meterialname;
	}
	public void setMeterialname(String meterialname) {
		this.meterialname = meterialname;
	}
	public int getVoluem() {
		return voluem;
	}
	public void setVoluem(int voluem) {
		this.voluem = voluem;
	}
}
