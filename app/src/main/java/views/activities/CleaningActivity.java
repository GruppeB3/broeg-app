package views.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import dk.dtu.gruppeb3.broeg.app.R;

public class CleaningActivity extends BrewerActivity {

    private Button startBtn, cancelBtn;
    private ProgressBar progressBar;
    private int totalTime = 60; // Total time it takes to clean in seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentLayout(R.layout.activity_cleaning).hideMenu();

        startBtn = findViewById(R.id.cleaning_start_button);
        cancelBtn = findViewById(R.id.cleaning_cancel_button);
        progressBar = findViewById(R.id.progressBar2);

        startBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == startBtn) {
            startCleaning();
        } else if (v == cancelBtn) {
            finish();
        }
    }

    private void startCleaning() {
        if (brewer.getMacAddress().equals("01:23:45:67:89:AB")) {
            startCleaningWithMockBrewer();
        } else {
            startCleaningWithRealBrewer();
        }
    }

    private void startCleaningWithMockBrewer() {
        if (progressBar == null) {
            return;
        }

        progressBar.setProgress(0);

        final int totalTime = this.totalTime;

        final Handler mainThread = new Handler(Looper.getMainLooper());

        new Thread(new Runnable() {
            @Override
            public void run() {
                int progressStatus = 0;
                while (progressStatus < 100) {
                    progressStatus += 10;
                    final int graphProgress = progressStatus;

                    mainThread.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(graphProgress);
                        }
                    });

                    try {
                        Thread.sleep((totalTime / 10) * 1000);
                    } catch (Exception ignored) {}
                }
            }
        }).start();
    }

    private void startCleaningWithRealBrewer() {
        throw new  IllegalStateException("Not implemented!");
    }
}