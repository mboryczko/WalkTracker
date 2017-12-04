package com.mjbor.ready.walk.view;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mjbor.ready.R;
import com.mjbor.ready.database.DbInteractor;
import com.mjbor.ready.database.IRepository;
import com.mjbor.ready.main.MainActivity;
import com.mjbor.ready.sessions.ISharedPreferencesManager;
import com.mjbor.ready.sessions.SharedPreferencesManager;
import com.mjbor.ready.walk.dialog.PreferencesDialog;
import com.mjbor.ready.walk.presenter.WalkPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class WalkFragment extends Fragment
 implements IWalkView,
        MainActivity.distanceListened{

    @BindView(R.id.timeTV) TextView timeTV;
    @BindView(R.id.distanceTV) TextView distanceTV;
    @BindView(R.id.averageSpeedTV) TextView averageSpeedTV;

    @BindView(R.id.actionButton) Button actionButton;


    private WalkPresenter presenter;
    private int iterations = 0;
    private boolean running;


    public WalkFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_walk, container, false);
        ButterKnife.bind(this, v);


        return v;
    }

    @Override
    public void onResume(){
        super.onResume();
        IRepository dbInteractor = new DbInteractor(getContext());
        ISharedPreferencesManager spf = new SharedPreferencesManager(getContext());
        presenter = new WalkPresenter(this, dbInteractor, spf);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach (context);
        BroadcastReceiver lowBatteryReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                presenter.lowBattery();
            }
        };

        getContext().registerReceiver(lowBatteryReceiver, new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED));
    }

    public void preferencesChanged() {
        ISharedPreferencesManager spf = new SharedPreferencesManager(getContext());
        presenter.getCurrentPreferences(spf);
        presenter.clearTheView();
    }

    @Override
    public void onDistanceChanged(double distance) {
        if(presenter != null && running){
            presenter.onDistanceChanged(distance);
        }
    }

    public void onStartClicked(){
        presenter.startClicked();
    }

    public void onStopClicked(){
        presenter.stopClicked();
    }

    public void startTimer() {
        running = true;

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                iterations++;
                presenter.calculateTime(iterations);
                if(running){
                    handler.postDelayed(this, 100);
                }
            }
        });
    }

    public void stopTimer(){
        running = false;
        iterations = 0;

    }


    @Override
    public void setDistance(String text) {
        distanceTV.setText(text);
    }

    @Override
    public void setAverageSpeed(String text) {
        averageSpeedTV.setText(text);
    }

    @Override
    public void setTime(String text) {
        timeTV.setText(text);
    }

    @Override
    public void setActionButtonText(String text){
        actionButton.setText(text);
    }
}
