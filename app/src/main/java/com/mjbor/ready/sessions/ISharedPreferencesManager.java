package com.mjbor.ready.sessions;

import java.util.HashMap;

/**
 * Created by mjbor on 9/1/2017.
 */

public interface ISharedPreferencesManager {
    public void savePreferences(String distance, String speed);
    public String getDistance();
    public String getSpeed();

}
