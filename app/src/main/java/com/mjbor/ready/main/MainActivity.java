package com.mjbor.ready.main;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.location.Location;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.mjbor.ready.R;
import com.mjbor.ready.lastWalks.view.LastWalksFragment;
import com.mjbor.ready.map.view.MyMapFragment;
import com.mjbor.ready.service.OdometerService;
import com.mjbor.ready.sessions.ISharedPreferencesManager;
import com.mjbor.ready.sessions.SharedPreferencesManager;
import com.mjbor.ready.walk.dialog.PreferencesDialog;
import com.mjbor.ready.walk.view.WalkFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener,
        ViewPager.OnPageChangeListener,
        PreferencesDialog.PreferencesListener{

    @BindView(R.id.bottom_navigation) BottomNavigationView bottomNavigationView;
    @BindView(R.id.viewpager) ViewPager viewPager;

    private ViewPagerAdapter adapter;
    private MenuItem prevMenuItem;

    private MyMapFragment myMapFragment;
    private LastWalksFragment lastWalkFragment;
    private WalkFragment walkFragment;

    private PreferencesDialog dialog;
    private OdometerService odometer;
    private boolean bound = false;
    private boolean clicked = false;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder binder) {
            OdometerService.OdometerBinder odometerBinder = (OdometerService.OdometerBinder) binder;
            odometer = odometerBinder.getOdometer();
            bound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            bound = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, OdometerService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (bound) {
            unbindService(connection);
            bound = false;
        }
    }


    MainActivity.distanceListened listener;
    public interface distanceListened{
        public void onDistanceChanged(double distance);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        //----------------------------------
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        setupViewPager(viewPager);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);


    }


    public void settingsClicked(View v){
        dialog = new PreferencesDialog();
        dialog.show(getSupportFragmentManager(), "PreferencesDialog");
    }

    @Override
    public void onDialogPositiveCheck(DialogFragment dialog) {
        walkFragment.preferencesChanged();
        lastWalkFragment.preferencesChanged();
    }


    private void watchMileage() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                double distance = 0.0;
                if (odometer != null) {
                    distance = odometer.getDistance();
                }

                listener.onDistanceChanged(distance);
                handler.postDelayed(this, 1000);
            }
        });
    }


    public void actionButtonClicked(View v){

        if(!clicked){
            clicked = true;
            odometer.setDistanceInMeters(0d);
            watchMileage();
            walkFragment.onStartClicked();
        }

        else{
            clicked = false;
            odometer.setDistanceInMeters(0d);
            walkFragment.onStopClicked();
        }
    }








    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        myMapFragment = new MyMapFragment();
        lastWalkFragment = new LastWalksFragment();
        walkFragment = new WalkFragment();
        listener = (distanceListened) walkFragment;

        adapter.addFragment(walkFragment);
        adapter.addFragment(myMapFragment);
        adapter.addFragment(lastWalkFragment);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(2);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_walk:
                viewPager.setCurrentItem(0);
                item.setChecked(true);
                break;

            case R.id.action_map:
                viewPager.setCurrentItem(1);
                item.setChecked(true);
                break;

            case R.id.action_last_seen:
                viewPager.setCurrentItem(2);
                item.setChecked(true);
                break;
        }
        return false;
    }


    @Override
    public void onPageSelected(int position) {
        if (prevMenuItem != null) {
            prevMenuItem.setChecked(false);
        } else {
            bottomNavigationView.getMenu().getItem(0).setChecked(false);
        }

        bottomNavigationView.getMenu().getItem(position).setChecked(true);
        prevMenuItem = bottomNavigationView.getMenu().getItem(position);
        if(adapter.getItem(position) instanceof LastWalksFragment){
            lastWalkFragment.onRefresh();
        }


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }

    }
}
