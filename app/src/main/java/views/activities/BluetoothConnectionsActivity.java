package views.activities;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.espressif.provisioning.listeners.BleScanListener;

import java.util.Random;

import controllers.BluetoothConnectionsController;
import dk.dtu.gruppeb3.broeg.app.R;
import models.exceptions.BluetoothNotAvailableException;
import models.exceptions.BluetoothNotEnabledException;

public class BluetoothConnectionsActivity extends AppCompatActivity implements View.OnClickListener, BleScanListener {

    private BluetoothConnectionsController controller;
    private Button searchNewDevicesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_connections);

        searchNewDevicesBtn = findViewById(R.id.searchNewDevices);
        searchNewDevicesBtn.setOnClickListener(this);

        try {
            this.controller = new BluetoothConnectionsController();
        } catch (BluetoothNotAvailableException e) {
            Toast.makeText(this,
                    "It doesn't seem like we can connect to the Bluetooth interface on your device." +
                            "\nPlease make sure your device supports Bluetooth,",
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
            }
        }
    }

    @Override
    public void scanStartFailed() {
        Toast.makeText(this, "An error occurred while trying to scan for new devices", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPeripheralFound(BluetoothDevice device, ScanResult scanResult) {
        this.controller.addDevice(device);
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void scanCompleted() {
        Toast.makeText(this, "Scan for new devices completed", Toast.LENGTH_SHORT).show();

        for (BluetoothDevice device : this.controller.getDevices()) {
            Log.d("espBleDeviceFound", "Name: " + device.getName() + ", Alias: " + device.getAlias());
        }
    }

    @Override
    public void onFailure(Exception e) {
        Toast.makeText(this, "An error occured while trying to scan for new devices", Toast.LENGTH_SHORT).show();
    }
}