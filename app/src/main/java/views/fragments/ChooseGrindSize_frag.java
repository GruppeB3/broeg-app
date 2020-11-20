package views.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;

import dk.dtu.gruppeb3.broeg.app.R;
import models.BrewBuilder;
import models.enums.GrindSize;

/**
 * This fragment gives the user the opportunity to choose between 3 types af grind size.
 */

public class ChooseGrindSize_frag extends Fragment implements View.OnClickListener {

    Button fineBtn, mediumBtn, coarseBtn, saveBtn;


    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View root = i.inflate(R.layout.fragment_choose_grind_size, container, false);

        fineBtn = root.findViewById(R.id.Fine);
        mediumBtn = root.findViewById(R.id.Medium);
        coarseBtn = root.findViewById(R.id.Coarse);


        fineBtn.setOnClickListener(this);
        mediumBtn.setOnClickListener(this);
        coarseBtn.setOnClickListener(this);



        return root;
    }

    @Override
    public void onClick(View ButtonClick) {

        if (ButtonClick == fineBtn) {
            BrewBuilder.getInstance().grindSize(GrindSize.FINE);


            getActivity().onBackPressed();

        } else if (ButtonClick == mediumBtn) {
            BrewBuilder.getInstance().grindSize(GrindSize.MEDIUM);

            getActivity().onBackPressed();

        } else if (ButtonClick == coarseBtn) {
            BrewBuilder.getInstance().grindSize(GrindSize.COARSE);

            getActivity().onBackPressed();

        }
    }
}