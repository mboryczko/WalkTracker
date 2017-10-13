package com.mjbor.ready.model;

/**
 * Created by mjbor on 9/25/2017.
 */

public class Place
{
    private int id;
    private String name;
    private double lng;
    private String avatar;
    private double lat;

    public Place(){

    }

    public Place(int id, String name, double lng, String avatar, double lat) {
        this.id = id;
        this.name = name;
        this.lng = lng;
        this.avatar = avatar;
        this.lat = lat;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
