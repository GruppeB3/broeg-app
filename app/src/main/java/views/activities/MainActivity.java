package views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import dk.dtu.gruppeb3.broeg.app.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button newBrewButton, cleaningButton, profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileButton = findViewById(R.id.profile_button);
        newBrewButton = findViewById(R.id.broeg_frontpagebutton);
        cleaningButton = findViewById(R.id.cleaning_button);

        profileButton.setOnClickListener(this);
        newBrewButton.setOnClickListener(this);
        cleaningButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == newBrewButton) {

            Intent i = new Intent(this, NewRecipeActivity.class);
            startActivity(i);

        } else if (v == cleaningButton) {

            startActivity(new Intent(this, CleaningActivity.class));

        } else if (v == profileButton){

            Intent i = new Intent(this, MyProfile.class);
            startActivity(i);
        }

    }
}