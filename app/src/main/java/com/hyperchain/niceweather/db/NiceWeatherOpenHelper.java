package com.hyperchain.niceweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Newcon on 2016/11/27.
 */
public class NiceWeatherOpenHelper extends SQLiteOpenHelper{
    /*
    Province表建表语句
     */
    public static final String CREATE_PROVINCE ="create " +
            "table Province(id integer primary key autoincrement, " +
            "province_name text, " +
            "province_code text) ";
    /*
    city表建表语句
     */
    public static final String CREATE_CITY = "create " +
            "table City(id integer primary key autoincrement, " +
            "city_name text, " +
            "city_code text, " +
            "province_id integer) ";
    /*
    County表建表语句
     */
    public static final String CREATE_COUNTY ="create " +
            "table County(id integer primary key autoincrement, " +
            "county_name text, " +
            "county_code text, " +
            "city_id integer) ";
    /*
    MultiCity表建表语句
     */
    public static final String CREATE_MULTICITY ="create " +
            "table MultiCity(id integer primary key autoincrement, " +
            "city text, " +
            "weather text, " +
            "temp text) ";

    public NiceWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTY);
        db.execSQL(CREATE_MULTICITY);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Province");
        db.execSQL("drop table if exists City");
        db.execSQL("drop table if exists County");
        db.execSQL("drop table if exists MultiCity");
        onCreate(db);

    }
}
