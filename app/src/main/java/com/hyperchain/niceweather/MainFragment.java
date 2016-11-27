package com.hyperchain.niceweather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyperchain.niceweather.bean.WeatherJson;
import com.hyperchain.niceweather.retrofit.WeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Newcon on 2016/11/21.
 */
public class MainFragment extends Fragment {
    /*
        第一个cardview的信息
         */
    String weatherIcon;
    String temp;
    String tempMax;
    String tempMin;
    String pm25;
    String qlty;
    ImageView weatherIconImageView;
    TextView tempTextView, tempMaxTextView, tempMinTextView, pm25TextView;
    View view;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_layout, container, false);
        initFindViewById();
        initGetFromServer();
        FlashonUI();
        return view;
    }

    private void FlashonUI() {
    }

    private void initFindViewById() {
        weatherIconImageView = (ImageView) view.findViewById(R.id.weatherIcon);
        tempTextView = (TextView) view.findViewById(R.id.temp);
        tempMaxTextView = (TextView) view.findViewById(R.id.tempMax);
        tempMinTextView = (TextView) view.findViewById(R.id.tempMin);
        pm25TextView = (TextView) view.findViewById(R.id.pm25);
    }

    private void initGetFromServer() {
        Log.d("tag", "TempMaxTextView:" + tempMaxTextView);
        new Thread(){
            public void run(){
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://free-api.heweather.com/v5/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                WeatherService weatherService = retrofit.create(WeatherService.class);
                Call<WeatherJson> call = weatherService.getWeather("宁波", "532ba71fed214e29907ee788e8bf8812");
                call.enqueue(new Callback<WeatherJson>() {
                    public void onResponse(Call<WeatherJson> call, Response<WeatherJson> response) {
                        weatherIcon = response.body().getHeWeather5().get(0).getNow().getCond().getCode();
                        temp = response.body().getHeWeather5().get(0).getNow().getTmp();
                        tempMax = response.body().getHeWeather5().get(0).getDaily_forecast().get(0).getTmp().getMax();
                        tempMin = response.body().getHeWeather5().get(0).getDaily_forecast().get(0).getTmp().getMin();
                        pm25 = response.body().getHeWeather5().get(0).getAqi().getCity().getPm25();
                        qlty = response.body().getHeWeather5().get(0).getAqi().getCity().getQlty();
                        Log.d("tag", "weather:" + weatherIcon);
                        Log.d("tag", "temp:" + temp);
                        Log.d("tag", "tempMax:" + tempMax);
                        Log.d("tag", "tempMin:" + tempMin);
                        Log.d("tag", "pm25:" + pm25);
                        Log.d("tag", "qlty:" + qlty);
                        int imgId = 0x7f02005c;
                        imgId=imgId+Integer.parseInt(weatherIcon)-100;
                        weatherIconImageView.setBackgroundResource(imgId);
                        tempTextView.setText(temp+"℃");
                        tempMaxTextView.setText(tempMax+"℃");
                        tempMinTextView.setText(tempMin+"℃");
                        pm25TextView.setText("PM2.5:"+pm25+"μg/m³    空气质量："+qlty);
                    }

                    public void onFailure(Call<WeatherJson> call, Throwable t) {
                        System.out.println("retrofit失败");
                    }
                });

            }
        }.start();


    }

}

