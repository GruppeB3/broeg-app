package controllers;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
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

    public static void makeHttpGetRequest(String url, Response.Listener<String> okListener, Response.ErrorListener errorListener) {
        StringRequest request = new StringRequest(Request.Method.GET, url, okListener, errorListener);

        if (App.getInstance().getUser().getApiToken() != null) {
            request = new StringRequest(Request.Method.GET, url, okListener, errorListener) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return addApiHeaders(super.getHeaders());
                }
            };
        }

        rq.add(request);
    }

    public static void makeHttpGetRequest(String url, JSONObject body, Response.Listener<JSONObject> okListener, Response.ErrorListener errorListener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, body, okListener, errorListener);

        if (App.getInstance().getUser().getApiToken() != null ){
            // We have a token stored - use it!
            request = new JsonObjectRequest(Request.Method.GET, url, body, okListener, errorListener) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return addApiHeaders(super.getHeaders());
                }
            };
        }

        rq.add(request);
    }

    public static void makeHttpPatchRequest(String url, JSONObject body, Response.Listener<JSONObject> okListener, Response.ErrorListener errorListener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PATCH, url, body, okListener, errorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return addApiHeaders(super.getHeaders());
            }
        };

        rq.add(request);
    }

    private static Map<String, String> addApiHeaders(Map<String, String> existingHeaders) {
        Map<String, String> headers = new HashMap<>(existingHeaders);

        String apiToken = App.getInstance().getUser().getApiToken();
        if (apiToken != null) {
            headers.put("Authorization", "Bearer " + App.getInstance().getUser().getApiToken());
        }

        headers.put("Accept", "application/json"); // Always have to be json response

        return headers;
    }

}
