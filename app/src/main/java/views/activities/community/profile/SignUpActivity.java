package views.activities.community.profile;

import android.app.ProgressDialog;
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

public class SignUpActivity extends BaseActivity implements Response.ErrorListener, Response.Listener<JSONObject> {

    EditText name, email, password, confirmPassword;
    Button signUpBtn;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentLayout(R.layout.activity_signup);

        name = findViewById(R.id.sign_up_name);
        email = findViewById(R.id.sign_up_email);
        password = findViewById(R.id.sign_up_password);
        confirmPassword = findViewById(R.id.sign_up_confirm_password);
        signUpBtn = findViewById(R.id.sign_up_btn);

        signUpBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == signUpBtn) {
            signUp();
        }
    }

    private void signUp() {
        if (!checkFields())
            return;

        progressDialog = ProgressDialog.show(this, "", "Signing you up...");

        JSONObject json = new JSONObject();
        try {
            json.put("name", name.getText().toString());
            json.put("email", email.getText().toString());
            json.put("password", password.getText().toString());
            json.put("password_confirmation", confirmPassword.getText().toString());

            String apiUrl = ApiController.getApiBaseUrl() + "register";
            ApiController.makeHttpPostRequest(apiUrl, json, this, this);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private boolean checkFields() {
        String password = this.password.getText().toString();
        String confirmPass = this.confirmPassword.getText().toString();
        String name = this.name.getText().toString();
        String email = this.email.getText().toString();

        boolean result = true;

        if (password.length() < 8) {
            this.password.setError("Password must be at least 8 characters");

            result = false;
        }

        if (!password.equals(confirmPass)) {
            // Passwords are not the same!
            this.password.setError("Password and confirm password must match");
            this.confirmPassword.setError("Password and confirm password must match");

            result = false;
        }

        if (name.length() < 2) {
            this.name.setError("Name must have a length of at least 2 characters");

            result = false;
        }

        if (!email.contains("@") || !email.contains(".")) {
            this.email.setError("A valid e-mail address must be provided");

            result = false;
        }

        return result;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if (progressDialog != null) {
            progressDialog.cancel();
        }

        error.printStackTrace();
        Toast.makeText(this, "An error occurred while signing you up. Maybe you already have an account with that e-mail?", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        if (progressDialog != null) {
            progressDialog.cancel();
        }

        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(LoginActivity.EMAIL_IDENTIFIER, email.getText().toString());
        intent.putExtra(LoginActivity.PASSWORD_IDENTIFIER, password.getText().toString());
        startActivity(intent);
        finish();
    }
}