package controllers;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

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

    public static void upsertBrewsFromApi(SharedPreferences prefs, List<Brew> brews) {
        ArrayList<Brew> localBrews = getBrewsFromLocalStorage(prefs);

        for (int i = 0; i < brews.size(); i++) {
            Brew brewFromApi = brews.get(i);
            int position = getPositionFromCommunityId(brewFromApi.getCommunityId(), localBrews);
            if (position > -1) {
                // Brew exists - update it!
                Brew brew = localBrews.get(position);
                brew.update(brewFromApi);
                localBrews.set(position, brew);
                continue;
            } else {
                // Brew does not exist in local storage
                localBrews.add(brewFromApi);
            }
        }

        saveBrewsToLocalStorage(prefs, localBrews);
    }

    public static int getPositionFromCommunityId(int id, List<Brew> brews) {
        for (int i = 0; i < brews.size(); i++) {
            Brew brew = brews.get(i);
            if (brew.getCommunityId() == id) {
                return i;
            }
        }

        return -1;
    }

    public static Brew getBrewFromCommunityId(int id, List<Brew> brews) {
        for (Brew brew : brews) {
            if (brew.getCommunityId() == id) {
                return brew;
            }
        }

        return null;
    }

}
