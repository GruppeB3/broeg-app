package views.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import controllers.BrewsController;
import dk.dtu.gruppeb3.broeg.app.R;
import io.sentry.Sentry;
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

        boolean EMULATOR = Build.PRODUCT.contains("sdk") || Build.MODEL.contains("Emulator");
        if (!EMULATOR) {
            Sentry.init("https://6c16f6a55ee34d9e8837467884c273b9@o506357.ingest.sentry.io/5595954");
        }

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