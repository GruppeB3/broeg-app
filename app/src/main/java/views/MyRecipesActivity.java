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


        SharedPreferences preferencesAmountCoffee = getSharedPreferences("pref", Context.MODE_PRIVATE);
        double coffeeAmount = PreferenceHelper.getDouble(preferencesAmountCoffee, "amountOfCoffee", "0");

        SharedPreferences preferencesBloomTime = getSharedPreferences("pref", Context.MODE_PRIVATE);
        double bloomTime = PreferenceHelper.getDouble(preferencesBloomTime, "amountBloomTime", "0");

        SharedPreferences preferencesAmountWater = getSharedPreferences("pref", Context.MODE_PRIVATE);
        double waterAmount = PreferenceHelper.getDouble(preferencesAmountWater, "amountBloomWater", "0");

        SharedPreferences preferencesTemperature = getSharedPreferences("pref", Context.MODE_PRIVATE);
        double brewingTemperature = PreferenceHelper.getDouble(preferencesTemperature, "temperature", "0");

        String grindSize = PreferenceManager.getDefaultSharedPreferences(this).getString("GrindSize", "defaultStringIfNothingFound");
    }
}