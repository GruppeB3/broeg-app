package views.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import dk.dtu.gruppeb3.broeg.app.R;
import models.Brew;
import views.adapters.MyRecipeListAdapter;


public class MyRecipesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayList<Brew> brews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("pref", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        updateListOfBrews();

        if(getBrewFromIntent()!= null){
            brews.add(getBrewFromIntent());
            prefs.edit().putString("brews", gson.toJson(brews)).apply();
        }

        setContentView(R.layout.activity_myrecipes);

        RecyclerView listView = this.findViewById(R.id.myrecipes_List);
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.scrollToPosition(0);

        MyRecipeListAdapter recyclerViewAdapter = new MyRecipeListAdapter(brews);

        listView.setAdapter(recyclerViewAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateListOfBrews();
    }

    // TODO: Re-introduce on item click
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        Brew brew = brews.get(position);
        alert.setTitle(brew.getName());
        alert.setMessage(brew.getBloomTime());
        alert.setMessage((int) brew.getBloomAmount());
        alert.setMessage((int) brew.getBrewingTemperature());
        alert.setMessage((int) brew.getGroundCoffeeAmount());
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alert.show();
    }

    private Brew getBrewFromIntent (){
        String json = this.getIntent().getStringExtra("brew");

        if (json == null|| json.equals("")) {
            return null;
        }

        return (new Gson()).fromJson(json, Brew.class);
    }

    private void updateListOfBrews() {
        SharedPreferences prefs = getSharedPreferences("pref", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        this.brews = gson.fromJson(prefs.getString("brews", "[]"), new TypeToken<ArrayList<Brew>>(){}.getType());
    }
}