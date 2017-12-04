package com.mjbor.ready.lastWalks.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mjbor.ready.R;
import com.mjbor.ready.model.Walk;
import com.mjbor.ready.utils.UnitConverter;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by mjbor on 10/13/2017.
 */

public class WalkAdapter extends RecyclerView.Adapter<WalkAdapter.ViewHolder> {

    private List<Walk> walks;
    private String distance;
    private String speed;


    public WalkAdapter(List<Walk> walks,String distance, String speed){
        this.walks = walks;
        this.distance = distance;
        this.speed = speed;
    }

    @Override
    public WalkAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.row, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WalkAdapter.ViewHolder holder, int position) {
        Walk walk = walks.get(position);


        holder.dateLastWalk.setText(walk.getDate());
        holder.distanceLastWalk.setText(formatDistance(walk.getDistance()));
        holder.averageSpeedWalkTV.setText(formatAverageSpeed(walk.getAverageSpeed()));
        holder.timeLastWalkTV.setText(calculateTime(walk.getSeconds()));
    }

    public String formatDistance(double d){
        double distanceCorrectUnit = UnitConverter.getDistance(d, distance);

        return String.format("%1$,.2f ", distanceCorrectUnit) + distance;
    }

    public String formatAverageSpeed(double as){
        double speedCorrectUnit = UnitConverter.getSpeed(as, speed);

        return String.format("%1$,.2f", speedCorrectUnit) + speed;
    }

    public String calculateTime(int seconds){
        int timeHours, timeMinutes, timeSeconds, timeHundrethSeconds;

        timeHours = (int)(seconds/3600);
        timeMinutes = (int)(seconds/60);
        timeSeconds = seconds % 60;

        String timeToShow = formatNumber(timeHours) + ":" + formatNumber(timeMinutes) + ":"
                + formatNumber(timeSeconds);

        return timeToShow;
    }

    public String formatNumber(int number){
        if(number < 10){
            return "0" + number;
        }

        return Integer.toString(number);
    }

    @Override
    public int getItemCount() {
        return walks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView dateLastWalk;
        private TextView distanceLastWalk;
        private TextView timeLastWalkTV;
        private TextView averageSpeedWalkTV;

        private Context context;


        public ViewHolder(View itemView) {
            super(itemView);
            dateLastWalk = (TextView) itemView.findViewById(R.id.dateLastWalkTV);
            distanceLastWalk = (TextView) itemView.findViewById(R.id.distanceLastWalkTV);
            timeLastWalkTV = (TextView) itemView.findViewById(R.id.timeLastWalkTV);
            averageSpeedWalkTV = (TextView) itemView.findViewById(R.id.averageSpeedLastWalkTV);

            context = itemView.getContext();
        }
    }

}
