package views.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import dk.dtu.gruppeb3.broeg.app.R;
import models.BrewBuilder;
import views.activities.MyRecipesActivity;

public class NameRecipe_frag extends Fragment implements View.OnClickListener, View.OnTouchListener {

    private View root;
    EditText et;
    String recipeName;
    Button saveBtn;


    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        this.root = i.inflate(R.layout.fragment_name_recipe, container, false);

        saveBtn = root.findViewById(R.id.Save_Recipe);
        et = root.findViewById(R.id.nameRecipe);

        saveBtn.setOnClickListener(this);
        et.setOnTouchListener(this);

        return root;
    }

    @Override
    public void onClick(View ButtonClick) {
        if (ButtonClick == saveBtn) {
            et = root.findViewById(R.id.nameRecipe);
            recipeName =et.getText().toString();

           String json = (new Gson()).toJson(BrewBuilder.getInstance().get());


            Intent intent = new Intent(getActivity(), MyRecipesActivity.class);
            intent.putExtra("brew", json);
            getActivity().finishAffinity(); //Need to clear backstack.
            startActivity(intent);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(v == et){
            et.setText("");
        }
        return false;
    }
}