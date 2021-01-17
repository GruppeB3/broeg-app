package views.activities.community.profile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import dk.dtu.gruppeb3.broeg.app.R;
import models.App;
import views.activities.BaseActivity;
import views.activities.community.login.LoginActivity;

public class MyProfileActivity extends BaseActivity {

    TextView name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentLayout(R.layout.activity_my_profile);

        startActivity(new Intent(this, LoginActivity.class));

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);

        name.setText(getString(R.string.my_profile_name, App.getInstance().getUser().getName()));
        email.setText(getString(R.string.my_profile_email, App.getInstance().getUser().getEmail()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        name.setText(getString(R.string.my_profile_name, App.getInstance().getUser().getName()));
        email.setText(getString(R.string.my_profile_email, App.getInstance().getUser().getEmail()));
    }
}