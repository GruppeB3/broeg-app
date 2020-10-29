package views.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import dk.dtu.gruppeb3.broeg.app.R;
import views.fragments.ChooseAmountOfCoffee_frag;
import views.fragments.ChooseBloomTime_frag;
import views.fragments.ChooseBloomWaterAmount_frag;
import views.fragments.ChooseBrewingTemperature_frag;
import views.fragments.ChooseGrindSize_frag;
import views.fragments.nameRecipe_frag;

public class NewRecipeActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView groundCoffeeAmtBtn, grindSizeBtn, brewTempBtn, bloomWaterAmtBtn, bloomTimeBtn;
    private Button saveBtn;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newrecipe);

        groundCoffeeAmtBtn = findViewById(R.id.groundCoffeeAmtBackground);
        grindSizeBtn = findViewById(R.id.grindSizeBackground);
        brewTempBtn = findViewById(R.id.brewingTempBackground);
        bloomWaterAmtBtn = findViewById(R.id.bloomWaterBackground);
        bloomTimeBtn = findViewById(R.id.bloomTimeBackground);
        saveBtn = findViewById(R.id.saveButton);

        groundCoffeeAmtBtn.setOnClickListener(this);
        grindSizeBtn.setOnClickListener(this);
        brewTempBtn.setOnClickListener(this);
        bloomWaterAmtBtn.setOnClickListener(this);
        bloomTimeBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

    }

    @Override
    public void onClick(View ClickButton) {
        if (ClickButton == groundCoffeeAmtBtn) {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragment_contents, new ChooseAmountOfCoffee_frag())
                    .addToBackStack(null)
                    .commit();
        } else if (ClickButton == grindSizeBtn) {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragment_contents, new ChooseGrindSize_frag())
                    .addToBackStack(null)
                    .commit();
        } else if (ClickButton == brewTempBtn) {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragment_contents, new ChooseBrewingTemperature_frag())
                    .addToBackStack(null)
                    .commit();
        } else if (ClickButton == bloomWaterAmtBtn) {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragment_contents, new ChooseBloomWaterAmount_frag())
                    .addToBackStack(null)
                    .commit();
        } else if (ClickButton == bloomTimeBtn) {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragment_contents, new ChooseBloomTime_frag())
                    .addToBackStack(null)
                    .commit();
        } else if (ClickButton == saveBtn){
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragment_contents, new nameRecipe_frag())
                    .addToBackStack(null)
                    .commit();

        }

    }
}