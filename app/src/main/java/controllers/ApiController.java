package controllers;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import models.App;

public class ApiController {

    private static RequestQueue rq = Volley.newRequestQueue(App.getInstance().getApplicationContext());

    public static String getApiBaseUrl() {
        return "https://broeg.eadu.dk/api/V1/";
    }

    public static void makeHttpPostRequest(String url, JSONObject body, Response.Listener<JSONObject> okListener, Response.ErrorListener errorListener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, body, okListener, errorListener);
        System.out.println("Queuing request");
        rq.add(request);
    }

}
