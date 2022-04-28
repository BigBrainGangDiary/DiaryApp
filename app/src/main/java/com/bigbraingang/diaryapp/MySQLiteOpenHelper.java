package com.bigbraingang.diaryapp;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    public MySQLiteOpenHelper(Context context) {
        super(context, "diary", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String newTable = "CREATE TABLE diary ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                    + "entry TEXT NOT NULL, "
                    + "rating TEXT NOT NULL, "
                    + "date TEXT NOT NULL, "
                    + "time TEXT NOT NULL)";
            db.execSQL(newTable);

            String profileTable = "CREATE TABLE profile ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                    + "name TEXT NOT NULL, "
                    + "contact TEXT NOT NULL, "
                    + "age TEXT NOT NULL)";
            db.execSQL(profileTable);

//            String insertProfile = "Insert"
        } catch (SQLException e) {
            Log.i("info", "DataManagerError - onCreate");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
