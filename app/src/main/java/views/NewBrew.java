package views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import dk.dtu.gruppeb3.broeg.app.R;

public class NewBrew extends AppCompatActivity implements View.OnClickListener {
    private Button knap1, knap2, knap3, knap4, knap5, knap6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_brew);

        knap1 = findViewById(R.id.GroundCoffeeAmount);
        knap2 = findViewById(R.id.GrindSize);
        knap3 = findViewById(R.id.BrewingTemperature);
        knap4 = findViewById(R.id.BloomWater);
        knap5 = findViewById(R.id.BloomTime);
        knap6 = findViewById(R.id.Save);


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
        getFragmentManager().beginTransaction()
                .replace(R.id.fragmentindhold, new ChooseAmountOfCoffee_frag())
                .addToBackStack(null)
                .commit();
        //if (ClickButton == knap1) {
            //Intent i = new Intent(this, ChooseAmountOfCoffee_frag.class);
            //startActivity(i);

        }

    }

}

}