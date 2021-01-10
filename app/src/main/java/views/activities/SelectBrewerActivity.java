package views.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

import controllers.BrewersController;
import dk.dtu.gruppeb3.broeg.app.R;
import helpers.PreferenceHelper;
import models.Brewer;
import views.activities.espble.ConnectBluetoothDeviceActivity;
import views.adapters.SelectBrewerListAdapter;
import views.listeners.OnRecyclerViewItemClickListener;

public class SelectBrewerActivity extends BaseActivity implements View.OnClickListener, OnRecyclerViewItemClickListener {

    private ArrayList<Brewer> brewers;
    private Button addNewDeviceBtn;
    private SelectBrewerListAdapter listViewAdapter;
    private SharedPreferences prefs;

    private final Intent resultIntent = new Intent();
    public static final String SELECTED_BREWER_IDENTIFIER = "selectedBrewer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentLayout(R.layout.activity_select_brewer).hideMenu();
//        setContentView(R.layout.activity_select_brewer);

        prefs = PreferenceHelper.getApplicationPreferences(getApplicationContext());

        this.addNewDeviceBtn = findViewById(R.id.add_new_device_btn);
        this.addNewDeviceBtn.setOnClickListener(this);

        RecyclerView listView = findViewById(R.id.select_brewer_list);
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.scrollToPosition(0);

        listViewAdapter = new SelectBrewerListAdapter(this.brewers, this);
        listView.setAdapter(listViewAdapter);

        updateBrewersList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateBrewersList();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == this.addNewDeviceBtn) {
            Intent i = new Intent(this, ConnectBluetoothDeviceActivity.class);
            startActivity(i);
        }
    }

    private void updateBrewersList() {
        this.brewers = BrewersController.getSavedBrewers(prefs);
        listViewAdapter.setBrewers(this.brewers);
    }

    @Override
    public void onItemClick(View view, int position) {
        // A brewer in the list was clicked
        String json = (new Gson()).toJson(this.brewers.get(position));
        resultIntent.putExtra(SELECTED_BREWER_IDENTIFIER, json);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}