package views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dk.dtu.gruppeb3.broeg.app.R;
import models.Brew;

public class MyRecipeListAdapter extends RecyclerView.Adapter<MyRecipeListAdapter.ViewHolder> {

    List<Brew> recipes;
    MyRecipeListButtonListener listener;

    public MyRecipeListAdapter(List<Brew> recipes, MyRecipeListButtonListener listener) {
        this.recipes = recipes;
        this.listener = listener;
    }

    public void setRecipes(List<Brew> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_myrecipes_recipe, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(recipes.get(position).getName());

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMyRecipeListButtonClick(Mode.NONE, position);
            }
        });

        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMyRecipeListButtonClick(Mode.EDIT, position);
            }
        });

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMyRecipeListButtonClick(Mode.DELETE, position);
            }
        });

        if (recipes.get(position).isSystem()) {
            // Hide action buttons if it's a system brew
            holder.editBtn.setVisibility(View.GONE);
            holder.deleteBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return this.recipes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView editBtn, deleteBtn;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.recipe_Name);
            editBtn = itemView.findViewById(R.id.editBtn);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
        }
    }

    public enum Mode {
        NONE, EDIT, DELETE;
    }

    public interface MyRecipeListButtonListener {
        void onMyRecipeListButtonClick(Mode mode, int position);
    }
}
