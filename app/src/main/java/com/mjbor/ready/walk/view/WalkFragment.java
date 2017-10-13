package com.mjbor.ready.walk.view;


import android.os.Bundle;
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

    @BindView(R.id.time) TextView timeTV;
    @BindView(R.id.distance) TextView distanceTV;
    @BindView(R.id.averageSpeed) TextView averageSpeed;

    private WalkPresenter presenter;


    @Override
    public void onDistanceChanged(double distance) {
        //TODO

    }

    public WalkFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_walk, container, false);
        ButterKnife.bind(getActivity());

        presenter = new WalkPresenter(this);

        return v;
    }

}
