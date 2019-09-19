package com.example.bakingapp.Models;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.FoodDetail;
import com.example.bakingapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class FoodDetailAdapter extends RecyclerView.Adapter<FoodDetailAdapter.ViewHolder> {

    Context context;
    ArrayList<Ingredient> ingredients;
    public FoodDetailAdapter(Context context, ArrayList<Ingredient> ingredients){
        this.context=context;
        if(ingredients==null){
            ingredients=new ArrayList<Ingredient>();
        }
        this.ingredients=ingredients;

    }
    @NonNull
    @Override
    public FoodDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.food_detail_item,parent,false);
        return new FoodDetailAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodDetailAdapter.ViewHolder holder, int position) {
      String data=position+1+"/"+ingredients.get(position).getQuantity()+" of "+
              ingredients.get(position).getMeasure()+" "+
              ingredients.get(position).getIngredient();
              holder.Name.setText(data);

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {



        TextView Name;

        public ViewHolder(View itemView) {

            super(itemView);

            Name=itemView.findViewById(R.id.ingredients);

        }
    }

}

