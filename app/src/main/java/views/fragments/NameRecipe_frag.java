package views.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import dk.dtu.gruppeb3.broeg.app.R;
import views.activities.MyRecipesActivity;

public class NameRecipe_frag extends Fragment implements View.OnClickListener {

    private View root;
    TextView et;
    String recipeName;
    Button saveBtn;


    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        this.root = i.inflate(R.layout.fragment_name_recipe, container, false);

        saveBtn = root.findViewById(R.id.Save_Recipe);

        saveBtn.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View ButtonClick) {
        if (ButtonClick == saveBtn) {
            et= root.findViewById(R.id.nameRecipe);
            recipeName =et.getText().toString();

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            SharedPreferences.Editor prefsEditor = preferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(this.opskriftNavn);
            prefsEditor.putString("recipeName", json);
            prefsEditor.commit();


            Intent intent = new Intent(getActivity(), MyRecipesActivity.class);
            startActivity(intent);
        }
    }
}