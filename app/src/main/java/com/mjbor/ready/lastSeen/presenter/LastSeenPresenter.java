package com.mjbor.ready.lastSeen.presenter;

import com.mjbor.ready.database.IRepository;
import com.mjbor.ready.lastSeen.view.ILastSeenView;
import com.mjbor.ready.lastSeen.view.PlaceAdapter;
import com.mjbor.ready.model.Place;

import java.util.List;

/**
 * Created by mjbor on 9/26/2017.
 */

public class LastSeenPresenter {

    private ILastSeenView view;
    private IRepository repository;
    private List<Place> places;


    public LastSeenPresenter(ILastSeenView view, IRepository repository){
        this.view = view;
        this.repository = repository;
    }


    public void createView(){
        places = repository.getAllPlaces();
        PlaceAdapter placeAdapter = new PlaceAdapter(places);
        view.setAdapter(placeAdapter);
    }








}
