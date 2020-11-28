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

    private static final BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
    private static final List<BluetoothDevice> devices = new ArrayList<>();

    private static BluetoothConnectionsController instance;

    private BluetoothConnectionsController() throws BluetoothNotAvailableException, BluetoothNotEnabledException {

        if (adapter == null) {
            // Device doesn't support Bluetooth.
            throw new BluetoothNotAvailableException();
        }

        if (!bluetoothIsEnabled()) {
            // Bluetooth is not enabled for the adapter provided
            throw new BluetoothNotEnabledException();
        }

    }

    /**
     * Get singleton instance
     *
     * @return
     * @throws BluetoothNotEnabledException
     * @throws BluetoothNotAvailableException
     */
    public static BluetoothConnectionsController getInstance() throws BluetoothNotEnabledException, BluetoothNotAvailableException {

        if (instance == null) {
            instance = new BluetoothConnectionsController();
        }

        return instance;
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
     * Connect to an BLE device
     *
     * @param context
     * @param device
     * @param uuid
     */
    public void connectToBleDevice(Context context, BluetoothDevice device, String uuid) {
        ESPProvisionManager manager = ESPProvisionManager.getInstance(context);

        if (manager.getEspDevice() != null)
            manager.getEspDevice().disconnectDevice();

        manager.createESPDevice(ESPConstants.TransportType.TRANSPORT_BLE, ESPConstants.SecurityType.SECURITY_1);
        manager.getEspDevice().connectBLEDevice(device, uuid);
    }

    /**
     * Set proof of possession for device
     *
     * @param context
     * @param pop
     */
    public void setProofOfPossession(Context context, String pop) {
        ESPProvisionManager manager = ESPProvisionManager.getInstance(context);

        if (manager.getEspDevice() != null) {
            manager.getEspDevice().setProofOfPossession(pop);
        }
    }

    /**
     * Send WiFi credentials to an ESP device.
     *
     * @param context
     * @param ssid
     * @param pwd
     */
    public void sendWifiCredentialsToDevice(Context context, String ssid, String pwd, ProvisionListener listener) {
        ESPProvisionManager manager = ESPProvisionManager.getInstance(context);

        if (manager.getEspDevice() != null) {
            manager.getEspDevice().provision(ssid, pwd, listener);
        }
    }

    /**
     * Add a bluetooth device to the list of devices
     *
     * @param device
     */
    public void addDevice(BluetoothDevice device) {
        if (!devices.contains(device))
            devices.add(device);
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
     * Get the connected device.<br>Returns null if a device is not connected
     *
     * @param context
     * @return
     */
    public ESPDevice getConnectedDevice(Context context) {
        ESPProvisionManager manager = ESPProvisionManager.getInstance(context);
        return manager.getEspDevice();
    }

    /**
     * Aux method the check if Bluetooth is enabled for the acquired adapter.
     * If the adapter is `null` the method will return `false`
     *
     * @return boolean
     */
    public boolean bluetoothIsEnabled() {
        if (adapter != null) {
            return adapter.isEnabled();
        }

        return false;
    }

}
