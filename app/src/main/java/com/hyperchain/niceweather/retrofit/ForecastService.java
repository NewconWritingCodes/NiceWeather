package com.hyperchain.niceweather.retrofit;

import com.hyperchain.niceweather.bean.ForecastJson;
import com.hyperchain.niceweather.bean.WeatherJson;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Newcon on 2016/11/26.
 */

public interface ForecastService {
    @GET("forecast")
    Call<ForecastJson> getWeather(@Query("city") String city, @Query("key") String key);
}
