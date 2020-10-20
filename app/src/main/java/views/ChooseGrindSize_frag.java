package views;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import dk.dtu.gruppeb3.broeg.app.R;

/**
 * This fragment gives the user the opportunity to choose between 3 types af grind size.
 * @Author Gustav Kirkholt
 */

public class ChooseGrindSize_frag extends Fragment implements View.OnClickListener {

    Button knap1, knap2, knap3, knap4;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.activity_choose_grind_size, container, false);

        knap1 = rod.findViewById(R.id.Fine);
        knap2 = rod.findViewById(R.id.Medium);
        knap3 = rod.findViewById(R.id.Coarse);
        knap4 = rod.findViewById(R.id.Gem2);

        knap1.setOnClickListener(this);
        knap2.setOnClickListener(this);
        knap3.setOnClickListener(this);
        knap4.setOnClickListener(this);

        return rod;
    }

    @Override
    public void onClick(View ButtonClick) {

        if (ButtonClick == knap1) {

        } else if (ButtonClick == knap2) {

        } else if (ButtonClick == knap3) {

        } else if (ButtonClick == knap4) {

        }
    }
}