package views.activities.espble;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.espressif.provisioning.ESPDevice;

import controllers.EspBluetoothConnectionsController;
import dk.dtu.gruppeb3.broeg.app.R;
import views.activities.BaseActivity;

public class GetPOPCodeActivity extends BaseActivity implements View.OnClickListener{

    EditText popField;
    Button continueBtn;
    EspBluetoothConnectionsController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentLayout(R.layout.activity_get_pop_code).hideMenu();

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
        super.onClick(v);
        if (v == continueBtn) {
            String pop = popField.getText().toString();
            controller.setProofOfPossession(getApplicationContext(), pop);

            Intent i = new Intent(this, GetWifiCredentialsActivity.class);
            startActivity(i);
            finish();
        }
    }
}