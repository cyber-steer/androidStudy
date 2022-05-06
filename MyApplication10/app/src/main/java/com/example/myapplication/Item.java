package com.example.myapplication;

public class Item {
    int imgSorce;
    int count;
    String imgName;

    public Item(int imgSorce, int count, String imgName) {
        this.imgSorce = imgSorce;
        this.count = count;
        this.imgName = imgName;
    }

    public int getImgSorce() {

        return imgSorce;
    }

    public void setImgSorce(int imgSorce) {

        this.imgSorce = imgSorce;
    }

    public int getCount() {

        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {

        this.imgName = imgName;
    }
}
