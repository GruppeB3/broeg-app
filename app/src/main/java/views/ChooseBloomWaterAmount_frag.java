package views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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

    Button knap1, knap2, knap3;
    double amountBloomWater = 45;
    private View rod;


    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.activity_choose_bloom_water_amount_frag, container, false);

        knap1 = rod.findViewById(R.id.PilOpBloomWater);
        knap2 = rod.findViewById(R.id.PilNedBloomWater);
        knap3 = rod.findViewById(R.id.gemBloomVand);

        knap1.setOnClickListener(this);
        knap2.setOnClickListener(this);
        knap3.setOnClickListener(this);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        this.amountBloomWater = PreferenceHelper.getDouble(preferences, "amountBloomWater", "0");

        return rod;
    }

    @Override
    public void onClick(View ButtonClick) {
        if (ButtonClick == knap1) {
            amountBloomWater++;
            TextView tv = rod.findViewById(R.id.amountWater);
            tv.setText("Mængde af vand i ml (" +amountBloomWater + ")");

        } else if (ButtonClick == knap2){
            amountBloomWater--;
            TextView tv = rod.findViewById(R.id.amountWater);
            tv.setText("Mængde af vand i ml (" +amountBloomWater + ")");

        } else if (ButtonClick == knap3){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            PreferenceHelper.putDouble(preferences, "amountBloomWater", this.amountBloomWater);

            getActivity().onBackPressed();
        }

    }
}