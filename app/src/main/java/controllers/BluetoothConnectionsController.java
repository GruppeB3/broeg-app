package controllers;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import com.espressif.provisioning.ESPConstants;
import com.espressif.provisioning.ESPDevice;
import com.espressif.provisioning.ESPProvisionManager;
import com.espressif.provisioning.listeners.BleScanListener;
import com.espressif.provisioning.listeners.ProvisionListener;

import java.util.ArrayList;
import java.util.List;

import models.exceptions.BluetoothNotAvailableException;
import models.exceptions.BluetoothNotEnabledException;

public class BluetoothConnectionsController {

    private final BluetoothAdapter adapter;
    private List<BluetoothDevice> devices = new ArrayList<>();

    public BluetoothConnectionsController() throws BluetoothNotAvailableException, BluetoothNotEnabledException {

        this.adapter = BluetoothAdapter.getDefaultAdapter();

        if (this.adapter == null) {
            // Device doesn't support Bluetooth.
            throw new BluetoothNotAvailableException();
        }

        if (!bluetoothIsEnabled()) {
            // Bluetooth is not enabled for the adapter provided
            throw new BluetoothNotEnabledException();
        }

    }

    /**
     * Start a scan for Bluetooth devices nearby.<br>
     * There's a hardcoded prefix of "PROV_" meaning that the listener will only
     * be notified about devices where this prefix is being fulfilled.
     *
     * @param context
     * @param listener
     */
    public void startScanForNewDevices(Context context, BleScanListener listener) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return;

        ESPProvisionManager.getInstance(context).searchBleEspDevices("PROV_", listener);
    }

    /**
     * Send WiFi credentials to an ESP device.
     *
     * @param context
     * @param device
     * @param ssid
     * @param pwd
     */
    public void sendWifiCredentialsToDevice(Context context, BluetoothDevice device, String uuid, String ssid, String pwd, ProvisionListener listener) {
        ESPDevice esp = ESPProvisionManager.getInstance(context)
                .createESPDevice(ESPConstants.TransportType.TRANSPORT_BLE, ESPConstants.SecurityType.SECURITY_1);
        esp.connectBLEDevice(device, uuid);
        esp.provision(ssid, pwd, listener);
    }

    /**
     * Add a bluetooth device to the list of devices
     *
     * @param device
     */
    public void addDevice(BluetoothDevice device) {
        if (!this.devices.contains(device))
            this.devices.add(device);
    }

    /**
     * Get list of devices
     *
     * @return List
     */
    public List<BluetoothDevice> getDevices() {
        return devices;
    }

    /**
     * Aux method the check if Bluetooth is enabled for the acquired adapter.
     * If the adapter is `null` the method will return `false`
     *
     * @return boolean
     */
    public boolean bluetoothIsEnabled() {
        if (this.adapter != null) {
            return adapter.isEnabled();
        }

        return false;
    }

}
