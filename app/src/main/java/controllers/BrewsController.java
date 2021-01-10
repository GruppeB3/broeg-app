package controllers;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import models.Brew;

public class BrewsController {

    public BrewsController() {}

    public static ArrayList<Brew> getBrewsFromLocalStorage(SharedPreferences preferences) {
        Gson gson = new Gson();
        return gson.fromJson(preferences.getString("brews", "[]"), new TypeToken<ArrayList<Brew>>(){}.getType());
    }

    public static Brew getBrewFromLocalStorage(SharedPreferences preferences, int position) {
        ArrayList<Brew> brews = getBrewsFromLocalStorage(preferences);

        if (position < 0 || position >= brews.size())
            return null;

        return brews.get(position);
    }

    public static void saveBrewsToLocalStorage(SharedPreferences preferences, ArrayList<Brew> brews) {
        Gson gson = new Gson();
        preferences.edit().putString("brews", gson.toJson(brews)).apply();
    }

}
