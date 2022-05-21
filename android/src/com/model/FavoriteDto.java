package com.model;

public class FavoriteDto {
	String recipesName;
	String userid;
	
	public FavoriteDto(String recipesName, String userid) {
		super();
		this.recipesName = recipesName;
		this.userid = userid;
	}
	public String getRecipesName() {
		return recipesName;
	}
	public void setRecipesName(String recipesName) {
		this.recipesName = recipesName;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
}
