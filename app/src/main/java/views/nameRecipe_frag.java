package views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dk.dtu.gruppeb3.broeg.app.R;

public class nameRecipe_frag extends Fragment {

    private View rod;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        this.rod = i.inflate(R.layout.activity_choose_amount_of_coffee, container, false);

        return rod;
    }
}