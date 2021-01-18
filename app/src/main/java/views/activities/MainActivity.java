package views.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.ArrayList;

import controllers.BrewsController;
import dk.dtu.gruppeb3.broeg.app.R;
import helpers.PreferenceHelper;
import models.Brew;
import models.BrewBuilder;
import views.activities.community.profile.MyProfileActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    Button newBrewButton, cleaningButton, profileButton, myrecipesButton, quickButton;
    private SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentLayout(R.layout.activity_main);


//        profileButton = findViewById(R.id.profile_button);
        newBrewButton = findViewById(R.id.broeg_frontpagebutton);
        cleaningButton = findViewById(R.id.cleaning_button);
        myrecipesButton = findViewById(R.id.myrecipes_button);
        quickButton = findViewById(R.id.quickbrew_button);

//        profileButton.setOnClickListener(this);
        newBrewButton.setOnClickListener(this);
        cleaningButton.setOnClickListener(this);
        myrecipesButton.setOnClickListener(this);
        quickButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == newBrewButton) {

            startActivity(new Intent(this, NewRecipeActivity.class));

        } /*else if (v == cleaningButton) {

            startActivity(new Intent(this, CleaningActivity.class));

        } */
         else if (v == myrecipesButton){

            startActivity(new Intent(this, MyRecipesActivity.class));

        } else if (v == profileButton){

            startActivity(new Intent(this, MyProfileActivity.class));

        } else if (v == quickButton){

            prefs = PreferenceHelper.getApplicationPreferences(this);

            ArrayList<Brew> brews = BrewsController.getSystemBrews(prefs);
            if(brews.size() > 0){
                Brew brew = brews.get(0);
            } else {
                Brew brew = new Brew();
            }
            Intent i = new Intent(this, BrewingActivity.class);
            i.putExtra(BrewingActivity.SELECTED_BREW_IDENTIFIER,(new Gson()).toJson(brews));
            startActivity(i);
            finish();
        }

    }
}