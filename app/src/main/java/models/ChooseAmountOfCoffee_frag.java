package models;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import dk.dtu.gruppeb3.broeg.app.R;

public class ChooseAmountOfCoffee_frag extends Fragment implements View.OnClickListener {

    int amountOfCoffee; //Denne variabel skal gemmes
    Button knap1, knap2;
    private View rod;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        String hjælpHtml = "Her skal vi have tilføjet pileknapper der øger eller reducerer mængden af kaffe i gram";

        knap1 = rod.findViewById(R.id.PilOp);
        knap2 = rod.findViewById(R.id.PilNed);

        knap1.setOnClickListener(this);
        knap2.setOnClickListener(this);

        return rod; //Der skal returneres amountOfCoffee


    }

    @Override
    public void onClick(View ButtonClick) {

    }
}