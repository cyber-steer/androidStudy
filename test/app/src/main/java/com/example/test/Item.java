package com.example.test;

public class Item {
    private int imgId;
    private int count;
    private String name;

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item(int imgId, int count, String name) {

        this.imgId = imgId;
        this.count = count;
        this.name = name;
    }
}
