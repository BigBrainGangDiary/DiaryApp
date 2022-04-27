package com.bigbraingang.diaryapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
            String query = "select * from diary order by date";
            cursor = db.rawQuery(query, null);
        }
        catch (Exception e) {
            Log.i ("info", "In DataManager selectAll method");
            Log.i ("info", e.getMessage());
        }

        //Log.i ("info", "Loaded data " + cursor.getCount());
        return cursor;
    }

    public void insert(String entry, String rating, Date date){
        try{
            String query = "insert into diary" + "(entry, rating, date) values " +
                    "( '" + entry + "', '" + rating + "', '" + date + "' )";
        } catch (SQLException e){
            Log.i("info", "DataManagerError - Insert");
        }
        Log.i("info", "Added new entry into diary");

    }
}

class MySQLiteOpenHelper extends SQLiteOpenHelper{
    public MySQLiteOpenHelper(Context context){
        super(context, "diary", null, 1);
    }
    public void onCreate (SQLiteDatabase db) {

        try {
            String newTable = "create table contact  ("
                    + "_id integer primary key autoincrement not null, "
                    + "entry text not null, "
                    + "rating text, "
                    + "date date, "
                    + "street text)";

            db.execSQL(newTable);
        }
        catch (SQLException e) {
            Log.i ("info", "DataManagerError - onCreate");
        }
    }


    public void onUpgrade (SQLiteDatabase db, int oldVErsion, int newVersion) {

    }
}


