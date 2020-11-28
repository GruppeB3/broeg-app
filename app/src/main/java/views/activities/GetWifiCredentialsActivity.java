package views.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.espressif.provisioning.ESPDevice;

import controllers.BluetoothConnectionsController;
import dk.dtu.gruppeb3.broeg.app.R;

public class GetWifiCredentialsActivity extends AppCompatActivity implements View.OnClickListener {

    EditText ssidField, pwdField;
    Button continueBtn;
    BluetoothConnectionsController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_wifi_credentials);

        controller = BluetoothConnectionsController.getInstance();

        ssidField = findViewById(R.id.ssidText);
        pwdField = findViewById(R.id.pwdText);
        continueBtn = findViewById(R.id.connectBtn);

        continueBtn.setOnClickListener(this);

        TextView deviceNameField = findViewById(R.id.deviceName);

        ESPDevice device = controller.getConnectedDevice(getApplicationContext());
        if (device != null)
            deviceNameField.setText(device.getDeviceName());
    }

    @Override
    public void onClick(View v) {
        if (v == continueBtn) {
            String ssid = ssidField.getText().toString();
            String password = pwdField.getText().toString();

            // TODO: Move to connect intent

            finish();
        }
    }
}