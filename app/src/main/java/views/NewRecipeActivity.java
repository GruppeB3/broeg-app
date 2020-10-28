package views;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import dk.dtu.gruppeb3.broeg.app.R;

public class NewRecipeActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView knap1, knap2, knap3, knap4, knap5;
    private Button knap6;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newrecipe);

        knap1 = findViewById(R.id.groundCoffeeAmtBackground);
        knap2 = findViewById(R.id.grindSizeBackground);
        knap3 = findViewById(R.id.brewingTempBackground);
        knap4 = findViewById(R.id.bloomWaterBackground);
        knap5 = findViewById(R.id.bloomTimeBackground);
        knap6 = findViewById(R.id.saveButton);

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
                    .replace(R.id.fragment_contents, new ChooseAmountOfCoffee_frag())
                    .addToBackStack(null)
                    .commit();
        } else if (ClickButton == knap2) {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragment_contents, new ChooseGrindSize_frag())
                    .addToBackStack(null)
                    .commit();
        } else if (ClickButton == knap3) {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragment_contents, new ChooseBrewingTemperature_frag())
                    .addToBackStack(null)
                    .commit();
        } else if (ClickButton == knap4) {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragment_contents, new ChooseBloomWaterAmount_frag())
                    .addToBackStack(null)
                    .commit();
        } else if (ClickButton == knap5) {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragment_contents, new ChooseBloomTime_frag())
                    .addToBackStack(null)
                    .commit();
        } else if (ClickButton == knap6){
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragment_contents, new nameRecipe_frag())
                    .addToBackStack(null)
                    .commit();

        }

    }
}