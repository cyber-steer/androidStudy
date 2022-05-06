package com.example.midletest;

public class Item {
    int imgId;
    int count;
    String name;

    public Item(int imgId, int count, String name) {
        this.imgId = imgId;
        this.count = count;
        this.name = name;
    }

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
}
