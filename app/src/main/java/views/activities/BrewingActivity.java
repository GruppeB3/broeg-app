package views.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import dk.dtu.gruppeb3.broeg.app.R;

public class BrewingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brewing);
    }

    public static class CleaningActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_cleaning);
        }
    }
}