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
        plusBtn.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                amountBloomTime++;
                TextView tv = rod.findViewById(R.id.bloomTime);
                tv.setText("Bloomtid i sekunder (" +amountBloomTime + ")");
            }
        }));
        minusBtn.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                amountBloomTime--;
                TextView tv = rod.findViewById(R.id.bloomTime);
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
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            SharedPreferences.Editor prefsEditor = preferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(this.amountBloomTime);
            PreferenceHelper.putDouble("amountBloomTime", json);
            prefsEditor.commit();

            getActivity().onBackPressed();
        }

    }

    private void updateText() {
        TextView tv = root.findViewById(R.id.bloomTime);
        tv.setText("Bloomtid i sekunder (" + amountBloomTime + ")");
    }
}