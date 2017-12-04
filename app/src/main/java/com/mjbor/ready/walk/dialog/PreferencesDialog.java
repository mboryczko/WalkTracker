package com.mjbor.ready.walk.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.mjbor.ready.R;
import com.mjbor.ready.sessions.ISharedPreferencesManager;
import com.mjbor.ready.sessions.SharedPreferencesManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mjbor on 10/14/2017.
 */

public class PreferencesDialog extends DialogFragment {

    PreferencesDialog.PreferencesListener listener;
    public interface PreferencesListener{
        public void onDialogPositiveCheck(DialogFragment dialog);
    }

    @BindView(R.id.distanceSwitch) Switch distanceSwitch;
    @BindView(R.id.speedSwitch) Switch speedSwitch;

    private String distance;
    private String speed;


    public void setUpSwitches(){
        if (distance.equals("km")) {
            distanceSwitch.setChecked(false);
        }

        else{
            distanceSwitch.setChecked(true);
        }

        if(speed.equals("km/h")){
            speedSwitch.setChecked(false);
        }

        else {
            speedSwitch.setChecked(true);
        }

        distanceSwitch.setText(distance);
        speedSwitch.setText(speed);
    }

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_preferences, null);
        ButterKnife.bind(this, view);

        final ISharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(getContext());
        distance = sharedPreferencesManager.getDistance();
        speed = sharedPreferencesManager.getSpeed();
        setUpSwitches();


        distanceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    distance = "mile";
                } else {
                    distance = "km";
                }
                distanceSwitch.setText(distance);
            }
        });

        speedSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    speed = "mile/h";

                } else {
                    speed = "km/h";
                }

                speedSwitch.setText(speed);
            }
        });


        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.save,  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        sharedPreferencesManager.savePreferences(distance, speed);
                        listener.onDialogPositiveCheck(PreferencesDialog.this);

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });


        return builder.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (PreferencesListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

}
