package com.mjbor.ready.walk.view;

/**
 * Created by mjbor on 10/13/2017.
 */

public interface IWalkView {

    public void setDistance(String text);
    public void setAverageSpeed(String text);
    public void setTime(String text);


    public void setActionButtonText(String text);

    public void startTimer();
    public void stopTimer();
}
