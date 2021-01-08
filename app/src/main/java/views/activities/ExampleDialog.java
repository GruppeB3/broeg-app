package views.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.gson.Gson;

import dk.dtu.gruppeb3.broeg.app.R;

public class ExampleDialog extends AppCompatDialogFragment {

    private TextView brewName;
    private TextView

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.brew_dialog, null);

        builder.setView(view)
                .setTitle(brew.get)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Bryg", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String json = (new Gson()).toJson(this.brews.get(position));
                        Intent i = new Intent(this, BrewingActivity.class);
                        i.putExtra(BrewingActivity.SELECTED_BREW_IDENTIFIER, json);
                        startActivity(i);
                        finish();
                    }
                });
        return builder.create();
    }
}
