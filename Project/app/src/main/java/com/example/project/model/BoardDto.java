package com.example.project.model;

public class BoardDto {
    int id;
    String title;
    String nickName;
    String content;
    String date;

    public BoardDto() {
    }

    public BoardDto(int id, String title, String nickName, String content, String date) {
        this.id = id;
        this.title = title;
        this.nickName = nickName;
        this.content = content;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
