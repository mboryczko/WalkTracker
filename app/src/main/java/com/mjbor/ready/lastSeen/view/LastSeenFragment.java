package com.mjbor.ready.lastSeen.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mjbor.ready.R;
import com.mjbor.ready.database.DbInteractor;
import com.mjbor.ready.database.IRepository;
import com.mjbor.ready.lastSeen.presenter.LastSeenPresenter;
import com.mjbor.ready.service.OdometerService;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LastSeenFragment extends Fragment
        implements ILastSeenView {


    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    private LastSeenPresenter presenter;
    private RecyclerView.Adapter adapter;

    public LastSeenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    public void onRefresh(){
        if(presenter != null)
            presenter.createView();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_last_seen, container, false);
        ButterKnife.bind(getActivity());
        recyclerView = view.findViewById(R.id.recyclerView);


        IRepository dbInteractor = new DbInteractor(getContext());
        presenter = new LastSeenPresenter(this, dbInteractor);
        presenter.createView();

        return view;
    }



    @Override
    public void setAdapter(PlaceAdapter adapter) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
    }


}
