package views.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import controllers.BrewsController;
import dk.dtu.gruppeb3.broeg.app.R;
import models.Brew;
import models.BrewBuilder;

public class EditRecipeActivity extends NewRecipeActivity {

    public static final String BREW_POSITION_KEY = "brew_position";

    private Button saveBtn;
    private BrewBuilder builder;
    private Brew brew;
    private int position;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        builder = BrewBuilder.getInstance();

        preferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);

        position = getIntent().getIntExtra(BREW_POSITION_KEY, -1);
        brew = BrewsController.getBrewFromLocalStorage(preferences, position);

        if (position == -1 || brew == null) {
            // TODO: Change this to something else?
            finish();
        }

        builder.set(brew);

        saveBtn = findViewById(R.id.saveButton);
    }

    @Override
    protected void onResume() {
        super.onResume();
        builder.set(brew);
    }

    @Override
    public void onClick(View ClickButton) {
        if (ClickButton == saveBtn) {
            saveBrew();
            finish();
            return;
        }

        super.onClick(ClickButton);
    }

    private void saveBrew() {
        ArrayList<Brew> brews = BrewsController.getBrewsFromLocalStorage(preferences);
        brews.set(position, builder.get());
        BrewsController.saveBrewsToLocalStorage(preferences, brews);
    }
}