package com.bigbraingang.diaryapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;


public class DataManager {
    private SQLiteDatabase db;

    public DataManager(Context context){
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(context);
        db = helper.getWritableDatabase();
    }

    public Cursor selectAll(){
        Cursor cursor = null;
        try {
            String query = "select * from diary";
            cursor = db.rawQuery(query, null);
        }
        catch (Exception e) {
            Log.i ("info", "In DataManager selectAll method");
            Log.i ("info", e.getMessage());
        }
        //Log.i ("info", "Loaded data " + cursor.getCount());
        return cursor;
    }

    public void insert(String entry, String rating, String date, String time){
        // DESCRIPTION: Insert new Entry into database.
        try{
            String query = "insert into diary" + "(entry, rating, date, time) values " +
                    "( '" + entry + "', '" + rating + "', '" + date + "', '" + time + "' )";
            db.execSQL(query);
        } catch (SQLException e){
            Log.i("info", "DataManagerError - Insert");
        }
    }



}


