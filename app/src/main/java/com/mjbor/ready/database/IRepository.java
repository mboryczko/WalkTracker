package com.mjbor.ready.database;

import com.mjbor.ready.model.Place;

import java.util.List;

/**
 * Created by mjbor on 9/26/2017.
 */

public interface IRepository {

    public void savePlace(Place place);
    public List<Place> getAllPlaces();
}
