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

    private static final String DB_NAME = "lastSeenPlacesDB";
    private static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "PLACE";
    public static final String ID = "_id";
    public static final String AVATAR = "avatar";
    public static final String LNG = "lng";
    public static final String LAT = "lat";
    public static final String NAME = "name";

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

            String placeTableCreation =
                    "CREATE TABLE " + TABLE_NAME + "( " +
                            ID + " INTEGER PRIMARY KEY," +
                            AVATAR + " TEXT," +
                            LNG + " REAL," +
                            LAT + " REAL," +
                            NAME + " TEXT" +
                            ");";

            try{
                db.execSQL(placeTableCreation);

            }
            catch(SQLiteException e){
                Log.e("AppDatabaseHelper", "error creating db");
                e.printStackTrace();
            }


        }

    }
}
