package com.model;

public class MeterialDto {
	String recipesname;
	String meterialname;
	String voluem;
	
	public MeterialDto(String recipesname, String meterialname, String voluem) {
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
	public String getVoluem() {
		return voluem;
	}
	public void setVoluem(String voluem) {
		this.voluem = voluem;
	}
}
