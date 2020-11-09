package views;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import dk.dtu.gruppeb3.broeg.app.R;
import helpers.PreferenceHelper;

public class MyRecipesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrecipes);


        SharedPreferences preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        double coffeeAmount = PreferenceHelper.getDouble(preferences, "amountOfCoffee", "0");
        double bloomTime = PreferenceHelper.getDouble(preferences, "amountBloomTime", "0");
        double waterAmount = PreferenceHelper.getDouble(preferences, "amountBloomWater", "0");
        double brewingTemperature = PreferenceHelper.getDouble(preferences, "temperature", "0");
        String grindSize = PreferenceManager.getDefaultSharedPreferences(this).getString("grindSize", "defaultStringIfNothingFound");
        String brewName = PreferenceManager.getDefaultSharedPreferences(this).getString("recipeName", "defaultStringIfNothingFound");

        String [] names = PreferenceManager.getDefaultSharedPreferences(this).getString("recipeName","defaultStringIfNothingFound");
    }
}