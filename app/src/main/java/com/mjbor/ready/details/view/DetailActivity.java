package com.mjbor.ready.details.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mjbor.ready.R;
import com.mjbor.ready.database.DbInteractor;
import com.mjbor.ready.database.IRepository;
import com.mjbor.ready.details.presenter.DetailPresenter;
import com.mjbor.ready.model.Place;
import com.mjbor.ready.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity
    implements IDetailView{

    @BindView(R.id.latitudeTV) TextView latitudeTV;
    @BindView(R.id.longitudeTV) TextView longitudeTV;
    @BindView(R.id.placeTV) TextView placeTV;
    @BindView(R.id.avatarIV) ImageView avatarIV;
    @BindView(R.id.progressBar) ProgressBar progressBar;


    private DetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        IRepository dbInteractor = new DbInteractor(this);
        presenter = new DetailPresenter(this, dbInteractor);
        getDataFromIntent();


    }

    public void getDataFromIntent(){
        Intent i = getIntent();
        String name = i.getStringExtra(Constants.NAME);
        String avatar = i.getStringExtra(Constants.AVATAR);
        int id = i.getIntExtra(Constants.ID, -1);
        double latitude = i.getDoubleExtra(Constants.LATITUDE, -1.0d);
        double longitude = i.getDoubleExtra(Constants.LONGITUDE, -1.0d);

        Place place = new Place(id, name, longitude, avatar, latitude);
        presenter.onDataFromIntent(place);
    }


    @Override
    public void setProgressBarVisible() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void setProgressBarInvisible() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setPlace(String text) {
        placeTV.setText(text);
    }

    @Override
    public void setLatitude(String text) {
        latitudeTV.setText(text);
    }

    @Override
    public void setLongitude(String text) {
        longitudeTV.setText(text);
    }


    @Override
    public void getImageAsync(String url) {

        Glide.with(this)
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        setProgressBarInvisible();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        presenter.onImageReady();
                        return false;
                    }
                })
                .into(avatarIV);

    }
}
