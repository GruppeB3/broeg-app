package views.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import controllers.ApiController;
import controllers.BrewsController;
import dk.dtu.gruppeb3.broeg.app.R;
import helpers.PreferenceHelper;
import models.App;
import models.Brew;
import views.activities.community.login.LoginActivity;
import views.adapters.MyRecipeListAdapter;

public class MyRecipesActivity extends BaseActivity implements MyRecipeListAdapter.MyRecipeListButtonListener, Response.Listener<JSONObject>, Response.ErrorListener {

    private ArrayList<Brew> brews;
    private ArrayList<Brew> systemBrews;
    private SharedPreferences prefs;
    MyRecipeListAdapter recyclerViewAdapter;
    ProgressDialog progressDialog;
    Date lastUpdatedAt;
    Date lastUpdateStartedAt;

    RequestMode requestMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentLayout(R.layout.activity_myrecipes);

        prefs = PreferenceHelper.getApplicationPreferences(this);
        updateListOfBrews();

        Brew brewFromIntent = getBrewFromIntent();
        if (brewFromIntent != null) {
            brews.add(brewFromIntent);
            long id = BrewsController.saveBrewToLocalStorage(brewFromIntent);
            brewFromIntent.setCommunityId((int) id);

            if (App.getInstance().userIsLoggedIn()) {
                try {
                    String apiUrl = ApiController.getApiBaseUrl() + "user/brews";
                    requestMode = RequestMode.ADD;
                    ApiController.makeHttpPostRequest(apiUrl, brewFromIntent.toApiJson(), this, this);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        RecyclerView listView = this.findViewById(R.id.myrecipes_List);
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.scrollToPosition(0);

        recyclerViewAdapter = new MyRecipeListAdapter(brews, this);

        listView.setAdapter(recyclerViewAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateListOfBrews();
    }


    private Brew getBrewFromIntent() {
        String json = this.getIntent().getStringExtra("brew");

        if (json == null|| json.equals("")) {
            return null;
        }

        return (new Gson()).fromJson(json, Brew.class);
    }

    private void updateListOfBrews() {
        this.systemBrews = BrewsController.getSystemBrews(prefs);
        this.brews = new ArrayList<>(this.systemBrews);
        this.brews.addAll(BrewsController.getBrewsFromLocalStorage(prefs));

        if (lastUpdateStartedAt != null && lastUpdateStartedAt.getTime() > (new Date()).getTime() - (5 * 60 * 1000)) {
            // Data update was started within the last 5 minutes
            return;
        }

        lastUpdateStartedAt = new Date();

        if (!App.getInstance().userIsLoggedIn())
            return;

        requestMode = RequestMode.UPDATE;

        progressDialog = ProgressDialog.show(this, "", getString(R.string.updating_brews));
        String apiUrl = ApiController.getApiBaseUrl() + "user/brews";
        ApiController.makeHttpGetRequest(apiUrl, new JSONObject(), this, this);
    }

    public void startBrew(int position){
        String json = (new Gson()).toJson(this.brews.get(position));
        Intent i = new Intent(this, BrewingActivity.class);
        i.putExtra(BrewingActivity.SELECTED_BREW_IDENTIFIER, json);
        startActivity(i);
        finish();
    }


    @Override
    public void onMyRecipeListButtonClick(MyRecipeListAdapter.Mode mode, final int position) {

        if (mode == MyRecipeListAdapter.Mode.EDIT) {

            Intent i = new Intent(this, EditRecipeActivity.class);
            i.putExtra(EditRecipeActivity.BREW_POSITION_KEY, position - this.systemBrews.size());
            startActivity(i);

        } else if (mode == MyRecipeListAdapter.Mode.DELETE) {

            final ArrayList<Brew> brews = BrewsController.getBrewsFromLocalStorage(prefs);
            final Brew brew = brews.get(position - this.systemBrews.size());

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle(getString(R.string.delete_brew));
            alert.setMessage(getString(R.string.sure_to_delete_brew, brew.getName()));

            alert.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    brews.remove(brew);
                    BrewsController.deleteBrewFromLocalStorage(brew);

                    ArrayList<Brew> brews = new ArrayList<>(systemBrews);
                    brews.addAll(BrewsController.getBrewsFromLocalStorage(prefs));
                    recyclerViewAdapter.setRecipes(brews);

                    if (brew.getCommunityId() > 0 && App.getInstance().userIsLoggedIn()) {
                        // Brew was added in the cloud
                        requestMode = RequestMode.DELETE;
                        deleteBrewFromCloud(brew.getCommunityId());
                    }
                }
            });

            alert.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    return;
                }
            });

            alert.show();

        } else if (mode == MyRecipeListAdapter.Mode.NONE) {

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            Brew brew = brews.get(position);
            alert.setTitle(brew.getName());

            alert.setMessage("Bloom Time: " + (brew.getBloomTime() + "(s)" + " - " + "Bloom Amount: " +
                    brew.getBloomAmount() + "(ml)" + " - " + "Total Time: " + brew.getTotalBrewTime() + "(s)" + " - "
                    + "Coffee Water Ratio: " + brew.getCoffeeWaterRatio() + " - " + "Brewing Temperature: " +
                    brew.getBrewingTemperature() + "(C)" + " - " + "Ground Coffee Amount: " +
                    brew.getGroundCoffeeAmount() + "(g)" + " - " + "Grind Size: " +
                    (brew.getGrindSize())));

            alert.setPositiveButton("Bryg", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                   startBrew(position);
                }
            });
            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                        return;
                }
            });
            alert.create().show();

        }
    }

    private void deleteBrewFromCloud(int communityId) {
        progressDialog = ProgressDialog.show(this, "", "Deleting brew...");

        String apiUrl = ApiController.getApiBaseUrl() + "user/brew/" + communityId;
        ApiController.makeHttpDeleteRequest(apiUrl, new JSONObject(), this, this);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        // We got an error from the API
        if (progressDialog != null) {
            progressDialog.cancel();
        }

        error.printStackTrace();

        if (error instanceof AuthFailureError) {
            // error was an auth failure
            // send to log in
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    @Override
    public void onResponse(JSONObject response) {
        // We got response from the API

        if (requestMode == RequestMode.UPDATE) {
            try {
                JSONArray array = response.getJSONArray("data");
                ArrayList<Brew> brewsFromApi = new ArrayList<>();

                for (int i = 0; i < array.length(); i++) {
                    brewsFromApi.add(Brew.fromApi(array.getJSONObject(i)));
                }

                BrewsController.upsertBrewsFromApi(prefs, brewsFromApi);
                this.brews = BrewsController.getSystemBrews(prefs);
                this.brews.addAll(BrewsController.getBrewsFromLocalStorage(prefs));
                recyclerViewAdapter.setRecipes(this.brews);
                lastUpdatedAt = new Date();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (progressDialog != null) {
            progressDialog.cancel();
            progressDialog.hide();
        }
    }

    private enum RequestMode {
        ADD, DELETE, UPDATE;
    }
}