package views;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import dk.dtu.gruppeb3.broeg.app.R;

/**
 * This fragment gives the user the opportunity to choose the amount of coffee they want to brew.
 * @Author Gustav Kirkholt
 */

public class ChooseAmountOfCoffee_frag extends Fragment implements View.OnClickListener {

    public int amountOfCoffee = 0; //Denne variabel skal gemmes
    Button knap1, knap2, knap3;
    private View rod;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState){
        View rod = i.inflate(R.layout.activity_choose_amount_of_coffee, container, false);
        

        knap1 = rod.findViewById(R.id.PilOp);
        knap2 = rod.findViewById(R.id.PilNed);
        knap3 = rod.findViewById(R.id.Gem);

        knap1.setOnClickListener(this);
        knap2.setOnClickListener(this);
        knap3.setOnClickListener(this);

        return rod;


    }

    @Override
    public void onClick(View ButtonClick) {

        long etTal = 1;

        if (ButtonClick == knap1) {
            TextView tv = rod.findViewById(R.id.CoffeeAmount);
            tv.setText("Mængde kaffe i gram "+etTal);

        } else if (ButtonClick == knap2){
            TextView tv = rod.findViewById(R.id.CoffeeAmount);
            tv.setText("Mængde kaffe i gram " +(-etTal));
        } else if (ButtonClick == knap3){
            Bundle result = new Bundle();
            result.putInt("bundleKey", amountOfCoffee);
            getActivity().onBackPressed();
        }

    }
}