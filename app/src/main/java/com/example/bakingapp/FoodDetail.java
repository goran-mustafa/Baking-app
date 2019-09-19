package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bakingapp.Models.Food;
import com.example.bakingapp.Models.FoodDetailAdapter;
import com.example.bakingapp.Models.Step;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FoodDetail extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    Food food;
    MaterialButton step;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        recyclerView=findViewById(R.id.ingredientsrc);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Intent intent = getIntent();
        Gson gson = new Gson();
        String resultString = intent.getExtras().getString("food");
        if (resultString != null) {
            Type type = new TypeToken<Food>() {
            }.getType();
            food = gson.fromJson(resultString, type);
            adapter=new FoodDetailAdapter(getApplicationContext(),food.getIngredients());
            recyclerView.setAdapter(adapter);
            step=findViewById(R.id.step);
            step.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    step();
                }
            });

        } else {
            Toast.makeText(this, "error ingredients", Toast.LENGTH_LONG).show();
        }
    }

    public void step() {
       Intent intent=new Intent(this,StepActivity.class);
        ArrayList<Step> result=food.getSteps();

        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Step> >(){}.getType();
        String resultString=gson.toJson(result,type);
        intent.putExtra("step",resultString);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
