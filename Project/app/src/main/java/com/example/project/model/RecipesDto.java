package com.example.project.model;

import android.graphics.drawable.Drawable;

public class RecipesDto {
    String name;
    String proof;

    public RecipesDto(String name, String proof) {
        this.name = name;
        this.proof = proof;
    }

    public RecipesDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProof() {
        return proof;
    }

    public void setProof(String proof) {
        this.proof = proof;
    }

}
