package com.example.bakingapp.mywidget.Model;

import com.google.gson.annotations.SerializedName;

public class Ingredient {
    @SerializedName("ingredient")
    private String ingredient;

    public Ingredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
