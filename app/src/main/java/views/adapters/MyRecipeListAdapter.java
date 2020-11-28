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

public class MyRecipeListAdapter extends RecyclerView.Adapter {

    List<Brew> recipes;
    MyRecipeListButtonListener listener;

    public MyRecipeListAdapter(List<Brew> recipes, MyRecipeListButtonListener listener) {
        this.recipes = recipes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_myrecipes_recipe, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder vh = (ViewHolder) holder;
            vh.name.setText(recipes.get(position).getName());

            vh.editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onMyRecipeListButtonClick(Mode.EDIT, position);
                }
            });

            vh.deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onMyRecipeListButtonClick(Mode.DELETE, position);
                }
            });
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
        EDIT, DELETE;
    }

    public interface MyRecipeListButtonListener {
        void onMyRecipeListButtonClick(Mode mode, int position);
    }
}
