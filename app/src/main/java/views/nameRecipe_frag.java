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

public class nameRecipe_frag extends Fragment {

    private View rod;
    double amountOfCoffee;
    double amountBloomTime = 45;
    double temperature = 80;
    double amountBloomWater = 45;
    String grindSize;
    Button knap1;
    TextView opskriftNavn;
    EditText et;


    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        this.rod = i.inflate(R.layout.activity_choose_amount_of_coffee, container, false);

        et  = (EditText)rod.findViewById(R.id.nameRecipe);
        et.getText().toString();

        opskriftNavn = (TextView)rod.findViewById(R.id.nameRecipe);
        opskriftNavn.setText(et.getText().toString());


        return rod;
    }
}