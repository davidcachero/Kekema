package com.proyecto.kekema.model;

public class Recipe {
    private String name;
    private String description;
    private int image;
    private int fav;

    public Recipe(String description) {
        this.description = description;
    }

    public Recipe(String name, String description, int image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Recipe(String name, String description, int image, int fav) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.fav = fav;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getFav() {
        return fav;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }
}
