package views.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import dk.dtu.gruppeb3.broeg.app.R;

import models.Brew;



public class MyRecipesActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrecipes);

        listView=(ListView)findViewById(R.id.brewList);

        SharedPreferences prefs = getSharedPreferences("pref", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        ArrayList<Brew> brews = gson.fromJson(prefs.getString("brews", "[]"), new TypeToken<ArrayList<Brew>>(){}.getType());

        if(getBrewFromIntent()!= null){
            brews.add(getBrewFromIntent());
            prefs.edit().putString("brews", gson.toJson(brews)).apply();
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, brews);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }


    private Brew getBrewFromIntent (){
        String json = this.getIntent().getStringExtra("brew");
        if(json == null|| json.equals("")){
            return null;
        }
        return (new Gson()).fromJson(json, Brew.class);

    }
}