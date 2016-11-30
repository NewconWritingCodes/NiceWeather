package com.hyperchain.niceweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hyperchain.niceweather.model.City;
import com.hyperchain.niceweather.model.County;
import com.hyperchain.niceweather.model.MultiCity;
import com.hyperchain.niceweather.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Newcon on 2016/11/27.
 */
public class NiceWeatherDB {
    /*
    数据库名
     */
    public static final String DB_NAME = "nice_weather";
    /*
    数据库版本
     */
    public static final int VERSION = 3;
    private static NiceWeatherDB niceWeatherDB;
    private SQLiteDatabase db;

    /*
    将构造方法私有化
     */
    private NiceWeatherDB(Context context) {
        NiceWeatherOpenHelper dbHelper = new NiceWeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /*
    获取CoolWeatherDB实例
     */
    public synchronized static NiceWeatherDB getInstance(Context context) {
        if (niceWeatherDB == null) {
            niceWeatherDB = new NiceWeatherDB(context);
        }
        return niceWeatherDB;
    }

    /*
    将Province 实例存储到数据库
     */
    public void saveProvince(Province province) {
        if (province != null) {
            ContentValues values = new ContentValues();
            values.put("province_name", province.getProvinceName());
            values.put("province_code", province.getProvinceCode());
            db.insert("Province", null, values);
        }
    }

    public List<Province> loadProvince() {
        List<Province> list = new ArrayList<Province>();
        try {
            Cursor cursor = db.query("Province", null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    Province province = new Province();
                    province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                    province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                    list.add(province);
                } while (cursor.moveToNext());

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public void saveCity(City city) {
        if (city != null) {
            ContentValues values = new ContentValues();
            values.put("city_name", city.getCityName());
            values.put("city_code", city.getCityCode());
            values.put("province_id",city.getProvinceId());
            db.insert("City", null, values);
        }
    }

    public List<City> loadCities(int provinceId) {
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query("City", null, "province_id = ?",
                new String[]{String.valueOf(provinceId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor
                        .getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor
                        .getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);
            } while (cursor.moveToNext());
        }
        return list;
    }

    /**
     * 将County实例存储到数据库。
     */
    public void saveCounty(County county) {
        if (county != null) {
            ContentValues values = new ContentValues();
            values.put("county_name", county.getCountyName());
            values.put("county_code", county.getCountyCode());
            values.put("city_id", county.getCityId());
            db.insert("County", null, values);
        }
    }

    /**
     * 从数据库读取某城市下所有的县信息。
     */
    public List<County> loadCounties(int cityId) {
        List<County> list = new ArrayList<County>();
        Cursor cursor = db.query("County", null, "city_id = ?",
                new String[]{String.valueOf(cityId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor
                        .getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor
                        .getColumnIndex("county_code")));
                county.setCityId(cityId);
                list.add(county);
            } while (cursor.moveToNext());
        }
        return list;
    }
    /*
    将多城市界面的城市数据存入
     */
    public void saveMultiCity(MultiCity multiCity){
        ContentValues contentValues = new ContentValues();
        contentValues.put("city",multiCity.getCity());
        contentValues.put("weather",multiCity.getWeather());
        contentValues.put("temp",multiCity.getTemp());
        db.insert("MultiCity",null,contentValues);
    }

    /**
     * 从数据库读取多城市数据
     */
    public List<MultiCity> loadMultiCity() {
        List<MultiCity> list = new ArrayList<MultiCity>();
        Cursor cursor = db.query("MultiCity", null,null,
                null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                MultiCity multiCity = new MultiCity();
                multiCity.setCity(cursor.getString(cursor.getColumnIndex("city")));
                multiCity.setTemp(cursor.getString(cursor.getColumnIndex("temp")));
                multiCity.setWeather(cursor.getString(cursor.getColumnIndex("weather")));
                list.add(multiCity);
            } while (cursor.moveToNext());
        }
        return list;
    }
}
