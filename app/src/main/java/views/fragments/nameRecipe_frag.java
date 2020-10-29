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

import dk.dtu.gruppeb3.broeg.app.R;
import views.activities.MyRecipesActivity;

public class nameRecipe_frag extends Fragment implements View.OnClickListener {

    private View rod;
    TextView et;
    String opskriftNavn;
    Button knap1;


    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        this.rod = i.inflate(R.layout.fragment_name_recipe, container, false);

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
            preferences.edit().putString("OpskriftNavn",this.opskriftNavn).apply();
            Intent intent = new Intent(getActivity(), MyRecipesActivity.class);
            startActivity(intent);
        }
    }
}