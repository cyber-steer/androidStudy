package com.model;

public class BoardDto {
	int id;
	String title;
	String content;
	String nickName;
	String date;
	
	public BoardDto() {
		super();
	}
	public BoardDto(int id, String title, String content, String nickName, String date) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.nickName = nickName;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
