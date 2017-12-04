package com.mjbor.ready.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.mjbor.ready.model.Walk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mjbor on 9/26/2017.
 */

public class DbInteractor implements IRepository {
    private SQLiteDatabase db;
    private Context context;
    private long lastIdInserted;


    public DbInteractor(Context context){
        this.context = context;
        SQLiteOpenHelper sqLiteOpenHelper = new AppDatabaseHelper(context);
        this.db = sqLiteOpenHelper.getWritableDatabase();

    }

    @Override
    public void saveWalk(Walk walk) {
        ContentValues values = getContentValuesForWalk(walk);
        this.lastIdInserted = db.insert(AppDatabaseHelper.TABLE_NAME, null, values);
    }


    @Override
    public void updateLastWalk(Walk walk){
        ContentValues values = getContentValuesForWalk(walk);
        db.update(AppDatabaseHelper.TABLE_NAME, values, "_id="+lastIdInserted, null);
    }


    public ContentValues getContentValuesForWalk(Walk walk){
        ContentValues values = new ContentValues();
        values.put(AppDatabaseHelper.WALK_DATE, walk.getDate());
        values.put(AppDatabaseHelper.AVERAGE_SPEED, walk.getAverageSpeed());
        values.put(AppDatabaseHelper.DISTANCE, walk.getDistance());
        values.put(AppDatabaseHelper.SECONDS, walk.getSeconds());

        return values;
    }

    @Override
    public List<Walk> getAllWalks() {
        String sql = "select " +
                AppDatabaseHelper.AVERAGE_SPEED + ", " +
                AppDatabaseHelper.DISTANCE + ", " +
                AppDatabaseHelper.WALK_DATE + ", " +
                AppDatabaseHelper.SECONDS +

                " from " + AppDatabaseHelper.TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        List<Walk> walks = new ArrayList<>();


        while(cursor.moveToNext()){
            double averageSpeed = cursor.getDouble(0);
            double distance = cursor.getDouble(1);
            String date  = cursor.getString(2);
            int seconds = cursor.getInt(3);

            walks.add(new Walk(distance, averageSpeed, seconds, date));
        }

        return walks;
    }
}
