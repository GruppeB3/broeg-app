package views.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;

import dk.dtu.gruppeb3.broeg.app.R;
import io.sentry.Sentry;
import models.BrewBuilder;
import models.enums.GrindSize;
import views.activities.NewRecipeActivity;

/**
 * This fragment gives the user the opportunity to choose between 3 types af grind size.
 */

public class ChooseGrindSize_frag extends Fragment implements View.OnClickListener {

    Button fineBtn, mediumBtn, coarseBtn;


    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View root = i.inflate(R.layout.fragment_choose_grind_size, container, false);

        boolean EMULATOR = Build.PRODUCT.contains("sdk") || Build.MODEL.contains("Emulator");
        if (!EMULATOR) {
            Sentry.init("https://6c16f6a55ee34d9e8837467884c273b9@o506357.ingest.sentry.io/5595954");
        }

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
            ((NewRecipeActivity)getActivity()).updateTextActivity();
            getActivity().onBackPressed();

        } else if (ButtonClick == mediumBtn) {
            BrewBuilder.getInstance().grindSize(GrindSize.MEDIUM);
            ((NewRecipeActivity)getActivity()).updateTextActivity();
            getActivity().onBackPressed();

        } else if (ButtonClick == coarseBtn) {
            BrewBuilder.getInstance().grindSize(GrindSize.COARSE);
            ((NewRecipeActivity)getActivity()).updateTextActivity();
            getActivity().onBackPressed();

        }
    }
}