package views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.Random;

import dk.dtu.gruppeb3.broeg.app.R;
import models.Brew;
import models.Brewer;

public class BrewingActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String SELECTED_BREW_IDENTIFIER = "selectedBrew";

    private Gson gson = new Gson();

    private Button startBtn;
    private Button cancelBtn;
    private ProgressBar progressBar;

    private int SELECT_BREWER_REQUEST_CODE = (new Random()).nextInt(16);
    private Brewer brewer;
    private Brew brew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brewing);

        startBtn = findViewById(R.id.button2);
        cancelBtn = findViewById(R.id.button10);
        progressBar = findViewById(R.id.progressBar);

        startBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

        getBrew();

        if (this.brew == null)
            throw new IllegalStateException("A brew was not provided. Cannot proceed!");

        getBrewer();
    }

    private void startBrewing() {
        if (brewer.getMacAddress().equals("01:23:45:67:89:AB")) {
            startBrewingWithMockBrewer();
        } else {
            startBrewingWithRealBrewer();
        }
    }

    private void startBrewingWithMockBrewer() {
        progressBar.setProgress(0);

        final Handler mainThread = new Handler(Looper.getMainLooper());

        new Thread(new Runnable() {
            public void run() {
                int progressStatus = 0;
                while (progressStatus < 100) {
                    progressStatus += 10;
                    final int progress = progressStatus;

                    mainThread.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progress);
                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (Exception ignored) {}
                }
            }
        }).start();
    }

    private void startBrewingWithRealBrewer() {
        throw new IllegalStateException("Not implemented!");
    }

    private void getBrew() {
        String json = getIntent().getStringExtra(SELECTED_BREW_IDENTIFIER);
        this.brew = gson.fromJson(json, Brew.class);
    }

    private void getBrewer() {
        Intent i = new Intent(this, SelectBrewerActivity.class);
        startActivityForResult(i, SELECT_BREWER_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_BREWER_REQUEST_CODE && resultCode == RESULT_OK) {
            String brewerJson = data.getStringExtra(SelectBrewerActivity.SELECTED_BREWER_IDENTIFIER);

            if (brewerJson.equals(""))
                return;

            this.brewer = gson.fromJson(brewerJson, Brewer.class);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == startBtn) {
            startBrewing();
        } else if (v == cancelBtn) {
            finish();
        }
    }
}