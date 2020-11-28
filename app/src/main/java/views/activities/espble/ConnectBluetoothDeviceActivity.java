package views.activities.espble;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.espressif.provisioning.DeviceConnectionEvent;
import com.espressif.provisioning.listeners.BleScanListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import controllers.EspBluetoothConnectionsController;
import dk.dtu.gruppeb3.broeg.app.R;
import models.exceptions.BluetoothNotAvailableException;
import models.exceptions.BluetoothNotEnabledException;
import views.adapters.BluetoothDeviceListAdapter;

public class ConnectBluetoothDeviceActivity extends AppCompatActivity implements View.OnClickListener, BluetoothDeviceListAdapter.OnItemClickListener, BleScanListener {

    private EspBluetoothConnectionsController controller;
    private Button searchNewDevicesBtn;
    private ProgressDialog spinnerDialog;
    private Map<BluetoothDevice, String> uuids = new HashMap<>();

    private static final int EVENT_DEVICE_CONNECTED = 1;
    private static final int EVENT_DEVICE_CONNECTION_FAILED = 2;
    private static final int EVENT_DEVICE_DISCONNECTED = 3;
    private static final int REQ_BLUETOOTH_PERMISSIONS_CODE = 1000;
    private static final String TAG = ConnectBluetoothDeviceActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_connections);

        searchNewDevicesBtn = findViewById(R.id.searchNewDevicesBtn);
        searchNewDevicesBtn.setOnClickListener(this);

        EventBus.getDefault().register(this);
        this.controller = EspBluetoothConnectionsController.getInstance();

        try {
            EspBluetoothConnectionsController.checkBluetoothComponents();
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
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQ_BLUETOOTH_PERMISSIONS_CODE) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                startBleScan();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == searchNewDevicesBtn && this.controller != null) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // Permissions have not been given for us to access Bluetooth on the device.
                String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
                ActivityCompat.requestPermissions(this, permissions, REQ_BLUETOOTH_PERMISSIONS_CODE);

                return;
            }

            startBleScan();
        }
    }

    private void startBleScan() {
        this.controller.startScanForNewDevices(getApplicationContext(), this);
        this.controller.getDevices().clear();
        spinnerDialog = ProgressDialog.show(this, "", "Searching for devices...");
    }

    @Override
    public void scanStartFailed() {
        spinnerDialog.cancel();
        Toast.makeText(this, "An error occurred while trying to scan for new devices", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPeripheralFound(BluetoothDevice device, ScanResult scanResult) {
        this.controller.addDevice(device);

        if (!this.uuids.containsKey(device)) {
            if (scanResult.getScanRecord().getServiceUuids() != null && scanResult.getScanRecord().getServiceUuids().size() > 0)
                this.uuids.put(device, scanResult.getScanRecord().getServiceUuids().get(0).toString());
        }

        Log.d(TAG, "Name: " + device.getName());
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


    @Override
    public void onItemClick(View view, int position) {
        final BluetoothDevice device = this.controller.getDevices().get(position);

        try {
            controller.connectToBleDevice(getApplicationContext(), device, uuids.get(device));
        } catch (BluetoothNotEnabledException | BluetoothNotAvailableException e) {
            // We really never should hit this point
            e.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(DeviceConnectionEvent event) {
        switch (event.getEventType()) {

            case EVENT_DEVICE_CONNECTED:
                Log.d(TAG, "Device connected");
                Intent i = new Intent(this, GetPOPCodeActivity.class);
                startActivity(i);
                finish();
                break;

            case EVENT_DEVICE_CONNECTION_FAILED:
                Log.d(TAG, "Received Bluetooth device connection failed event");
                Toast.makeText(this, "An error occurred while trying to connect to the device", Toast.LENGTH_SHORT).show();
                break;

            case EVENT_DEVICE_DISCONNECTED:
                Log.d(TAG, "Received Bluetooth device disconnected event");
                Toast.makeText(this, "Device disconnected", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}