package views.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import dk.dtu.gruppeb3.broeg.app.R;
import models.BrewBuilder;
import views.RepeatListener;
import views.activities.NewRecipeActivity;

/**
 * This fragment gives the user the opportunity to choose the amount of coffee they want to brew.
 */

public class ChooseTotalBrewTime_frag extends Fragment implements View.OnClickListener {


    Button plusBtn, minusBtn, saveBtn;
    private View root;
    int totalBrewingTime;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState){
        this.root = i.inflate(R.layout.fragment_total_brewing_time, container, false);

        totalBrewingTime = BrewBuilder.getInstance().get().getTotalBrewTime();

        plusBtn = root.findViewById(R.id.ArrowUp_Ratio);
        minusBtn = root.findViewById(R.id.ArrowDown_Ratio);
        saveBtn = root.findViewById(R.id.Save_Ratio);

        plusBtn.setOnClickListener(this);
        minusBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);

        plusBtn.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalBrewingTime++;
                TextView tv = root.findViewById(R.id.totalTime);
                tv.setText(totalBrewingTime + "(min/s)");
            }
        }));
        minusBtn.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                totalBrewingTime--;
                TextView tv = root.findViewById(R.id.totalTime);
                tv.setText(totalBrewingTime + "(min/s)");
            }
        }));

        updateText();

        return root;


    }

    @Override
    public void onClick(View ButtonClick) {

        if (ButtonClick == plusBtn) {
            totalBrewingTime++;
            updateText();

        } else if (ButtonClick == minusBtn){
            totalBrewingTime--;
            updateText();

        } else if (ButtonClick == saveBtn){
            BrewBuilder.getInstance().totalBrewTime(totalBrewingTime);
            ((NewRecipeActivity)getActivity()).updateTextActivity();
            getActivity().onBackPressed();
        }

    }

    private void updateText() {
        TextView tv = root.findViewById(R.id.totalTime);
        tv.setText(totalBrewingTime + "(min/s)");
    }
}