package com.example.week05tel;

import android.graphics.drawable.Drawable;

public class TelList {
    private Drawable image;
    private String name;
    private String tel;

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        System.out.println("==============================================================Tellist=============================================");
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
