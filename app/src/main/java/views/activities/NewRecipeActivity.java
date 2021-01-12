package views.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import dk.dtu.gruppeb3.broeg.app.R;
import helpers.PreferenceHelper;
import models.BrewBuilder;
import views.fragments.ChooseAmountOfCoffee_frag;
import views.fragments.ChooseBloomTime_frag;
import views.fragments.ChooseBloomWaterAmount_frag;
import views.fragments.ChooseBrewingTemperature_frag;
import views.fragments.ChooseGrindSize_frag;
import views.fragments.ChooseRatio_frag;
import views.fragments.ChooseTotalBrewTime_frag;
import views.fragments.NameRecipe_frag;

public class NewRecipeActivity extends BaseActivity implements View.OnClickListener {


    private ImageView groundCoffeeAmtImgView, grindSizeImgView, brewTempImgView, bloomWaterAmtImgView, bloomTimeImgView, totalTimeImgView, ratioImgView;
    private Button groundCoffeeAmtBtn, grindSizeBtn, brewTempBtn, bloomWaterAmtBtn, bloomTimeBtn, totalTimeBtn, ratioBtn, saveBtn;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentLayout(R.layout.activity_newrecipe);

        groundCoffeeAmtImgView = findViewById(R.id.groundCoffeeAmtBackground);
        grindSizeImgView = findViewById(R.id.grindSizeBackground);
        brewTempImgView = findViewById(R.id.brewingTempBackground);
        bloomWaterAmtImgView = findViewById(R.id.bloomWaterBackground);
        bloomTimeImgView = findViewById(R.id.bloomTimeBackground);
        totalTimeImgView = findViewById(R.id.totalBrewTimeBackground);
        ratioImgView = findViewById(R.id.coffeeWaterRatioBackground);

        groundCoffeeAmtBtn = findViewById(R.id.groundCoffeAmtText);
        grindSizeBtn = findViewById(R.id.grindSizeText);
        brewTempBtn = findViewById(R.id.brewingTempText);
        bloomWaterAmtBtn = findViewById(R.id.bloomWaterText);
        bloomTimeBtn = findViewById(R.id.bloomTimeText);
        totalTimeBtn = findViewById(R.id.totalBrewTimeText);
        ratioBtn = findViewById(R.id.coffeeWaterRatioText);
        saveBtn = findViewById(R.id.saveButton);

        groundCoffeeAmtImgView.setOnClickListener(this);
        grindSizeImgView.setOnClickListener(this);
        brewTempImgView.setOnClickListener(this);
        bloomWaterAmtImgView.setOnClickListener(this);
        bloomTimeImgView.setOnClickListener(this);
        groundCoffeeAmtBtn.setOnClickListener(this);
        grindSizeBtn.setOnClickListener(this);
        brewTempBtn.setOnClickListener(this);
        bloomWaterAmtBtn.setOnClickListener(this);
        bloomTimeBtn.setOnClickListener(this);
        totalTimeBtn.setOnClickListener(this);
        ratioBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);

        prefs = PreferenceHelper.getApplicationPreferences(this);

        BrewBuilder.reset();
    }

    @Override
    public void onClick(View ClickButton) {
        super.onClick(ClickButton);
        if (ClickButton == groundCoffeeAmtImgView || ClickButton == groundCoffeeAmtBtn) {
                    getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragment_contents, new ChooseAmountOfCoffee_frag())
                    .addToBackStack(null)
                    .commit();
        } else if (ClickButton == grindSizeImgView || ClickButton == grindSizeBtn) {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragment_contents, new ChooseGrindSize_frag())
                    .addToBackStack(null)
                    .commit();
        } else if (ClickButton == brewTempImgView || ClickButton == brewTempBtn) {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragment_contents, new ChooseBrewingTemperature_frag())
                    .addToBackStack(null)
                    .commit();
        } else if (ClickButton == bloomWaterAmtImgView || ClickButton == bloomWaterAmtBtn) {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragment_contents, new ChooseBloomWaterAmount_frag())
                    .addToBackStack(null)
                    .commit();
        } else if (ClickButton == bloomTimeImgView || ClickButton == bloomTimeBtn) {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragment_contents, new ChooseBloomTime_frag())
                    .addToBackStack(null)
                    .commit();
        } else if (ClickButton == totalTimeImgView || ClickButton == totalTimeBtn) {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragment_contents, new ChooseTotalBrewTime_frag())
                    .addToBackStack(null)
                    .commit();
        } else if (ClickButton == ratioImgView || ClickButton == ratioBtn) {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragment_contents, new ChooseRatio_frag())
                    .addToBackStack(null)
                    .commit();
        } else if (ClickButton == saveBtn) {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragment_contents, new NameRecipe_frag())
                    .addToBackStack(null)
                    .commit();

        }

    }

}