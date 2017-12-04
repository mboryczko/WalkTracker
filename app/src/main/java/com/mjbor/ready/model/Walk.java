package com.mjbor.ready.model;

/**
 * Created by mjbor on 10/13/2017.
 */

public class Walk {

    public Walk(){

    }

    public Walk(double distance, double averageSpeed, int seconds, String date){
        this.distance = distance;
        this.averageSpeed = averageSpeed;
        this.seconds = seconds;
        this.date = date;
    }

    private double distance;
    private int seconds;
    private double averageSpeed;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }
}
