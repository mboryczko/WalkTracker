package com.mjbor.ready.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by mjbor on 10/15/2017.
 */

public class UnitConverter {

    public static double getDistance(double distance, String distanceUnit){
        if(distanceUnit.equals("km")){
            return distance;
        }

        return distance * 0.621371d;
    }

    public static double getSpeed(double speed, String speedUnit){
        if(speedUnit.equals("km/h")){
            return speed;
        }
        return speed * 0.621371d;
    }

    public static String getDateFormated(Date date){
        SimpleDateFormat ft = new SimpleDateFormat ("dd MMMM YYYY - HH:mm");

        return ft.format(date);
    }

}
