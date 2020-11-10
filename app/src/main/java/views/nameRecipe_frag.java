package views;

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

import static android.content.Context.MODE_PRIVATE;

public class nameRecipe_frag extends Fragment implements View.OnClickListener {

    private View rod;
    TextView et;
    String opskriftNavn;
    Button knap1;


    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        this.rod = i.inflate(R.layout.activity_name_recipe_frag, container, false);

        knap1 = rod.findViewById(R.id.Save_Recipe);

        knap1.setOnClickListener(this);

        return rod;
    }

    @Override
    public void onClick(View ButtonClick) {
        if (ButtonClick == knap1) {
            et=rod.findViewById(R.id.nameRecipe);
            opskriftNavn=et.getText().toString();

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
