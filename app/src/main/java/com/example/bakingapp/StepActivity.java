package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.bakingapp.Models.Food;
import com.example.bakingapp.Models.Step;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class StepActivity extends AppCompatActivity implements VideoFragment.OnFragmentInteractionListener {

    ArrayList<Step> steps;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        Intent intent = getIntent();
        Gson gson = new Gson();
        String resultString = intent.getExtras().getString("step");
        if (resultString != null) {
            Type type = new TypeToken<ArrayList<Step>>() {}.getType();

            steps = gson.fromJson(resultString, type);
            boolean istablet=getResources().getBoolean(R.bool.isTablet);
            if(istablet){
                recyclerView=findViewById(R.id.tablet_recycler);
                layoutManager=new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutManager);
                adapter=new StepsAdapter(this,steps);
                recyclerView.setAdapter(adapter);
                playVidioOnTablet(counter);
            }
            else{
                playVideo(counter);
            }

        }
    }

    public void playVidioOnTablet(int videoNumber) {
        String mVideoUri = steps.get(videoNumber).getVideoURL();
        Bundle bundle=new Bundle();
        VideoFragment videoFragment = new VideoFragment();
        bundle.putString("VIDEO_URI", mVideoUri);
        bundle.putString("VIDEO_DESC", steps.get(videoNumber).getDescription());
        bundle.putString("VIDEO_THUMB", steps.get(videoNumber).getThumbnailURL());
        bundle.putString("VIDEO_SHORTDESC", steps.get(videoNumber).getShortDescription());
        videoFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.player_container, videoFragment)
                .commit();
    }

    public void playVideo(int videoNumber){
        String mVideoUri = steps.get(videoNumber).getVideoURL();
       Bundle bundle=new Bundle();
        VideoFragment videoFragment = new VideoFragment();
        bundle.putString("VIDEO_URI", mVideoUri);
        bundle.putString("VIDEO_DESC", steps.get(videoNumber).getDescription());
        bundle.putString("VIDEO_THUMB", steps.get(videoNumber).getThumbnailURL());
        bundle.putString("VIDEO_SHORTDESC", steps.get(videoNumber).getShortDescription());
        videoFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.player_container, videoFragment)
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void pre(View view) {
        if(counter>0){
            counter--;
            playVideo(counter);
        }
    }

    public void next(View view) {
        if(counter<steps.size()-1){
            counter++;
            playVideo(counter);
        }
    }
}
