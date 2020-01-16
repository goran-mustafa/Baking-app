package com.example.bakingapp.mywidget.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Recipe {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("ingredients")
    private ArrayList<Ingredient> ingredients;

    public Recipe(int id, String name, ArrayList<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
