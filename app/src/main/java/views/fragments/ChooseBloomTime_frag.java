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

import com.google.gson.Gson;

import dk.dtu.gruppeb3.broeg.app.R;
import helpers.PreferenceHelper;
import models.BrewBuilder;
import views.RepeatListener;

public class ChooseBloomTime_frag extends Fragment implements View.OnClickListener {

    Button plusBtn, minusBtn, saveBtn;
    private View root;
    int amountBloomTime = 45;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        this.root = i.inflate(R.layout.fragment_choose_bloom_time, container, false);

        plusBtn = root.findViewById(R.id.ArrowUp_BloomTime);
        minusBtn = root.findViewById(R.id.ArrowDown_BloomTime);
        saveBtn = root.findViewById(R.id.Save_BloomTime);

        plusBtn.setOnClickListener(this);
        minusBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);

        plusBtn.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                amountBloomTime++;
                TextView tv = root.findViewById(R.id.bloomTime);
                tv.setText("Bloomtid i sekunder (" +amountBloomTime + ")");
            }
        }));
        minusBtn.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                amountBloomTime--;
                TextView tv = root.findViewById(R.id.bloomTime);
                tv.setText("Bloomtid i sekunder (" + amountBloomTime + ")");
            }
        }));

        updateText();

        return root;
    }

    @Override
    public void onClick(View ButtonClick) {
        if (ButtonClick == plusBtn) {
            amountBloomTime++;
            updateText();

        } else if (ButtonClick == minusBtn){
            amountBloomTime--;
            updateText();

        } else if (ButtonClick == saveBtn){
            BrewBuilder.getInstance().bloomTime(amountBloomTime);

            getActivity().onBackPressed();
        }

    }

    private void updateText() {
        TextView tv = root.findViewById(R.id.bloomTime);
        tv.setText("Bloomtid i sekunder (" + amountBloomTime + ")");
    }
}