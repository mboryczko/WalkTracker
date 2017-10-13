package com.mjbor.ready.map.view;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.mjbor.ready.model.Place;

import java.util.List;

/**
 * Created by mjbor on 9/25/2017.
 */

public interface IMapView {

    public void showToast(String text);
    public List<Marker> addMarkers(List<LatLng> list);
    public void getLocationPermission();
    public void getDeviceLocation();
    public void updateLocationUI();
    public void moveCamera(List<Marker> markers);
    public void startDetailActivity(Place place);
}
