package com.hyperchain.niceweather.retrofit;

import com.hyperchain.niceweather.bean.WeatherJson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Newcon on 2016/11/26.
 */
/*
interface GitHubService {
  @GET("/repos/{owner}/{repo}/contributors")
  List<Contributor> repoContributors(
      @Path("owner") String owner,
      @Path("repo") String repo);
}
 */

public interface WeatherService {
    @GET("weather")
    Call<WeatherJson> getWeather(@Query("city") String city, @Query("key")String key);
}
