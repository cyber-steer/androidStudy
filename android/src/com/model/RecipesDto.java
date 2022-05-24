package com.model;

public class RecipesDto {
	String name;
	double proof;
	String base;
	
	public RecipesDto() {
		
	}
	public RecipesDto(String name, double proof, String base) {
		super();
		this.name = name;
		this.proof = proof;
		this.base = base;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getProof() {
		return proof;
	}

	public void setProof(double proof) {
		this.proof = proof;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	
}
