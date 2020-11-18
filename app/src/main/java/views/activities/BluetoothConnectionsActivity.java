package views.activities;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.espressif.provisioning.ESPConstants;
import com.espressif.provisioning.listeners.BleScanListener;
import com.espressif.provisioning.listeners.ProvisionListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import controllers.BluetoothConnectionsController;
import dk.dtu.gruppeb3.broeg.app.R;
import models.exceptions.BluetoothNotAvailableException;
import models.exceptions.BluetoothNotEnabledException;

public class BluetoothConnectionsActivity extends AppCompatActivity implements View.OnClickListener, BleScanListener, AdapterView.OnItemClickListener, ProvisionListener {

    private BluetoothConnectionsController controller;
    private Button searchNewDevicesBtn;

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

        final List<BluetoothDevice> devices = this.controller.getDevices();
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.activity_bluetooth_connections, R.id.devicesList, devices) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                BluetoothDevice device = devices.get(position);
                Log.d("espBleDeviceFound", "Name: " + device.getName() + ", Alias: " + device.getAlias());

                TextView description = findViewById(R.id.searchNewDevice);
                description.setText("Enhed: " + device.getName());

                return view;
            }
        };

        ListView listView = new ListView(this);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);
    }

    @Override
    public void onFailure(Exception e) {
        Toast.makeText(this, "An error occured while trying to scan for new devices", Toast.LENGTH_SHORT).show();
    }

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