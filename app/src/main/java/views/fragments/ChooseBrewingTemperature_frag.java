package views.fragments;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import dk.dtu.gruppeb3.broeg.app.R;
import io.sentry.Sentry;
import models.BrewBuilder;
import views.RepeatListener;
import views.activities.NewRecipeActivity;

/**
 * This fragment gives the user the opportunity to choose the amount of coffee they want to brew.
 */

public class ChooseBrewingTemperature_frag extends Fragment implements View.OnClickListener {

    Button plusBtn, minusBtn, saveBtn;
    private View root;
    double temperature;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState){
        this.root = i.inflate(R.layout.fragment_brewing_temperature, container, false);

        boolean EMULATOR = Build.PRODUCT.contains("sdk") || Build.MODEL.contains("Emulator");
        if (!EMULATOR) {
            Sentry.init("https://6c16f6a55ee34d9e8837467884c273b9@o506357.ingest.sentry.io/5595954");
        }

        temperature = BrewBuilder.getInstance().get().getBrewingTemperature();

        plusBtn = root.findViewById(R.id.ArrowUp_Ratio);
        minusBtn = root.findViewById(R.id.ArrowDown_Ratio);
        saveBtn = root.findViewById(R.id.Save_Ratio);

        plusBtn.setOnClickListener(this);
        minusBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);

        plusBtn.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temperature++;
                TextView tv = root.findViewById(R.id.totalTime);
                tv.setText(temperature + "(C)");
            }
        }));
        minusBtn.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                temperature--;
                TextView tv = root.findViewById(R.id.totalTime);
                tv.setText(temperature + "(C)");
            }
        }));


        updateText();

        return root;
    }

    @Override
    public void onClick(View ButtonClick) {



        if (ButtonClick == plusBtn) {
            temperature++;
            updateText();

        } else if (ButtonClick == minusBtn){
            temperature--;
            updateText();

        } else if (ButtonClick == saveBtn){
            BrewBuilder.getInstance().brewingTemperature(temperature);
            ((NewRecipeActivity)getActivity()).updateTextActivity();
            getActivity().onBackPressed();
        }

    }

    private void updateText() {
        TextView tv = root.findViewById(R.id.totalTime);
        tv.setText(temperature + "(C)");
    }
}