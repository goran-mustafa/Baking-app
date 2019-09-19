package com.example.bakingapp.Api;

import com.example.bakingapp.Models.Food;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<ArrayList<Food>> getFoods();
}
