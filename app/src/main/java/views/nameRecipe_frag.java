package views;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import dk.dtu.gruppeb3.broeg.app.R;
import helpers.PreferenceHelper;

public class nameRecipe_frag extends Fragment implements View.OnClickListener {

    private View rod;
    TextView tv;
    EditText et;
    TextView opskriftNavn;
    Button knap1;


    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        this.rod = i.inflate(R.layout.activity_choose_amount_of_coffee, container, false);

        knap1 = rod.findViewById(R.id.gemOpskrift);

        knap1.setOnClickListener(this);

        et  = (EditText)rod.findViewById(R.id.nameRecipe);
        et.getText().toString();

        opskriftNavn = (TextView)rod.findViewById(R.id.nameRecipe);
        opskriftNavn.setText(et.getText().toString());

        return rod;
    }

    @Override
    public void onClick(View ButtonClick) {
        if (ButtonClick == knap1){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            preferences.edit().putString("OpskriftNavn", String.valueOf(this.opskriftNavn)).apply();
            getActivity().onBackPressed();
    }
}