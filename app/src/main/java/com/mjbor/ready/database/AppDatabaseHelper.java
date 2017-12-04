package com.mjbor.ready.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by mjbor on 9/26/2017.
 */

public class AppDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "lastWalksDB";
    private static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "WALKS";
    public static final String ID = "_id";
    public static final String DISTANCE = "distance";
    public static final String SECONDS = "seconds";
    public static final String AVERAGE_SPEED = "average_speed";
    public static final String WALK_DATE = "walk_date";

    public AppDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        updateMyDatabese(db, 0, DB_VERSION);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        updateMyDatabese(db, oldVersion, newVersion);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        updateMyDatabese(db, oldVersion, newVersion);
    }
    private void updateMyDatabese(SQLiteDatabase db, int oldVersion, int newVersion)  {
        if(oldVersion < 1){

            String walkTableCreation =
                    "CREATE TABLE " + TABLE_NAME + "( " +
                            ID + " INTEGER PRIMARY KEY, " +
                            DISTANCE + " REAL, " +
                            SECONDS + " INTEGER, " +
                            AVERAGE_SPEED + " REAL, " +
                            WALK_DATE + " TEXT" +
                            ");";

            try{
                db.execSQL(walkTableCreation);

            }
            catch(SQLiteException e){
                Log.e("AppDatabaseHelper", "error creating db");
                e.printStackTrace();
            }


        }

    }
}
