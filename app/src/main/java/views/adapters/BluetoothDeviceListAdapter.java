package views.adapters;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dk.dtu.gruppeb3.broeg.app.R;

public class BluetoothDeviceListAdapter extends RecyclerView.Adapter {

    List<BluetoothDevice> devices;
    Context context;
    View.OnClickListener onClickListener;

    public BluetoothDeviceListAdapter(List<BluetoothDevice> devices, View.OnClickListener listener, Context context) {
        this.devices = devices;
        this.onClickListener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_bluetooth_connection_device, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder vh = (ViewHolder) holder;
            vh.name.setText(devices.get(position).getName());
            vh.itemView.setOnClickListener(this.onClickListener);
        }
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.deviceText);
        }
    }

}
