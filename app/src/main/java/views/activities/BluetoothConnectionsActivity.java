package views.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.provisioning.ESPConstants;
import com.espressif.provisioning.listeners.BleScanListener;
import com.espressif.provisioning.listeners.ProvisionListener;

import java.util.List;
import java.util.Random;

import controllers.BluetoothConnectionsController;
import dk.dtu.gruppeb3.broeg.app.R;
import models.exceptions.BluetoothNotAvailableException;
import models.exceptions.BluetoothNotEnabledException;
import views.adapters.BluetoothDeviceListAdapter;

public class BluetoothConnectionsActivity extends AppCompatActivity implements View.OnClickListener, BleScanListener, ProvisionListener {

    private BluetoothConnectionsController controller;
    private Button searchNewDevicesBtn;
    private ProgressDialog spinnerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_connections);

        searchNewDevicesBtn = findViewById(R.id.searchNewDevicesBtn);
        searchNewDevicesBtn.setOnClickListener(this);

        try {
            this.controller = new BluetoothConnectionsController();
        } catch (BluetoothNotAvailableException e) {
            Toast.makeText(this,
                    "It doesn't seem like we can connect to the Bluetooth interface on your device." +
                            "\nPlease make sure your device supports Bluetooth!",
                    Toast.LENGTH_SHORT).show();

        } catch (BluetoothNotEnabledException e) {
            int requestCode = (new Random()).nextInt();

            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, requestCode, null);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == searchNewDevicesBtn) {
            if (this.controller != null) {

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // Permissions have not been given for us to access Bluetooth on the device.
                    String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
                    ActivityCompat.requestPermissions(this, permissions, 1);
                }

                this.controller.startScanForNewDevices(this, this);

                spinnerDialog = ProgressDialog.show(this, "", "Søger efter enheder...");
            }
        } else if (v == findViewById(R.id.deviceListElement)) {
            // TODO: Fill in code to provision device
        }
    }

    @Override
    public void scanStartFailed() {
        spinnerDialog.cancel();
        Toast.makeText(this, "An error occurred while trying to scan for new devices", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPeripheralFound(BluetoothDevice device, ScanResult scanResult) {
        this.controller.addDevice(device);

        // NOTE: Debug step
        Log.d("espBleDeviceFound", "Name: " + device.getName());
    }

    @Override
    public void scanCompleted() {
        spinnerDialog.cancel();

        List<BluetoothDevice> devices = this.controller.getDevices();

        RecyclerView listView = this.findViewById(R.id.devicesList);
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.scrollToPosition(0);

        BluetoothDeviceListAdapter recyclerViewAdapter = new BluetoothDeviceListAdapter(devices, this, this);

        listView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onFailure(Exception e) {
        spinnerDialog.cancel();
        Toast.makeText(this, "An error occured while trying to scan for new devices", Toast.LENGTH_SHORT).show();
    }

/*
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BluetoothDevice device = this.controller.getDevices().get(position);
        final Map<String, String> credentials = new HashMap<>();


        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("SSID");
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                credentials.put("ssid", input.getText().toString());
            }
        });

        alert.show();

        input.setText("");
        alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                credentials.put("pwd", input.getText().toString());
            }
        });

        this.controller.sendWifiCredentialsToDevice(this,
                device,
                credentials.get("ssid"),
                credentials.get("pwd"),
                this);
    }
*/

    @Override
    public void createSessionFailed(Exception e) {

    }

    @Override
    public void wifiConfigSent() {

    }

    @Override
    public void wifiConfigFailed(Exception e) {

    }

    @Override
    public void wifiConfigApplied() {

    }

    @Override
    public void wifiConfigApplyFailed(Exception e) {

    }

    @Override
    public void provisioningFailedFromDevice(ESPConstants.ProvisionFailureReason failureReason) {

    }

    @Override
    public void deviceProvisioningSuccess() {

    }

    @Override
    public void onProvisioningFailed(Exception e) {

    }
}