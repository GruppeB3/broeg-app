package views.activities.community.login;

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
import views.activities.BaseActivity;

public class ResetPasswordActivity extends BaseActivity implements Response.ErrorListener, Response.Listener<JSONObject> {

    EditText resetPwdText;
    Button resetPwdBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentLayout(R.layout.activity_reset_password);

        resetPwdText = findViewById(R.id.reset_password_email);
        resetPwdBtn = findViewById(R.id.reset_password_submit);

        resetPwdBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == resetPwdBtn) {
            resetPassword();
        }
    }

    private void resetPassword() {
        String email = resetPwdText.getText().toString();

        if (email == null || email.equals("")) {
            resetPwdText.setError("An e-mail address must be provided");
            return;
        }

        try {
            String apiUrl = ApiController.getApiBaseUrl() + "user/reset-password";
            JSONObject json = new JSONObject();
            json.put("email", email);

            ApiController.makeHttpPostRequest(apiUrl, json, this, this);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
        Toast.makeText(this, "An error occurred!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}