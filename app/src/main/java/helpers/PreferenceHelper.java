package helpers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Helper class to clean up the general code a little
 */
public class PreferenceHelper {

    public static final String PREFERENCE_KEY = "prefs";

    /**
     * Put a double to the preferences
     * <br>
     * Underlying the double will be stored as a string so it's highly
     * important that the value is retrieved with the "sister function"
     * getDouble(SharedPreferences pf, String key, String defValue)
     *
     * @param pf SharedPreferences, the preferences that should be added to
     * @param key String, the key of the the key-value pair
     * @param val double, the value of the key-value pair
     */
    public static void putDouble(SharedPreferences pf, String key, double val) {
        pf.edit().putString(key, Double.toString(val)).apply();
    }

    /**
     * Get a double stored as a string from the preferences
     *
     * @param pf SharedPreferences, the preferences the value should be retrieved from
     * @param key String, the key associated with the value
     * @param defValue String, a default value
     * @return double
     */
    public static double getDouble(SharedPreferences pf, String key, String defValue) {
        return Double.parseDouble(pf.getString(key, defValue));
    }


    /**
     * Get the shared preferences with default mode (Context.MODE_PRIVATE)
     *
     * @param context
     * @return
     */
    public static SharedPreferences getApplicationPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE);
    }

    /**
     * Get the shared preferences with a given mode
     *
     * @param context
     * @param mode
     * @return
     */
    public static SharedPreferences getApplicationPreferences(Context context, int mode) {
        return context.getSharedPreferences(PREFERENCE_KEY, mode);
    }
}
