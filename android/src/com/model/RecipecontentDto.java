package com.model;

public class RecipecontentDto {
	String recipesname;
	String content;
	
	
	public RecipecontentDto(String recipesname, String content) {
		this.recipesname = recipesname;
		this.content = content;
	}
	public String getRecipesname() {
		return recipesname;
	}
	public void setRecipesname(String recipesname) {
		this.recipesname = recipesname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
	

}
