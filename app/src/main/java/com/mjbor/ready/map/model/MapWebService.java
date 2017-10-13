package com.mjbor.ready.map.model;

import com.mjbor.ready.model.Place;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mjbor on 9/25/2017.
 */

public interface MapWebService {


    @GET("geo")
    Call<List<Place>> getPlaces(@Query("lat") Double lat, @Query("lng") Double lon);

}
