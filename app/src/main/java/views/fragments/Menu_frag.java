package views.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

import dk.dtu.gruppeb3.broeg.app.R;
import models.App;
import models.User;
import views.activities.community.profile.MyProfileActivity;
import io.sentry.Sentry;
import views.activities.espble.ConnectBluetoothDeviceActivity;

public class Menu_frag extends Fragment implements View.OnClickListener {

    Button settingsBtn, helpBtn, connectBtn, signOutBtn, profileBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_menu, container, false);

        boolean EMULATOR = Build.PRODUCT.contains("sdk") || Build.MODEL.contains("Emulator");
        if (!EMULATOR) {
            Sentry.init("https://6c16f6a55ee34d9e8837467884c273b9@o506357.ingest.sentry.io/5595954");
        }

        settingsBtn = root.findViewById(R.id.menu_settings_btn);
        helpBtn = root.findViewById(R.id.menu_help_btn);
        connectBtn = root.findViewById(R.id.menu_connect_btn);
        signOutBtn = root.findViewById(R.id.menu_sign_out_btn);
        profileBtn = root.findViewById(R.id.menu_myprofile_button);

        settingsBtn.setOnClickListener(this);
        helpBtn.setOnClickListener(this);
        connectBtn.setOnClickListener(this);
        signOutBtn.setOnClickListener(this);
        profileBtn.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        if (v == settingsBtn) {
            // Do nothing for now
        } else if (v == helpBtn) {
            // Do nothing for now
        } else if (v == connectBtn) {
            Intent i = new Intent(getActivity(), ConnectBluetoothDeviceActivity.class);
            startActivity(i);
        } else if (v == signOutBtn) {
            App.getInstance().setUser(new User("Local user"));
        } else if (v == profileBtn) {
            Intent i = new Intent(getActivity(), MyProfileActivity.class);
            startActivity(i);
        }
        getActivity().onBackPressed();
    }
}
