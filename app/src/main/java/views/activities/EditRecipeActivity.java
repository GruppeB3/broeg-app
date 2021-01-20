package views.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import controllers.ApiController;
import controllers.BrewsController;
import dk.dtu.gruppeb3.broeg.app.R;
import io.sentry.Sentry;
import models.App;
import models.Brew;
import models.BrewBuilder;

public class EditRecipeActivity extends NewRecipeActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    public static final String BREW_POSITION_KEY = "brew_position";

    private Button saveBtn;
    private BrewBuilder builder;
    private Brew brew;
    private int position;
    private SharedPreferences preferences;

    ProgressDialog progressDialog;

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

        updateTextActivity();
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
            return;
        }

        super.onClick(ClickButton);
    }

    private void saveBrew() {
        ArrayList<Brew> brews = BrewsController.getBrewsFromLocalStorage(preferences);
        Brew brew = builder.get();
        brews.set(position, brew);
        BrewsController.saveBrewsToLocalStorage(preferences, brews);

        if (brew.getCommunityId() > 0 && App.getInstance().userIsLoggedIn()) {
            // Brew is a community brew.
            progressDialog = ProgressDialog.show(this, "", "Saving update brew in cloud");

            try {
                String apiUrl = ApiController.getApiBaseUrl() + "user/brew/" + brew.getCommunityId();
                ApiController.makeHttpPatchRequest(apiUrl, brew.toApiJson(), this, this);
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(this, getString(R.string.error_updating_brew), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onResponse(JSONObject response) {
        if (progressDialog != null) {
            progressDialog.cancel();
        }

        finish();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if (progressDialog != null) {
            progressDialog.cancel();
            Toast.makeText(this, getString(R.string.error_updating_brew), Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}