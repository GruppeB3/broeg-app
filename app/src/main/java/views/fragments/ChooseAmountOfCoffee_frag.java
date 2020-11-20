package views.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import dk.dtu.gruppeb3.broeg.app.R;
import helpers.PreferenceHelper;
import models.BrewBuilder;
import views.RepeatListener;

/**
 * This fragment gives the user the opportunity to choose the amount of coffee they want to brew.
 */

public class ChooseAmountOfCoffee_frag extends Fragment implements View.OnClickListener {

    Button plusBtn, minusBtn, saveBtn;
    private View root;
    double amountOfCoffee;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState){
        this.root = i.inflate(R.layout.fragment_choose_amount_of_coffee, container, false);

        plusBtn = root.findViewById(R.id.ArrowUp_CoffeAmount);
        minusBtn = root.findViewById(R.id.ArrowDown_CoffeAmount);
        saveBtn = root.findViewById(R.id.Save_AmountCoffee);

        plusBtn.setOnClickListener(this);
        minusBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);

        plusBtn.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountOfCoffee++;
                TextView tv = root.findViewById(R.id.CoffeeAmount);
                tv.setText("Mængde kaffe i gram (" + amountOfCoffee + ")");
            }
        }));
        minusBtn.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                amountOfCoffee--;
                TextView tv = root.findViewById(R.id.CoffeeAmount);
                tv.setText("Mængde kaffe i gram (" + amountOfCoffee + ")");
            }
        }));

        updateText();

        return root;


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
            BrewBuilder.getInstance().groundCoffeeAmount(amountOfCoffee);

            getActivity().onBackPressed();
        }

    }

    private void updateText() {
        TextView tv = root.findViewById(R.id.CoffeeAmount);
        tv.setText("Mængde kaffe i gram (" + amountOfCoffee + ")");
    }
}