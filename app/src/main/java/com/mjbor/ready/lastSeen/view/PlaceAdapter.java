package com.mjbor.ready.lastSeen.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mjbor.ready.R;
import com.mjbor.ready.details.view.DetailActivity;
import com.mjbor.ready.model.Place;
import com.mjbor.ready.utils.Constants;

import java.util.List;

/**
 * Created by mjbor on 9/26/2017.
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    private List<Place> places;


    public PlaceAdapter(List<Place> places) {
        this.places = places;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.row, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Place place = places.get(position);
        holder.placeLastSeenTV.setText(place.getName());
        holder.coordinatesTV.setText(place.getLat() + " , " + place.getLng());

    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        private TextView placeLastSeenTV;
        private TextView coordinatesTV;
        private Context context;


        public ViewHolder(View itemView) {
            super(itemView);
            placeLastSeenTV = (TextView) itemView.findViewById(R.id.placeLastSeenTV);
            coordinatesTV = (TextView) itemView.findViewById(R.id.coordinatesTV);
            context = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            int position = getLayoutPosition();
            Place place = places.get(position);
            Intent i = new Intent(context, DetailActivity.class);
            i.putExtra(Constants.NAME, place.getName());
            i.putExtra(Constants.AVATAR, place.getAvatar());
            i.putExtra(Constants.LATITUDE, place.getLat());
            i.putExtra(Constants.LONGITUDE, place.getLng());
            i.putExtra(Constants.ID, place.getId());
            context.startActivity(i);

        }
    }

}
