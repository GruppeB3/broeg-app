package views;

import android.os.Bundle;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import dk.dtu.gruppeb3.broeg.app.R;
import helpers.PreferenceHelper;

public class ChooseBloomTime_frag extends Fragment implements View.OnClickListener {

    Button knap1, knap2, knap3;
    private View rod;
    double amountBloomTime = 45;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.activity_choose_bloom_time_frag, container, false);

        knap1 = rod.findViewById(R.id.ArrowUp_BloomTime);
        knap2 = rod.findViewById(R.id.ArrowDown_BloomTime);
        knap3 = rod.findViewById(R.id.Save_BloomTime);

        knap1.setOnClickListener(this);
        knap2.setOnClickListener(this);
        knap3.setOnClickListener(this);


        return rod;
    }

    @Override
    public void onClick(View ButtonClick) {
        if (ButtonClick == knap1) {
            amountBloomTime++;
            TextView tv = rod.findViewById(R.id.amountWater);
            tv.setText("Bloomtid i sekunder (" +amountBloomTime + ")");

        } else if (ButtonClick == knap2){
            amountBloomTime--;
            TextView tv = rod.findViewById(R.id.amountWater);
            tv.setText("Bloomtid i sekunder (" +amountBloomTime + ")");

        } else if (ButtonClick == knap3){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            PreferenceHelper.putDouble(preferences, "amountBloomTime", this.amountBloomTime);

            getActivity().onBackPressed();
        }

    }
}