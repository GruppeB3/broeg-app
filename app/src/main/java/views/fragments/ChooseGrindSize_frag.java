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

import com.google.gson.Gson;

import dk.dtu.gruppeb3.broeg.app.R;

/**
 * This fragment gives the user the opportunity to choose between 3 types af grind size.
 */

public class ChooseGrindSize_frag extends Fragment implements View.OnClickListener {

    Button fineBtn, mediumBtn, coarseBtn, saveBtn;
    String grindSize;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.fragment_choose_grind_size, container, false);

        fineBtn = rod.findViewById(R.id.Fine);
        mediumBtn = rod.findViewById(R.id.Medium);
        coarseBtn = rod.findViewById(R.id.Coarse);
        saveBtn = rod.findViewById(R.id.Save_GrindSize);

        fineBtn.setOnClickListener(this);
        mediumBtn.setOnClickListener(this);
        coarseBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);


        return rod;
    }

    @Override
    public void onClick(View ButtonClick) {

        if (ButtonClick == fineBtn) {
            grindSize="Fine";
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            SharedPreferences.Editor prefsEditor = preferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(this.grindSize);
            prefsEditor.putString("grindSize", json);
            prefsEditor.commit();

            getActivity().onBackPressed();

        } else if (ButtonClick == mediumBtn) {
            grindSize="Medium";
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            SharedPreferences.Editor prefsEditor = preferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(this.grindSize);
            prefsEditor.putString("grindSize", json);
            prefsEditor.commit();

            getActivity().onBackPressed();

        } else if (ButtonClick == coarseBtn) {
            grindSize="Coarse";

        } else if (ButtonClick == saveBtn) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            SharedPreferences.Editor prefsEditor = preferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(this.grindSize);
            prefsEditor.putString("grindSize", json);
            prefsEditor.commit();

            getActivity().onBackPressed();
        }
    }
}