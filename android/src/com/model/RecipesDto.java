package com.model;

public class RecipesDto {
	String name;
	double proof;
	boolean favorite;
	
	public RecipesDto() {
		
	}
	public RecipesDto(String name, double proof, boolean favorite) {
		super();
		this.name = name;
		this.proof = proof;
		this.favorite = favorite;
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

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}
	
	
	
}
