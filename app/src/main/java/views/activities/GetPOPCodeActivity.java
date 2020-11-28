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
import models.exceptions.BluetoothNotAvailableException;
import models.exceptions.BluetoothNotEnabledException;

public class GetPOPCodeActivity extends AppCompatActivity implements View.OnClickListener{

    EditText popField;
    Button continueBtn;
    BluetoothConnectionsController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_pop_code);

        try {
            controller = BluetoothConnectionsController.getInstance();
        } catch (BluetoothNotEnabledException | BluetoothNotAvailableException e) {
            e.printStackTrace();
        }

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

            // TODO: Start Wifi activity
        }
    }
}