package com.model;

public class FormalitiesDto {
	String recipesname;
	String content;
	int num;
	
	
	public FormalitiesDto(String recipesname, String content, int num) {
		this.recipesname = recipesname;
		this.content = content;
		this.num = num;
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	
	
	
}
