package views.fragments;

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

public class ChooseBloomWaterAmount_frag extends Fragment implements View.OnClickListener {

    Button plusBtn, minusBtn, saveBtn;
    double amountBloomWater = 45;
    private View root;


    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        this.root = i.inflate(R.layout.fragment_choose_bloom_water_amount, container, false);

        plusBtn = root.findViewById(R.id.ArrowUp_BloomAmount);
        minusBtn = root.findViewById(R.id.ArrowDown_BloomAmount);
        saveBtn = root.findViewById(R.id.Save_BloomAmount);

        plusBtn.setOnClickListener(this);
        minusBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        this.amountBloomWater = PreferenceHelper.getDouble(preferences, "amountBloomWater", "0");

        return root;
    }

    @Override
    public void onClick(View ButtonClick) {
        if (ButtonClick == plusBtn) {
            amountBloomWater++;
            TextView tv = root.findViewById(R.id.amountWater);
            tv.setText("Mængde af vand i ml (" +amountBloomWater + ")");

        } else if (ButtonClick == minusBtn){
            amountBloomWater--;
            TextView tv = root.findViewById(R.id.amountWater);
            tv.setText("Mængde af vand i ml (" +amountBloomWater + ")");

        } else if (ButtonClick == saveBtn){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            PreferenceHelper.putDouble(preferences, "amountBloomWater", this.amountBloomWater);

            getActivity().onBackPressed();
        }

    }
}