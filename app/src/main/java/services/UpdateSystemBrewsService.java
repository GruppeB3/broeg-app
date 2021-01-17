package services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import controllers.ApiController;
import helpers.PreferenceHelper;
import models.Brew;

public class UpdateSystemBrewsService extends Service implements Response.Listener<JSONObject>, Response.ErrorListener {

    private static ExecutorService bgThread = Executors.newSingleThreadExecutor();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        bgThread.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo mWifi = connManager.getActiveNetworkInfo();

                    if (mWifi == null || mWifi.isRoaming()) {
                        break;
                    }

                    updateBrews();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored) {}
                }
            }
        });

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void updateBrews() {
        System.out.println("Updating system brews!");
        String apiUrl = ApiController.getApiBaseUrl() + "brews/defaults";
        ApiController.makeHttpGetRequest(apiUrl, new JSONObject(), this, this);
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray array = response.getJSONArray("data");
            ArrayList<Brew> brews = new ArrayList<>();
            SharedPreferences prefs = PreferenceHelper.getApplicationPreferences(getApplicationContext());
            Gson gson = new Gson();

            for (int i = 0; i < array.length(); i++) {
                brews.add(Brew.fromApi(array.getJSONObject(i)));
            }

            prefs.edit().putString("systemBrews", gson.toJson(brews)).apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
    }
}
