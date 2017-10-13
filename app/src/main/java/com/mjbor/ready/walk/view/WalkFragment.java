package com.mjbor.ready.walk.view;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mjbor.ready.R;
import com.mjbor.ready.lastSeen.presenter.LastSeenPresenter;
import com.mjbor.ready.main.MainActivity;
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

    private WalkPresenter presenter;
    private int iterations = 0;


    public WalkFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_walk, container, false);
        ButterKnife.bind(this, v);

        return v;
    }


    @Override
    public void onDistanceChanged(double distance) {
        if(presenter != null){
            presenter.onDistanceChanged(distance);
        }
    }

    public void startTimer() {
        presenter = new WalkPresenter(this);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                iterations++;
                presenter.calculateTime(iterations);
                handler.postDelayed(this, 100);
            }
        });
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



}
