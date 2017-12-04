package com.mjbor.ready.lastWalks.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mjbor.ready.R;
import com.mjbor.ready.database.DbInteractor;
import com.mjbor.ready.database.IRepository;
import com.mjbor.ready.lastWalks.presenter.LastWalkPresenter;
import com.mjbor.ready.sessions.ISharedPreferencesManager;
import com.mjbor.ready.sessions.SharedPreferencesManager;
import com.mjbor.ready.walk.presenter.WalkPresenter;
import com.mjbor.ready.walk.view.IWalkView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LastWalksFragment extends Fragment
implements ILastWalksView{

    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    private LastWalkPresenter presenter;
    private RecyclerView.Adapter adapter;

    public LastWalksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_last_walks, container, false);
        ButterKnife.bind(getActivity());
        recyclerView = view.findViewById(R.id.recyclerView);


        IRepository dbInteractor = new DbInteractor(getContext());
        ISharedPreferencesManager spf = new SharedPreferencesManager(getContext());
        presenter = new LastWalkPresenter(this, dbInteractor, spf.getDistance(), spf.getSpeed());
        presenter.createView(spf.getDistance(), spf.getSpeed());

        return view;
    }

    public void preferencesChanged(){
        ISharedPreferencesManager spf = new SharedPreferencesManager(getContext());
        presenter.createView(spf.getDistance(), spf.getSpeed());
    }

    @Override
    public void setAdapter(WalkAdapter adapter) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), llm.getOrientation());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    public void onRefresh(){
        if(presenter != null)
            presenter.createView();
    }

}
