package views.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.util.Random;

import models.Brewer;

/**
 * All activities that relies on the selection of a brewer
 * should extend this activity. Upon creation of the activity during
 * runtime this class will take care of prompting the user for
 * a brewer and save that in the `<code>brewer</code>` attribute.
 * <br>
 * This attribute can be accessed from all classes that extend this one.
 */
public abstract class BrewerActivity extends BaseActivity {

    Brewer brewer;
    private Gson gson;
    private int SELECT_BREWER_REQUEST_CODE = (new Random()).nextInt(16);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson = new Gson();
        getBrewer();
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
                throw new IllegalStateException("A brewer was not properly selected. How on earth did you end up here!?");

            this.brewer = gson.fromJson(brewerJson, Brewer.class);
        }

        if (resultCode == RESULT_CANCELED) {
            finish();
        }
    }

}
