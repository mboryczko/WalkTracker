package com.mjbor.ready.details.view;

import android.graphics.Bitmap;

/**
 * Created by mjbor on 9/26/2017.
 */

public interface IDetailView {

    public void setPlace(String text);
    public void setLatitude(String text);
    public void setLongitude(String text);

    public void setProgressBarVisible();
    public void setProgressBarInvisible();

    public void getImageAsync(String url);
}
