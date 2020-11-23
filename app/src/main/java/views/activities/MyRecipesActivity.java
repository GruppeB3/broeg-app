package views.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import dk.dtu.gruppeb3.broeg.app.R;

import models.Brew;



public class MyRecipesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("pref", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        final ArrayList<Brew> brews = gson.fromJson(prefs.getString("brews", "[]"), new TypeToken<ArrayList<Brew>>(){}.getType());

        if(getBrewFromIntent()!= null){
            brews.add(getBrewFromIntent());
            prefs.edit().putString("brews", gson.toJson(brews)).apply();
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.activity_myrecipes, R.id.listelement, brews){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                Brew brew = brews.get(position);
                TextView textview = view.findViewById(R.id.listelement);
                textview.setText(brew.getName());
                return view;

            }
        };

        ListView lv = new ListView(this);
        lv.setAdapter(arrayAdapter);
        setContentView(lv);

    }


    private Brew getBrewFromIntent (){
        String json = this.getIntent().getStringExtra("brew");
        if(json == null|| json.equals("")){
            return null;
        }
        return (new Gson()).fromJson(json, Brew.class);

    }
}