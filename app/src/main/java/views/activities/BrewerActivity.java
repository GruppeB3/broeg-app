package views.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.Random;

import models.Brewer;

public abstract class BrewerActivity extends AppCompatActivity {

    Brewer brewer;
    private Gson gson;
    private int SELECT_BREWER_REQUEST_CODE = (new Random()).nextInt(16);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson = new Gson();
        getBrewer();
    }

    void getBrewer() {
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

}
