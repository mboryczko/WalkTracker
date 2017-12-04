package com.mjbor.ready.lastWalks.presenter;

import com.mjbor.ready.database.IRepository;
import com.mjbor.ready.lastWalks.view.ILastWalksView;
import com.mjbor.ready.lastWalks.view.WalkAdapter;
import com.mjbor.ready.model.Walk;

import java.util.List;

/**
 * Created by mjbor on 9/26/2017.
 */

public class LastWalkPresenter {

    private ILastWalksView view;
    private IRepository repository;
    private List<Walk> walks;

    private String distance;
    private String speed;

    public LastWalkPresenter(ILastWalksView view, IRepository repository, String distance, String speed){
        this.view = view;
        this.repository = repository;
        this.distance = distance;
        this.speed = speed;
    }


    public void createView(String distance, String speed){
        walks = repository.getAllWalks();
        WalkAdapter walkAdapter = new WalkAdapter(walks, distance, speed);
        view.setAdapter(walkAdapter);
    }

    public void createView(){
        walks = repository.getAllWalks();
        WalkAdapter walkAdapter = new WalkAdapter(walks, distance, speed);
        view.setAdapter(walkAdapter);
    }


}
