package views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dk.dtu.gruppeb3.broeg.app.R;
import models.Brewer;
import views.listeners.OnRecyclerViewItemClickListener;

public class SelectBrewerListAdapter extends RecyclerView.Adapter<SelectBrewerListAdapter.ViewHolder> {

    private ArrayList<Brewer> brewers;
    private OnRecyclerViewItemClickListener listener;

    public SelectBrewerListAdapter(ArrayList<Brewer> brewers, OnRecyclerViewItemClickListener listener) {
        this.brewers = brewers;
        this.listener = listener;
    }

    public void setBrewers(ArrayList<Brewer> brewers) {
        this.brewers = brewers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_select_brewer_list_elem, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(this.brewers.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return brewers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public ViewHolder(View viewItem) {
            super(viewItem);
            name = viewItem.findViewById(R.id.deviceText);
        }
    }
}
