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

public class BluetoothDeviceListAdapter extends RecyclerView.Adapter<BluetoothDeviceListAdapter.ViewHolder> {

    List<BluetoothDevice> devices;
    Context context;
    OnItemClickListener listener;

    public BluetoothDeviceListAdapter(List<BluetoothDevice> devices, OnItemClickListener listener, Context context) {
        this.devices = devices;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_bluetooth_connection_device, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(devices.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, position);
            }
        });
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

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}
