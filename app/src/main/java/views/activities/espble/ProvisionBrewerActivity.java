package views.activities.espble;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

import com.airbnb.lottie.LottieAnimationView;
import com.espressif.provisioning.ESPConstants;
import com.espressif.provisioning.listeners.ProvisionListener;

import controllers.EspBluetoothConnectionsController;
import dk.dtu.gruppeb3.broeg.app.R;

public class ProvisionBrewerActivity extends AppCompatActivity implements View.OnClickListener, ProvisionListener {

    public static final String SSID_STRING_ID = "SSID";
    public static final String PASSWORD_STRING_ID = "PASSWORD";

    private static final String TAG = ProvisionBrewerActivity.class.getSimpleName();

    Button doneBtn;
    EspBluetoothConnectionsController controller;

    LottieAnimationView tick1, tick2, tick3;
    ContentLoadingProgressBar prog1, prog2, prog3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provision_brewer);

        controller = EspBluetoothConnectionsController.getInstance();

        doneBtn = findViewById(R.id.doneBtn);
        doneBtn.setOnClickListener(this);

        initViews();

        String ssid = getIntent().getStringExtra(SSID_STRING_ID);
        String password = getIntent().getStringExtra(PASSWORD_STRING_ID);

        if (ssid != null && !ssid.equals("") && password != null) {
            controller.sendWifiCredentialsToDevice(getApplicationContext(), ssid, password, this);
            prog1.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == doneBtn) {
            finish();
        }
    }

    private void initViews() {
        tick1 = findViewById(R.id.iv_tick_1);
        tick2 = findViewById(R.id.iv_tick_2);
        tick3 = findViewById(R.id.iv_tick_3);

        tick1.setVisibility(View.INVISIBLE);
        tick2.setVisibility(View.INVISIBLE);
        tick3.setVisibility(View.INVISIBLE);

        prog1 = findViewById(R.id.prov_progress_1);
        prog2 = findViewById(R.id.prov_progress_2);
        prog3 = findViewById(R.id.prov_progress_3);

        prog1.setVisibility(View.INVISIBLE);
        prog2.setVisibility(View.INVISIBLE);
        prog3.setVisibility(View.INVISIBLE);
    }

    @Override
    public void createSessionFailed(Exception e) {
        Log.d(TAG, "Failed to create session");
    }

    @Override
    public void wifiConfigSent() {
        Log.d(TAG, "Wifi config sent");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                prog1.setVisibility(View.INVISIBLE);
                tick1.setVisibility(View.VISIBLE);
                tick1.playAnimation();
                prog2.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void wifiConfigFailed(Exception e) {
        Log.d(TAG, "Wifi config failed");
    }

    @Override
    public void wifiConfigApplied() {
        Log.d(TAG, "Wifi config applied");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                prog2.setVisibility(View.INVISIBLE);
                tick2.setVisibility(View.VISIBLE);
                tick2.playAnimation();
                prog3.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void wifiConfigApplyFailed(Exception e) {
        Log.d(TAG, "Wifi config apply failed");
    }

    @Override
    public void provisioningFailedFromDevice(ESPConstants.ProvisionFailureReason failureReason) {
        Log.d(TAG, "Provision failed from device " + failureReason.toString());
    }

    @Override
    public void deviceProvisioningSuccess() {
        Log.d(TAG, "Provision succeeded");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                prog3.setVisibility(View.INVISIBLE);
                tick3.setVisibility(View.VISIBLE);
                tick3.playAnimation();
            }
        });
    }

    @Override
    public void onProvisioningFailed(Exception e) {
        Log.d(TAG, "Provisioning failed");
    }
}