package com.mjbor.ready.map.presenter;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.mjbor.ready.map.model.MapInteractor;
import com.mjbor.ready.map.view.IMapView;
import com.mjbor.ready.model.Place;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mjbor on 9/25/2017.
 */

public class MapPresenter {

    private IMapView view;
    private MapInteractor interactor;

    private List<Place> places;
    private List<Marker> listMarker;


    public MapPresenter(IMapView view){
        this.view = view;
        this.interactor = new MapInteractor(this);
    }


    public void getPlaces(double lat, double lon){
        interactor.getPlaces(lat, lon);
    }

    public void onMapReady(){
        view.getLocationPermission();
        view.updateLocationUI();
        view.getDeviceLocation();

    }

    public void permissionGranted(){
        view.updateLocationUI();
        view.getDeviceLocation();
    }

    public void onMarkerClicked(Marker marker){
        int i = listMarker.indexOf(marker);
        Place place = places.get(i);

        view.startDetailActivity(place);

    }

    public void onSuccessfullyReadLocation(Location location){
        //getPlaces(location.getLatitude(), location.getLongitude());
    }




    //------------------------------------

    public void onPlacesFetchedSuccessfully(List<Place> list){
        this.places = list;

        List<LatLng> listLatLng = new ArrayList<>();

        for(Place place : list){
            LatLng latLng = new LatLng(place.getLat(), place.getLng());
            listLatLng.add(latLng);
        }

        listMarker = view.addMarkers(listLatLng);
        view.moveCamera(listMarker);

    }

    public void onPlacesFetchedFailed(){
        view.showToast("Failed to load data from server");
    }





}

