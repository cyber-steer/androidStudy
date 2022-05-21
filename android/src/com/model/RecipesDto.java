package com.model;

public class RecipesDto {
	String name;
	double proof;
	
	public RecipesDto() {
		
	}
	public RecipesDto(String name, double proof) {
		super();
		this.name = name;
		this.proof = proof;
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

	
	
	
}
