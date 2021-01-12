package views.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import dk.dtu.gruppeb3.broeg.app.R;
import models.BrewBuilder;
import views.RepeatListener;

public class ChooseBloomWaterAmount_frag extends Fragment implements View.OnClickListener {

    Button plusBtn, minusBtn, saveBtn;
    int amountBloomWater;
    private View root;


    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        this.root = i.inflate(R.layout.fragment_choose_bloom_water_amount, container, false);

        amountBloomWater = (int) BrewBuilder.getInstance().get().getBloomAmount();

        plusBtn = root.findViewById(R.id.ArrowUp_BloomAmount);
        minusBtn = root.findViewById(R.id.ArrowDown_BloomAmount);
        saveBtn = root.findViewById(R.id.Save_BloomAmount);

        plusBtn.setOnClickListener(this);
        minusBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);

        plusBtn.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                amountBloomWater++;
                TextView tv = root.findViewById(R.id.amountWater);
                tv.setText(amountBloomWater + "(ml)");
            }
        }));
        minusBtn.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                amountBloomWater--;
                TextView tv = root.findViewById(R.id.amountWater);
                tv.setText(amountBloomWater + "(ml)");
            }
        }));

        updateText();

        return root;
    }

    @Override
    public void onClick(View ButtonClick) {
        if (ButtonClick == plusBtn) {
            amountBloomWater++;
            updateText();

        } else if (ButtonClick == minusBtn){
            amountBloomWater--;
            updateText();

        } else if (ButtonClick == saveBtn){
            BrewBuilder.getInstance().bloomAmount(amountBloomWater);

            getActivity().onBackPressed();
        }

    }

    private void updateText() {
        TextView tv = root.findViewById(R.id.amountWater);
        tv.setText(amountBloomWater + "(ml)");
    }
}