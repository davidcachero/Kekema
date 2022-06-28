package com.proyecto.kekema.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Recipe implements Parcelable {
    private String name;
    private String description;
    private String elaboration;
    private int image;
    private int fav;
    private String idVideo;
    private List<String> ingredients;

    public Recipe() {

    }

    public Recipe(String description) {
        this.description = description;
    }

    public Recipe(String name, String description, int image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Recipe(String name, String description, String elaboration, List<String> ingredients, int image, int fav, String idVideo) {
        this.name = name;
        this.description = description;
        this.elaboration = elaboration;
        this.ingredients = ingredients;
        this.image = image;
        this.fav = fav;
        this.idVideo = idVideo;
    }

    protected Recipe(Parcel in) {
        name = in.readString();
        description = in.readString();
        elaboration = in.readString();
        image = in.readInt();
        fav = in.readInt();
        idVideo = in.readString();
        ingredients = in.createStringArrayList();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

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

    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getElaboration() {
        return elaboration;
    }

    public void setElaboration(String elaboration) {
        this.elaboration = elaboration;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(elaboration);
        parcel.writeInt(image);
        parcel.writeInt(fav);
        parcel.writeString(idVideo);
        parcel.writeStringList(ingredients);
    }
}
