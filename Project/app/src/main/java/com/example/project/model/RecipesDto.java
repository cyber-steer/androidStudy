package com.example.project.model;

import android.graphics.drawable.Drawable;

public class RecipesDto {
    String name;
    String proof;
    boolean favorite;

    public RecipesDto(String name, String proof, boolean favorite) {
        this.name = name;
        this.proof = proof;
        this.favorite = favorite;
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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
