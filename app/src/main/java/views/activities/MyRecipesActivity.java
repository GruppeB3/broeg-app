package views.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

import controllers.BrewsController;
import dk.dtu.gruppeb3.broeg.app.R;
import helpers.PreferenceHelper;
import models.Brew;
import views.adapters.MyRecipeListAdapter;

import static java.lang.String.valueOf;


public class MyRecipesActivity extends AppCompatActivity implements MyRecipeListAdapter.MyRecipeListButtonListener {

    private ArrayList<Brew> brews;
    private SharedPreferences prefs;
    MyRecipeListAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrecipes);

        prefs = PreferenceHelper.getApplicationPreferences(this);
        updateListOfBrews();

        if (getBrewFromIntent() != null) {
            brews.add(getBrewFromIntent());
            BrewsController.saveBrewsToLocalStorage(prefs, brews);
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
        this.brews = BrewsController.getBrewsFromLocalStorage(prefs);
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
            i.putExtra(EditRecipeActivity.BREW_POSITION_KEY, position);
            startActivity(i);

        } else if (mode == MyRecipeListAdapter.Mode.DELETE) {

            final ArrayList<Brew> brews = BrewsController.getBrewsFromLocalStorage(prefs);
            final Brew brew = brews.get(position);

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle(getString(R.string.delete_brew));
            alert.setMessage(getString(R.string.sure_to_delete_brew, brew.getName()));

            alert.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    brews.remove(brew);
                    recyclerViewAdapter.setRecipes(brews);
                    BrewsController.saveBrewsToLocalStorage(prefs, brews);
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

            alert.setMessage("Bloom Time:  "+(brew.getBloomTime() + " - " + "Bloom Amount:  " +
                    brew.getBloomAmount() + " - " + "Brewing Temperature  " +
                    brew.getBrewingTemperature() + " - " + "Ground Coffee Amount  " +
                    brew.getGroundCoffeeAmount() + " - " + "Grind Size  " +
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


}