package models;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Application extends android.app.Application {
    private static Application instance;
    private static SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Application get() {
        return instance;
    }


/**
 * Gets shared preferences.
 *
 * @return the shared preferences
 */

public static SharedPreferences getSharedPreferences() {
    if (preferences == null)
        preferences = PreferenceManager.getDefaultSharedPreferences(instance);
    return preferences;
}

    //set methods
    public static void setPreferences(String key, String value) {
        getSharedPreferences().edit().putString(key, value).apply();
    }

    public static void setPreferences(String key, long value) {
        getSharedPreferences().edit().putLong(key, value).apply();
    }
    public static void setPreferences(String key, int value) {
        getSharedPreferences().edit().putInt(key, value).apply();
    }

    public static void setPreferencesBoolean(String key, boolean value) {
        getSharedPreferences().edit().putBoolean(key, value).apply();
    }

    //get methods
    public static String getPrefrenceData(String key) {
        return getSharedPreferences().getString(key, "");
    }

    public static int getPrefrenceDataInt(String key) {
        return getSharedPreferences().getInt(key, 0);
    }

    public static boolean getPrefrenceDataBoolean(String key) {
        return getSharedPreferences().getBoolean(key, false);
    }
    public static long getPrefrenceDataLong(String interval) {
        return getSharedPreferences().getLong(interval, 0);
    }
}