package models;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dk.dtu.gruppeb3.broeg.app.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button knap1, knap2, knap3, knap4, knap5; //5 knapper på forsiden

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        knap1 = findViewById(R.id.NewBrew);

        knap1.setOnClickListener(this);
    }

    @Override
    public void onClick(View ClickButton) {

        if (ClickButton == knap1) {

            Intent i = new Intent(this, NewBrew.class);
            startActivity(i);

        }
    }
}