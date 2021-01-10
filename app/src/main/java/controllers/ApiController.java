package controllers;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import models.App;

public class ApiController {

    private static RequestQueue rq = Volley.newRequestQueue(App.getInstance().getApplicationContext());

    public static String getApiBaseUrl() {
        return "https://broeg.eadu.dk/api/V1/";
    }

    public static void makeHttpPostRequest(String url, JSONObject body, Response.Listener<JSONObject> okListener, Response.ErrorListener errorListener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, body, okListener, errorListener);
        rq.add(request);
    }

    public static void makeHttpGetRequest(String url, JSONObject body, Response.Listener<JSONObject> okListener, Response.ErrorListener errorListener) {
        JsonObjectRequest request = null;
        if (App.getInstance().getUser().getApiToken() == null) {
            request = new JsonObjectRequest(Request.Method.GET, url, body, okListener, errorListener);
        } else {
            request = new JsonObjectRequest(Request.Method.GET, url, body, okListener, errorListener) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();

                    for (Map.Entry<String, String> entry : super.getHeaders().entrySet()) {
                        map.put(entry.getKey(), entry.getValue());
                    }

                    map.put("Authorization", "Bearer " + App.getInstance().getUser().getApiToken());
                    map.put("Accept", "application/json"); // Always have to be json response

                    return map;
                }
            };
        }

        rq.add(request);
    }

}
