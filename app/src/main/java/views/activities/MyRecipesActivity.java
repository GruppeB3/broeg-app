package views.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

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

        ArrayList<Brew> brews = new ArrayList<Brew>();


        SharedPreferences preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        double coffeeAmount = PreferenceHelper.getDouble(preferences, "amountOfCoffee", "0");
        double bloomTime = PreferenceHelper.getDouble(preferences, "amountBloomTime", "0");
        double waterAmount = PreferenceHelper.getDouble(preferences, "amountBloomWater", "0");
        double brewingTemperature = PreferenceHelper.getDouble(preferences, "temperature", "0");
        String grindSize = PreferenceManager.getDefaultSharedPreferences(this).getString("grindSize", "defaultStringIfNothingFound");

        Gson gson = new Gson();
        String json = preferences.getString("recipeName", "defaultStringIfNothingFound");
        NameRecipe_frag brewName = gson.fromJson(json, NameRecipe_frag.class);
        brews.add(brewName);
    }
}