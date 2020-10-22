    package controllers;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

import java.util.ArrayList;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class BluetoothConnectionsController {

    private static final int REQUEST_BT_ENABLE_CODE = 1;

    private Activity activity;
    private BluetoothConnectionsController instance;
    private BluetoothAdapter adapter;

    public BluetoothConnectionsController() {

        this.adapter = BluetoothAdapter.getDefaultAdapter();

        if (this.adapter == null) {
            // Device doesn't support Bluetooth.
            Toast.makeText(this.activity, "Bluetooth is not available. Please make sure that it is enabled and available on your device", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!adapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(this.activity, enableBtIntent, REQUEST_BT_ENABLE_CODE, null);
        }

    }

    public ArrayList<BluetoothDevice> getDevices() {

        final ArrayList<BluetoothDevice> devices = new ArrayList<>();

        // Create a BroadcastReceiver for ACTION_FOUND.
        final BroadcastReceiver receiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    // Discovery has found a device. Get the BluetoothDevice
                    // object and its info from the Intent.
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    devices.add(device);
                }
            }
        };

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        activity.registerReceiver(receiver, filter);

        // Don't forget to unregister again...
        activity.unregisterReceiver(receiver);

        return devices;

    }

}
