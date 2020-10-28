package views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import dk.dtu.gruppeb3.broeg.app.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button newBrewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newBrewButton = findViewById(R.id.button8);
        newBrewButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == newBrewButton) {

            Intent i = new Intent(this, NewRecipeActivity.class);
            startActivity(i);

        }

    }
}