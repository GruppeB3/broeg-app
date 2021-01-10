package models;

import android.app.Application;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import helpers.PreferenceHelper;

public class App extends Application {

    private static App instance = null;
    private User user = null;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        SharedPreferences prefs = PreferenceHelper.getApplicationPreferences(getApplicationContext());
        Gson gson = new Gson();
        this.user = gson.fromJson(prefs.getString("user", gson.toJson(new User("Local user"))), User.class);
    }

    public static App getInstance() {
        return instance;
    }

    public void saveUserData() {
        Gson gson = new Gson();
        SharedPreferences prefs = PreferenceHelper.getApplicationPreferences(getApplicationContext());
        prefs.edit().putString("user", gson.toJson(this.user)).apply();
    }

    public User getUser() {
        if (user == null) {
            user = new User("Local user");
        }

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean userIsLoggedIn() {
        return getUser().getApiToken() != null;
    }
}
