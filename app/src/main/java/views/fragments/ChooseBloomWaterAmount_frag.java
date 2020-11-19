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
import views.RepeatListener;

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

        plusBtn.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                amountBloomWater++;
                TextView tv = root.findViewById(R.id.amountWater);
                tv.setText("Mængde af vand i ml (" +amountBloomWater + ")");
            }
        }));
        minusBtn.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                amountBloomWater--;
                TextView tv = root.findViewById(R.id.amountWater);
                tv.setText("Mængde af vand i ml (" + amountBloomWater + ")");
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
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            SharedPreferences.Editor prefsEditor = preferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(this.amountBloomWater);
            PreferenceHelper.putDouble("amountBloomWater", json);
            prefsEditor.commit();

            getActivity().onBackPressed();
        }

    }

    private void updateText() {
        TextView tv = root.findViewById(R.id.amountWater);
        tv.setText("Mængde af vand i ml (" + amountBloomWater + ")");
    }
}