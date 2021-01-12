package services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import controllers.ApiController;

public class UserLoginService extends Service implements Response.Listener<String>, Response.ErrorListener {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        ApiController.makeHttpGetRequest(ApiController.getApiBaseUrl() + "user", this, this);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        
    }

    @Override
    public void onResponse(String response) {

    }
}
