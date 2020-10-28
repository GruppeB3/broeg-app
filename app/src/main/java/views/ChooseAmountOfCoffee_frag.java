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
import android.widget.TextView;

import dk.dtu.gruppeb3.broeg.app.R;
import helpers.PreferenceHelper;

/**
 * This fragment gives the user the opportunity to choose the amount of coffee they want to brew.
 */

public class ChooseAmountOfCoffee_frag extends Fragment implements View.OnClickListener {

    Button knap1, knap2, knap3;
    private View rod;
    double amountOfCoffee;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState){
        this.rod= i.inflate(R.layout.activity_choose_amount_of_coffee, container, false);

        knap1 = rod.findViewById(R.id.ArrowUp_CoffeAmount);
        knap2 = rod.findViewById(R.id.ArrowDown_CoffeAmount);
        knap3 = rod.findViewById(R.id.Save_AmountCoffee);

        knap1.setOnClickListener(this);
        knap2.setOnClickListener(this);
        knap3.setOnClickListener(this);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        this.amountOfCoffee = PreferenceHelper.getDouble(preferences, "amountOfCoffee", "0");

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
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            PreferenceHelper.putDouble(preferences, "amountOfCoffee", this.amountOfCoffee);

            getActivity().onBackPressed();
        }

    }
}