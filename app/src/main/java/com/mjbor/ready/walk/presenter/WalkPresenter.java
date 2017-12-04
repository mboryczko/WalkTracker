package com.mjbor.ready.walk.presenter;

import com.mjbor.ready.database.IRepository;
import com.mjbor.ready.model.Walk;
import com.mjbor.ready.sessions.ISharedPreferencesManager;
import com.mjbor.ready.sessions.SharedPreferencesManager;
import com.mjbor.ready.utils.UnitConverter;
import com.mjbor.ready.walk.view.IWalkView;

import java.util.Date;

/**
 * Created by mjbor on 10/13/2017.
 */

public class WalkPresenter {

    private IWalkView view;
    private Walk walk;
    private IRepository repository;
    private boolean toUpdate;
    private String distance;
    private String speed;



    public WalkPresenter(IWalkView view, IRepository IRepository, ISharedPreferencesManager spf){
        this.view = view;
        this.repository = IRepository;
        this.walk = new Walk();

        getCurrentPreferences(spf);
        clearTheView();

    }

    public void getCurrentPreferences(ISharedPreferencesManager spf){
        distance = spf.getDistance();
        speed = spf.getSpeed();
    }

    public void clearTheView(){
        view.setDistance(formatDistance());
        view.setAverageSpeed(formatAverageSpeed(0d));
        view.setTime("00:00:00:00");
    }

    public void startClicked(){
        view.startTimer();
        view.setActionButtonText("STOP");
    }

    public void stopClicked(){

        view.stopTimer();
        view.setActionButtonText("START");
        walk.setDate(UnitConverter.getDateFormated(new Date()));
        if(toUpdate){
            repository.updateLastWalk(walk);
        }

        else {
            repository.saveWalk(walk);
        }

        toUpdate = false;

        walk.setDistance(0.0d);
        walk.setAverageSpeed(0.0d);
        walk.setSeconds(0);


        clearTheView();
    }

    public void lowBattery(){
        //save the current state
        //mark to flag to update later
        toUpdate = true;
        walk.setDate(UnitConverter.getDateFormated(new Date()));
        repository.saveWalk(walk);
    }

    public void onDistanceChanged(double distance){
        walk.setDistance(distance);
        walk.setAverageSpeed(calculateAverageSpeed());


        view.setDistance(formatDistance());
        view.setAverageSpeed(formatAverageSpeed(walk.getAverageSpeed()));
    }

    public String formatDistance(){
        double distanceCorrectUnit = UnitConverter.getDistance(walk.getDistance(), distance);

        return String.format("%1$,.2f ", distanceCorrectUnit) + distance;
    }

    public String formatAverageSpeed(double averageSpeed){
        double speedCorrectUnit = UnitConverter.getSpeed(walk.getAverageSpeed(), speed);

        return String.format("%1$,.2f", speedCorrectUnit) + speed;
    }

    public double calculateAverageSpeed(){
        if (walk.getSeconds() == 0) {
            return 0;
        }

        double hours = (double)walk.getSeconds()/3600;
        double averageSpeed = walk.getDistance()/hours;


        return averageSpeed;
    }

    //should be triggered every tenth of a second
    public void calculateTime(int iterations){
        int timeHours, timeMinutes, timeSeconds, timeHundrethSeconds;
        int seconds = walk.getSeconds();

        walk.setSeconds((int) (iterations / 10));

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
