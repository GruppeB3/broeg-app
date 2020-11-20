package views.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import dk.dtu.gruppeb3.broeg.app.R;
import helpers.PreferenceHelper;
import models.Brew;
import views.fragments.NameRecipe_frag;


public class MyRecipesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrecipes);

        SharedPreferences prefs = getSharedPreferences("pref", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        ArrayList<Brew> brews = gson.fromJson(prefs.getString("brews", "[]"), new TypeToken<ArrayList<Brew>>(){}.getType());

        if(getBrewFromIntent()!= null){
            brews.add(getBrewFromIntent());
            prefs.edit().putString("brews", gson.toJson(brews)).apply();
        }
    }
    private Brew getBrewFromIntent (){
        String json = this.getIntent().getStringExtra("brew");
        if(json == null|| json.equals("")){
            return null;
        }
        return (new Gson()).fromJson(json, Brew.class);

    }

}