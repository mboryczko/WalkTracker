package com.mjbor.ready.map.view;


import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by mjbor on 9/25/2017.
 */

public interface IMapView {

    public void getLocationPermission();

    public void getDeviceLocation();

    public void updateLocationUI();
}
