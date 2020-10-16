package models;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import dk.dtu.gruppeb3.broeg.app.R;

public class NewBrew extends Fragment implements View.OnClickListener {
    private Button knap1, knap2, knap3, knap4, knap5, knap6;
    private View rod;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState){
        rod = i.inflate(R.layout.activity_new_brew, container, false);

        knap1 = rod.findViewById(R.id.GroundCoffeeAmount);
        knap2 = rod.findViewById(R.id.GrindSize);
        knap3 = rod.findViewById(R.id.BrewingTemperature);
        knap4 = rod.findViewById(R.id.BloomWater);
        knap5 = rod.findViewById(R.id.BloomTime);
        knap6 = rod.findViewById(R.id.Save);


        knap1.setOnClickListener(this);
        knap2.setOnClickListener(this);
        knap3.setOnClickListener(this);
        knap4.setOnClickListener(this);
        knap5.setOnClickListener(this);
        knap6.setOnClickListener(this);

    }

    @Override
    public void onClick(View ClickButton) {
        if (ClickButton == knap1){
            Intent i = new Intent(getActivity(), ChooseAmountOfCoffee.class);
            startActivity(i);
        }

    }
}