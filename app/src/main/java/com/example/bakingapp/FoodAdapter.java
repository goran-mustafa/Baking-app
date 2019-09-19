package com.example.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.Models.Food;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    Context context;
    ArrayList<Food> foods;
    public FoodAdapter(Context context, ArrayList<Food> foods){
        this.context=context;
        if(foods==null){
            foods=new ArrayList<Food>();
        }
        this.foods=foods;

    }
    @NonNull
    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new FoodAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder, int position) {

        holder.Name.setText(foods.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return foods.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {



        Button Name;

        public ViewHolder(View itemView) {

            super(itemView);

              Name=itemView.findViewById(R.id.name);
              Name.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      Food result=foods.get(getAdapterPosition());
                      Intent intent=new Intent(context,FoodDetail.class);
                      Gson gson=new Gson();
                      Type type=new TypeToken<Food>(){}.getType();
                      String resultString=gson.toJson(result,type);
                      intent.putExtra("food",resultString);
                      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                      context.startActivity(intent);
                  }
              });
        }
    }

}

