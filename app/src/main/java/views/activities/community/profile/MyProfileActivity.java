package views.activities.community.profile;

import android.content.Intent;
import android.os.Bundle;

import dk.dtu.gruppeb3.broeg.app.R;
import models.App;
import views.activities.BaseActivity;

public class MyProfileActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentLayout(R.layout.activity_my_profile);

        if (!App.getInstance().userIsLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}