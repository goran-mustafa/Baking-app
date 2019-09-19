package com.example.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bakingapp.Models.Step;


import java.util.ArrayList;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {

    StepActivity activity;
    ArrayList<Step> steps;
    public StepsAdapter(StepActivity activity, ArrayList<Step> steps){
        this.activity=activity;
        if(steps==null){
            steps=new ArrayList<>();
        }
        this.steps =steps;

    }
    @NonNull
    @Override
    public StepsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(activity).inflate(R.layout.step_item,parent,false);
        return new StepsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Name.setText(steps.get(position).getShortDescription());

    }


    @Override
    public int getItemCount() {
        return steps.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {



        Button Name;

        public ViewHolder(View itemView) {

            super(itemView);

            Name=itemView.findViewById(R.id.tv_step_description);
            Name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.playVidioOnTablet(getAdapterPosition());
                }
            });
        }
    }

}
