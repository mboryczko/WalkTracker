package com.mjbor.ready.walk.presenter;

import com.mjbor.ready.walk.view.IWalkView;

/**
 * Created by mjbor on 10/13/2017.
 */

public class WalkPresenter {

    private IWalkView view;
    private double distance;
    private int seconds;



    public WalkPresenter(IWalkView view){
        this.view = view;
    }

    public void onDistanceChanged(double distance){
        this.distance = distance;

        view.setDistance(formatDistance());
        view.setAverageSpeed(calculateAverageSpeed());
    }

    public String formatDistance(){
        return String.format("%1$,.2f ", distance) + "km";
    }

    public String calculateAverageSpeed(){
        if (seconds == 0) {
            return "0 km/h";
        }

        double hours = (double)seconds/3600;
        double averageSpeed = distance/hours;


        return String.format("%1$,.2f", averageSpeed) + "km/h";
    }

    //should be triggered every tenth of a second
    public void calculateTime(int iterations){
        int timeHours, timeMinutes, timeSeconds, timeHundrethSeconds;
        this.seconds = (int) (iterations / 10);

        timeHours = (int)(seconds/3600);
        timeMinutes = (int)(seconds/60);
        timeSeconds = seconds % 60;
        timeHundrethSeconds = iterations % 10;

        String timeToShow = formatNumber(timeHours) + ":" + formatNumber(timeMinutes) + ":"
                + formatNumber(timeSeconds) + ":" + timeHundrethSeconds;
        view.setTime(timeToShow);
    }

    public String formatNumber(int number){
        if(number < 10){
            return "0" + number;
        }

        return Integer.toString(number);
    }



}
