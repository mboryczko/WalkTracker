package com.mjbor.ready.map.presenter;

import android.location.Location;
import com.mjbor.ready.map.view.IMapView;

/**
 * Created by mjbor on 9/25/2017.
 */

public class MapPresenter {

    private IMapView view;


    public MapPresenter(IMapView view){
        this.view = view;
    }


    public void onMapReady(){
        //view.showProgressDialog();
        view.getLocationPermission();
        view.updateLocationUI();
        view.getDeviceLocation();

    }

    public void permissionGranted(){
        //view.showProgressDialog();
        view.updateLocationUI();
        view.getDeviceLocation();
    }

    public void onSuccessfullyReadLocation(Location location){

    }


}

