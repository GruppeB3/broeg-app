package views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.espressif.provisioning.ESPDevice;

import controllers.EspBluetoothConnectionsController;
import dk.dtu.gruppeb3.broeg.app.R;

public class GetPOPCodeActivity extends AppCompatActivity implements View.OnClickListener{

    EditText popField;
    Button continueBtn;
    EspBluetoothConnectionsController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_pop_code);

        controller = EspBluetoothConnectionsController.getInstance();

        popField = findViewById(R.id.editPOPText);
        continueBtn = findViewById(R.id.setPopBtn);

        continueBtn.setOnClickListener(this);

        TextView deviceNameField = findViewById(R.id.deviceName);

        ESPDevice device = controller.getConnectedDevice(getApplicationContext());
        if (device != null)
            deviceNameField.setText(device.getDeviceName());
    }


    @Override
    public void onClick(View v) {
        if (v == continueBtn) {
            String pop = popField.getText().toString();
            controller.setProofOfPossession(getApplicationContext(), pop);

            Intent i = new Intent(this, GetWifiCredentialsActivity.class);
            startActivity(i);
            finish();
        }
    }
}