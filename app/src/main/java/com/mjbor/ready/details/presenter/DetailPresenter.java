package com.mjbor.ready.details.presenter;

import com.mjbor.ready.database.IRepository;
import com.mjbor.ready.details.view.IDetailView;
import com.mjbor.ready.model.Place;

/**
 * Created by mjbor on 9/26/2017.
 */

public class DetailPresenter {

    private IDetailView view;
    private Place place;
    private IRepository repository;

    public DetailPresenter(IDetailView view, IRepository IRepository) {
        this.view = view;
        this.repository = IRepository;
    }

    public void onDataFromIntent(Place place){
        this.place = place;

        view.setLatitude(Double.toString(place.getLat()));
        view.setLongitude(Double.toString(place.getLng()));
        view.setPlace(place.getName());

        view.setProgressBarVisible();
        view.getImageAsync(place.getAvatar());

        repository.savePlace(place);
    }

    public void onImageReady(){
        view.setProgressBarInvisible();
    }




}
