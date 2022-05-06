package com.example.week09;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Card {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String tel;
    private String email;

    public Card(String name, String tel, String email) {
        this.name = name;
        this.tel = tel;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
