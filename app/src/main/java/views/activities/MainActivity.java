package views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dk.dtu.gruppeb3.broeg.app.R;
import views.activities.community.profile.MyProfileActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    Button newBrewButton, cleaningButton, profileButton, myrecipesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentLayout(R.layout.activity_main);

        profileButton = findViewById(R.id.profile_button);
        newBrewButton = findViewById(R.id.broeg_frontpagebutton);
        cleaningButton = findViewById(R.id.cleaning_button);
        myrecipesButton = findViewById(R.id.myrecipes_button);

        profileButton.setOnClickListener(this);
        newBrewButton.setOnClickListener(this);
        cleaningButton.setOnClickListener(this);
        myrecipesButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == newBrewButton) {

            startActivity(new Intent(this, NewRecipeActivity.class));

        } else if (v == cleaningButton) {

            startActivity(new Intent(this, CleaningActivity.class));

        } else if (v == myrecipesButton){

            startActivity(new Intent(this, MyRecipesActivity.class));

        } else if (v == profileButton){

            startActivity(new Intent(this, MyProfileActivity.class));

        }
    }
}