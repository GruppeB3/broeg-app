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

public class ChooseBloomTime_frag extends Fragment implements View.OnClickListener {

    Button plusBtn, minusBtn, saveBtn;
    private View root;
    double amountBloomTime = 45;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        this.root = i.inflate(R.layout.fragment_choose_bloom_time, container, false);

        plusBtn = root.findViewById(R.id.ArrowUp_BloomTime);
        minusBtn = root.findViewById(R.id.ArrowDown_BloomTime);
        saveBtn = root.findViewById(R.id.Save_BloomTime);

        plusBtn.setOnClickListener(this);
        minusBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        this.amountBloomTime = PreferenceHelper.getDouble(preferences, "amountBloomTime", "45");

        return root;
    }

    @Override
    public void onClick(View ButtonClick) {
        if (ButtonClick == plusBtn) {
            amountBloomTime++;
            TextView tv = root.findViewById(R.id.bloomTime);
            tv.setText("Bloomtid i sekunder (" +amountBloomTime + ")");

        } else if (ButtonClick == minusBtn){
            amountBloomTime--;
            TextView tv = root.findViewById(R.id.bloomTime);
            tv.setText("Bloomtid i sekunder (" +amountBloomTime + ")");

        } else if (ButtonClick == saveBtn){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            PreferenceHelper.putDouble(preferences, "amountBloomTime", this.amountBloomTime);

            getActivity().onBackPressed();
        }

    }
}