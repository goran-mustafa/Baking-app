package com.example.bakingapp.mywidget;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;


import com.example.bakingapp.R;
import com.example.bakingapp.mywidget.Model.Ingredient;
import com.example.bakingapp.mywidget.Model.Recipe;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MyRemoteViewsFactory extends RemoteViewsService {
  public static boolean load=false;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new BakingRemotesViewFactory(getApplicationContext());
    }
}

class BakingRemotesViewFactory implements RemoteViewsService.RemoteViewsFactory {

    Context context;
    ArrayList<Recipe> recipes = new ArrayList<>();
    ArrayList<Ingredient> ingredients = new ArrayList<>();

    BakingRemotesViewFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        /*Retrofit retrofit = new Retrofit.Builder().baseUrl("https://d17h27t6h515a5.cloudfront.net")
                .addConverterFactory(GsonConverterFactory.create()).build();
        BakingApi bakingApi = retrofit.create(BakingApi.class);
        Call<ArrayList<Recipe>> call = bakingApi.getRecipe();
        call.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                recipes = response.body();
                ingredients = recipes.get(0).getIngredients();
            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Log.v("size", String.valueOf(ingredients.size()));*/

        StringBuilder JsonData = new StringBuilder();
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        try {
            String urlString = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
            Log.v("NETWORK_URL", urlString);
            URL url = new URL(urlString);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                JsonData.append(line);
                line = reader.readLine();
            }
            Log.v("AsyncTask", "Connected" + httpURLConnection.getResponseCode());
        } catch (Exception e) {
            e.printStackTrace();
            Log.v("AsyncTask", e.getMessage());
        } finally {
            if (httpURLConnection != null)
                httpURLConnection.disconnect();
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            Type listType = new TypeToken<ArrayList<Recipe>>() {
            }.getType();
            recipes = new Gson().fromJson(JsonData.toString(), listType);
            ingredients = recipes.get(0).getIngredients();

        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return ingredients.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.row);
        Ingredient ingredient = ingredients.get(position);
        remoteViews.setTextViewText(R.id.item_text_view, ingredient.getIngredient());
        Log.v("GV", ingredient.getIngredient());
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}