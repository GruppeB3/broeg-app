package views.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import dk.dtu.gruppeb3.broeg.app.R;
import views.fragments.Menu_frag;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    Button menuBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        menuBtn = findViewById(R.id.menu_button);

        if (menuBtn != null) {
            menuBtn.setOnClickListener(this);
        }
    }

    protected BaseActivity hideMenu() {
        if (menuBtn != null) {
            menuBtn.setVisibility(View.INVISIBLE);
        }
        return this;
    }

    protected BaseActivity addContentLayout(int layout) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, null);
        FrameLayout fl = findViewById(R.id.main_content);
        fl.addView(view);
        return this;
    }

    @Override
    public void onClick(View v) {
        if (v == menuBtn) {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.menu_content, new Menu_frag())
                    .addToBackStack(null)
                    .commit();
        }
    }
}
