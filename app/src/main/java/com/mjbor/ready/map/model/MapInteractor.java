package com.mjbor.ready.map.model;

import com.mjbor.ready.map.presenter.MapPresenter;
import com.mjbor.ready.model.Place;
import com.mjbor.ready.rest.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mjbor on 9/25/2017.
 */

public class MapInteractor implements Callback<List<Place>> {

    private MapPresenter presenter;
    private MapWebService webService;
    private Call<List<Place>> call;

    public MapInteractor(MapPresenter presenter){
        this.presenter = presenter;
        webService = ApiClient.getClient().create(MapWebService.class);
    }


    public void getPlaces(Double lat, Double lon){
        call = webService.getPlaces(lat, lon);
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
        presenter.onPlacesFetchedSuccessfully(response.body());
    }

    @Override
    public void onFailure(Call<List<Place>>call, Throwable t) {
        presenter.onPlacesFetchedFailed();

    }





}
