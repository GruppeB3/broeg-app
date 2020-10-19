package views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import dk.dtu.gruppeb3.broeg.app.R;

public class ChooseAmountOfCoffee_frag extends Fragment implements View.OnClickListener {

    int amountOfCoffee; //Denne variabel skal gemmes
    Button knap1, knap2;
    private View rod;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState){
        String hjælpHtml = "Her skal vi have tilføjet pileknapper der øger eller reducerer mængden af kaffe i gram";
        View rod = i.inflate(R.layout.activity_choose_amount_of_coffee, container, false);

        knap1 = rod.findViewById(R.id.PilOp);
        knap2 = rod.findViewById(R.id.PilNed);

        knap1.setOnClickListener(this);
        knap2.setOnClickListener(this);

        return rod;e


    }

    @Override
    public void onClick(View ButtonClick) {

        long etTal = System.currentTimeMillis();

        if (ButtonClick == knap1) {
            TextView tv = rod.findViewById(R.id.CoffeeAmount);
            tv.setText(" "+etTal);

        } else if (ButtonClick == knap2){
            TextView tv = rod.findViewById(R.id.CoffeeAmount);
            //tv.setText("" --etTal); Vi skal trække tal fra her, måske er setText forkert metode?
        }

    }
}