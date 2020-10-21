package views;

import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import dk.dtu.gruppeb3.broeg.app.R;
/**
 * This activity contains all the fragment from which you can customise your brew.
 */

public class NewBrew extends AppCompatActivity implements View.OnClickListener {
    private Button knap1, knap2, knap3, knap4, knap5, knap6;
    SharedPreferences prefs;

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_brew);


        knap1 = findViewById(R.id.GroundCoffeeAmount);
        knap2 = findViewById(R.id.GrindSize);
        knap3 = findViewById(R.id.BrewingTemperature);
        knap4 = findViewById(R.id.BloomWater);
        knap5 = findViewById(R.id.BloomTime);
        knap6 = findViewById(R.id.Save);


        knap1.setOnClickListener(this);
        knap2.setOnClickListener(this);
        knap3.setOnClickListener(this);
        knap4.setOnClickListener(this);
        knap5.setOnClickListener(this);
        knap6.setOnClickListener(this);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

    }

    @Override
    public void onClick(View ClickButton) {
        if (ClickButton == knap1) {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragmentindhold, new ChooseAmountOfCoffee_frag())
                    .addToBackStack(null)
                    .commit();
        } else if (ClickButton == knap2) {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragmentindhold, new ChooseGrindSize_frag())
                    .addToBackStack(null)
                    .commit();
        }

    }
}
