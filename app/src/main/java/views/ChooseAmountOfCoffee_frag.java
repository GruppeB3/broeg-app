package views;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import dk.dtu.gruppeb3.broeg.app.R;
import models.Application;

/**
 * This fragment gives the user the opportunity to choose the amount of coffee they want to brew.
 * @Author Gustav Kirkholt
 */

public class ChooseAmountOfCoffee_frag extends Fragment implements View.OnClickListener {

    Button knap1, knap2, knap3;
    private View rod;
    int amountOfCoffee;
    SharedPreferences prefs;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState){
        this.rod= i.inflate(R.layout.activity_choose_amount_of_coffee, container, false);

        knap1 = rod.findViewById(R.id.PilOp);
        knap2 = rod.findViewById(R.id.PilNed);
        knap3 = rod.findViewById(R.id.Gem);

        knap1.setOnClickListener(this);
        knap2.setOnClickListener(this);
        knap3.setOnClickListener(this);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity();


        return rod;


    }

    @Override
    public void onClick(View ButtonClick) {



        if (ButtonClick == knap1) {
            amountOfCoffee++;
            TextView tv = rod.findViewById(R.id.CoffeeAmount);
            tv.setText("Mængde kaffe i gram (" +amountOfCoffee + ")");

        } else if (ButtonClick == knap2){
            amountOfCoffee--;
            TextView tv = rod.findViewById(R.id.CoffeeAmount);
            tv.setText("Mængde kaffe i gram (" +amountOfCoffee + ")");

        } else if (ButtonClick == knap3){
            prefs.edit().putInt("Mængde kaffe", amountOfCoffee).apply();
            getActivity().onBackPressed();
        }

    }
}
