package com.example.bakingapp;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new  ActivityTestRule<>(MainActivity.class);


    @Before
    public void Before(){
    }



    @Test
    public void name() {

       while(!MainActivity.loaded){

       }
        Espresso.onView(ViewMatchers.withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
        Espresso.onView(ViewMatchers.withId(R.id.step)).perform(ViewActions.click());

    }
}