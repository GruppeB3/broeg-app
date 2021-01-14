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

import models.Brew;

public class NewRecipeActivity extends BaseActivity implements View.OnClickListener {


    private ImageView groundCoffeeAmtImgView, grindSizeImgView, brewTempImgView, bloomWaterAmtImgView, bloomTimeImgView, totalTimeImgView, ratioImgView;
    private Button groundCoffeeAmtBtn, grindSizeBtn, brewTempBtn, bloomWaterAmtBtn, bloomTimeBtn, totalTimeBtn, ratioBtn, saveBtn;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentLayout(R.layout.activity_newrecipe);

        Brew brew = BrewBuilder.getInstance().get();

        groundCoffeeAmtImgView = findViewById(R.id.groundCoffeeAmtBackground);
        grindSizeImgView = findViewById(R.id.grindSizeBackground);
        brewTempImgView = findViewById(R.id.brewingTempBackground);
        bloomWaterAmtImgView = findViewById(R.id.bloomWaterBackground);
        bloomTimeImgView = findViewById(R.id.bloomTimeBackground);
        totalTimeImgView = findViewById(R.id.totalBrewTimeBackground);
        ratioImgView = findViewById(R.id.coffeeWaterRatioBackground);

        groundCoffeeAmtBtn = findViewById(R.id.groundCoffeAmtText);
        groundCoffeeAmtBtn.setText("Ground Coffee Amount" + " " + "(" + (brew.getGroundCoffeeAmount() + "g)"));
        grindSizeBtn = findViewById(R.id.grindSizeText);
        grindSizeBtn.setText("Grind Size" + " " + "(" + (brew.getGrindSize() + ")"));
        brewTempBtn = findViewById(R.id.brewingTempText);
        brewTempBtn.setText("Brewing Temperature" + " " + "(" + (brew.getBrewingTemperature() + "C)"));
        bloomWaterAmtBtn = findViewById(R.id.bloomWaterText);
        bloomWaterAmtBtn.setText("Bloom Water Amount" + " " + "(" + (brew.getBloomAmount() + "ml)"));
        bloomTimeBtn = findViewById(R.id.bloomTimeText);
        bloomTimeBtn.setText("Bloom Time" + " " + "(" + (brew.getBloomTime() + "s)"));
        totalTimeBtn = findViewById(R.id.totalBrewTimeText);
        totalTimeBtn.setText("Total Brewing TIme" + " " + "(" + (brew.getTotalBrewTime() + "min/s)"));
        ratioBtn = findViewById(R.id.coffeeWaterRatioText);
        ratioBtn.setText("Coffee Water Ratio" + " " + "(" + (brew.getCoffeeWaterRatio() + "g/L)"));
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

    public void updateTextActivity(){
        Brew brew = BrewBuilder.getInstance().get();
        groundCoffeeAmtBtn.setText("Ground Coffee Amount" +(brew.getGroundCoffeeAmount()));
        grindSizeBtn.setText("Grind Size" + " " + "(" + (brew.getGrindSize() + ")"));
        brewTempBtn.setText("Brewing Temperature" + " " + "(" + (brew.getBrewingTemperature() + "C)"));
        bloomWaterAmtBtn.setText("Bloom Water Amount" + " " + "(" + (brew.getBloomAmount() + "ml)"));
        bloomTimeBtn.setText("Bloom Time" + " " + "(" + (brew.getBloomTime() + "s)"));
        totalTimeBtn.setText("Total Brewing TIme" + " " + "(" + (brew.getTotalBrewTime() + "min/s)"));
        ratioBtn.setText("Coffee Water Ratio" + " " + "(" + (brew.getCoffeeWaterRatio() + "g/L)"));

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