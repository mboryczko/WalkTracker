package com.mjbor.ready.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.mjbor.ready.model.Place;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mjbor on 9/26/2017.
 */

public class DbInteractor implements IRepository {
    private SQLiteDatabase db;
    private Context context;


    public DbInteractor(Context context){
        this.context = context;
        SQLiteOpenHelper sqLiteOpenHelper = new AppDatabaseHelper(context);
        this.db = sqLiteOpenHelper.getWritableDatabase();
    }

    @Override
    public void savePlace(Place place) {
        List<Place> list = getAllPlaces();
        for(Place p : list){
            if(p.getId() == place.getId()){
                Log.i("db", "already in db");
                return;
            }

        }

        ContentValues values = new ContentValues();
        values.put(AppDatabaseHelper.ID, place.getId());
        values.put(AppDatabaseHelper.AVATAR, place.getAvatar());
        values.put(AppDatabaseHelper.LAT, place.getLat());
        values.put(AppDatabaseHelper.LNG, place.getLng());
        values.put(AppDatabaseHelper.NAME, place.getName());

        db.insert(AppDatabaseHelper.TABLE_NAME, null, values);


    }

    @Override
    public List<Place> getAllPlaces() {
        String sql = "select " + AppDatabaseHelper.ID + ", " +
                AppDatabaseHelper.AVATAR + ", " +
                AppDatabaseHelper.LAT + ", " +
                AppDatabaseHelper.LNG + ", " +
                AppDatabaseHelper.NAME +
                " from " + AppDatabaseHelper.TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        List<Place> places = new ArrayList<>();


        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String avatar = cursor.getString(1);
            double lat = cursor.getDouble(2);
            double lng = cursor.getDouble(3);
            String name = cursor.getString(4);

            places.add(new Place(id, name, lng, avatar, lat));
        }

        return places;
    }
}
