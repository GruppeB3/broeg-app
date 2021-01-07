package controllers;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import models.Brewer;

public class BrewersController {

    public static final String BREWERS_LIST_IDENTIFIER = "brewers";

    public static ArrayList<Brewer> getSavedBrewers(SharedPreferences prefs) {
        Gson gson = new Gson();
        ArrayList<Brewer> brewers = gson.fromJson(prefs.getString(BREWERS_LIST_IDENTIFIER, "[]"),
                new TypeToken<ArrayList<Brewer>>(){}.getType());

        if (brewers.size() == 0) {
            Brewer mockBrewer = new Brewer("Mock brewer");
            mockBrewer.setMacAddress("01:23:45:67:89:AB");
            brewers.add(mockBrewer);
        }

        return brewers;
    }
}
