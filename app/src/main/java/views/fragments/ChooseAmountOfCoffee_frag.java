package views.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;

import dk.dtu.gruppeb3.broeg.app.R;
import helpers.PreferenceHelper;
import views.activities.NewRecipeActivity;

/**
 * This fragment gives the user the opportunity to choose the amount of coffee they want to brew.
 */

public class ChooseAmountOfCoffee_frag extends Fragment implements View.OnClickListener {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    Button plusBtn, minusBtn, saveBtn;
    private View rod;
    double amountOfCoffee;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        this.rod= i.inflate(R.layout.fragment_choose_amount_of_coffee, container, false);

        /**DisplayMetrics dm = new DisplayMetrics();

        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getActivity().getWindow().setLayout((int)(width*.5),(int)(height*.5));*/


        plusBtn = rod.findViewById(R.id.ArrowUp_CoffeAmount);
        minusBtn = rod.findViewById(R.id.ArrowDown_CoffeAmount);
        saveBtn = rod.findViewById(R.id.Save_AmountCoffee);

        plusBtn.setOnClickListener(this);
        minusBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        this.amountOfCoffee = PreferenceHelper.getDouble(preferences, "amountOfCoffee", "0");

        updateText();

        return rod;

    }



    @Override
    public void onClick(View ButtonClick) {

        if (ButtonClick == plusBtn) {
            amountOfCoffee++;
            updateText();

        } else if (ButtonClick == minusBtn){
            amountOfCoffee--;
            updateText();

        } else if (ButtonClick == saveBtn){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            PreferenceHelper.putDouble(preferences, "amountOfCoffee", this.amountOfCoffee);

            getActivity().onBackPressed();
        }

    }

        private void updateText() {
        TextView tv = rod.findViewById(R.id.CoffeeAmount);
        tv.setText("Mængde kaffe i gram (" + amountOfCoffee + ")");
    }
}