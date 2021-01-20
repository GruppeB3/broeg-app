package views.activities;

import android.os.Bundle;

import dk.dtu.gruppeb3.broeg.app.R;

public class GroupMembersActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentLayout(R.layout.activity_group_members);
    }
}