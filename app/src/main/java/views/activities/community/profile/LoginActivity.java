package views.activities.community.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import controllers.ApiController;
import dk.dtu.gruppeb3.broeg.app.R;
import models.App;
import views.activities.BaseActivity;

public class LoginActivity extends BaseActivity implements Response.ErrorListener, Response.Listener<JSONObject> {

    EditText username, password;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentLayout(R.layout.activity_login);

        if (App.getInstance().userIsLoggedIn()) {
            handleUserIsLoggedIn();
        }

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login);

        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == loginBtn) {
            loginBtn.setEnabled(false);
            logInUser();
        }
    }

    private void handleUserIsLoggedIn() {
        startActivity(new Intent(this, MyProfileActivity.class));
        finish();
    }

    private void logInUser() {
        String email = this.username.getText().toString();
        String password = this.password.getText().toString();

        if (email.length() == 0 || password.length() == 0) {
            Toast.makeText(this, getString(R.string.login_parameters_not_ok), Toast.LENGTH_SHORT).show();
        }


        JSONObject params = new JSONObject();
        try {
            params.put("email", email);
            params.put("password", password);
            params.put("device_name", "Test device"); //Settings.System.getString(App.getInstance().getContentResolver(), "device_name"));
            // TODO: Add proper device name
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiController.makeHttpPostRequest(ApiController.getApiBaseUrl() + "user/token/", params, this, this);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
        Toast.makeText(this, "Error while logging in", Toast.LENGTH_SHORT).show();
        loginBtn.setEnabled(true);
    }

    @Override
    public void onResponse(JSONObject response) {
        String token = null;

        try {
            token = response.getString("data");
        } catch (JSONException ignored) {}

        App.getInstance().getUser().setApiToken(token);
        System.out.println(response);

        finish();
    }
}