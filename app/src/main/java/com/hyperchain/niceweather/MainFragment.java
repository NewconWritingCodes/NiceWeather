package com.hyperchain.niceweather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.hyperchain.niceweather.bean.ForecastJson;
import com.hyperchain.niceweather.bean.WeatherJson;
import com.hyperchain.niceweather.retrofit.ForecastService;
import com.hyperchain.niceweather.retrofit.WeatherService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    String weatherIcon="100";
    String temp="0";
    String tempMax="0";
    String tempMin="0";
    String pm25="0";
    String qlty="0";
    ImageView weatherIconImageView;
    TextView tempTextView, tempMaxTextView, tempMinTextView, pm25TextView;
    View view;

    String countyName = null;
    Toolbar toolbar;
    Map<Integer, Integer> myMap = new HashMap<Integer, Integer>();

    //forecast 内存储的信息
    List<Map<String, Object>> dataList;
    SimpleAdapter simpleAdapter;
    String forecastWeatherIcon;
    String forecastTempMax;
    String forecastTempMin;
    String forecastWeather;
    String forecastWindDir;
    String forecastWindSpeed;
    String forecastPop;
    ListView listView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_layout, container, false);
        initFindViewById();
//        initGetFromServer();
        return view;
    }


    private void initFindViewById() {
        weatherIconImageView = (ImageView) view.findViewById(R.id.weatherIcon);
        tempTextView = (TextView) view.findViewById(R.id.temp);
        tempMaxTextView = (TextView) view.findViewById(R.id.tempMax);
        tempMinTextView = (TextView) view.findViewById(R.id.tempMin);
        pm25TextView = (TextView) view.findViewById(R.id.pm25);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        Log.d("tag", "toolBar:" + toolbar);
        //forecast中的listView;
        listView = (ListView) view.findViewById(R.id.listView);
        Log.d("tag","listView"+listView.toString());
        //将icon的Name与代号对应起来
        myMap.put(100, R.drawable.icon100);
        myMap.put(101, R.drawable.icon101);
        myMap.put(102, R.drawable.icon102);
        myMap.put(103, R.drawable.icon103);
        myMap.put(104, R.drawable.icon104);
        myMap.put(200, R.drawable.icon200);
        myMap.put(201, R.drawable.icon201);
        myMap.put(202, R.drawable.icon202);
        myMap.put(203, R.drawable.icon203);
        myMap.put(204, R.drawable.icon204);
        myMap.put(205, R.drawable.icon205);
        myMap.put(206, R.drawable.icon206);
        myMap.put(207, R.drawable.icon207);
        myMap.put(208, R.drawable.icon208);
        myMap.put(209, R.drawable.icon209);
        myMap.put(210, R.drawable.icon210);
        myMap.put(211, R.drawable.icon211);
        myMap.put(212, R.drawable.icon212);
        myMap.put(213, R.drawable.icon213);
        myMap.put(300, R.drawable.icon300);
        myMap.put(301, R.drawable.icon301);
        myMap.put(302, R.drawable.icon302);
        myMap.put(303, R.drawable.icon303);
        myMap.put(304, R.drawable.icon304);
        myMap.put(305, R.drawable.icon305);
        myMap.put(306, R.drawable.icon306);
        myMap.put(307, R.drawable.icon307);
        myMap.put(308, R.drawable.icon308);
        myMap.put(309, R.drawable.icon309);
        myMap.put(310, R.drawable.icon310);
        myMap.put(311, R.drawable.icon311);
        myMap.put(312, R.drawable.icon312);
        myMap.put(313, R.drawable.icon313);
        myMap.put(400, R.drawable.icon400);
        myMap.put(401, R.drawable.icon401);
        myMap.put(402, R.drawable.icon402);
        myMap.put(403, R.drawable.icon403);
        myMap.put(404, R.drawable.icon404);
        myMap.put(405, R.drawable.icon405);
        myMap.put(406, R.drawable.icon406);
        myMap.put(407, R.drawable.icon407);
        myMap.put(500, R.drawable.icon500);
        myMap.put(501, R.drawable.icon501);
        myMap.put(502, R.drawable.icon502);
        myMap.put(503, R.drawable.icon503);
        myMap.put(504, R.drawable.icon504);
        myMap.put(507, R.drawable.icon507);
        myMap.put(508, R.drawable.icon508);
        myMap.put(900, R.drawable.icon900);
        myMap.put(901, R.drawable.icon901);
        myMap.put(999, R.drawable.icon999);
    }

    private void initGetFromServer() {
        countyName = getActivity().getIntent().getStringExtra("county_name");
        Log.d("tag", "county_name" + countyName);
        Log.d("tag", "TempMaxTextView:" + tempMaxTextView);
        if (countyName == null) {
            countyName = "宁波";
        }
        toolbar.setTitle(countyName);
        new Thread() {
            public void run() {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://free-api.heweather.com/v5/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                WeatherService weatherService = retrofit.create(WeatherService.class);
                Log.d("tag", "county_name2" + countyName);
                Call<WeatherJson> call = weatherService.getWeather(countyName, "532ba71fed214e29907ee788e8bf8812");
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
//                        int imgId = 0x7f02005e;
//                        imgId=imgId+Integer.parseInt(weatherIcon)-100;
//                        Log.d("tag","map:"+myMap.get(weatherIcon))
                        weatherIconImageView.setBackgroundResource(myMap.get(Integer.parseInt(weatherIcon)));
                        tempTextView.setText(temp + "℃");
                        tempMaxTextView.setText(tempMax + "℃");
                        tempMinTextView.setText(tempMin + "℃");
                        pm25TextView.setText("PM2.5:" + pm25 + "μg/m³    空气质量：" + qlty);
                    }

                    public void onFailure(Call<WeatherJson> call, Throwable t) {
                        System.out.println("retrofit失败");
                    }
                });
            }
        }.start();
        getForeCastData();
    }
    private void getForeCastData() {
        new Thread() {
            public void run() {
                Retrofit retrofitForeCast = new Retrofit.Builder()
                        .baseUrl("https://free-api.heweather.com/v5/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ForecastService forecastService = retrofitForeCast.create(ForecastService.class);
                Call<ForecastJson> callForeCast = forecastService.getWeather(countyName, "532ba71fed214e29907ee788e8bf8812");
                callForeCast.enqueue(new Callback<ForecastJson>() {
                    @Override
                    public void onResponse(Call<ForecastJson> call, Response<ForecastJson> response) {
                        dataList = new ArrayList<Map<String, Object>>();
                        for (int i = 0; i < 3; i++) {
                            Map<String, Object> map = new HashMap<String, Object>();
//                            String forecastWeatherIcon;
//                            String forecastTempMax;
//                            String forecastTempMin;
//                            String forecastWeather;
//                            String forecastWindDir;
//                            String forecastWindSpeed;
//                            String forecastPop;
                            forecastWeatherIcon = response.body().getHeWeather5().get(0).
                                    getDaily_forecast().get(i).getCond().getCode_d();
                            forecastTempMax = response.body().getHeWeather5().get(0).
                                    getDaily_forecast().get(i).getTmp().getMax();
                            forecastTempMin = response.body().getHeWeather5().get(0)
                                    .getDaily_forecast().get(i).getTmp().getMin();
                            forecastWeather = response.body().getHeWeather5().get(0)
                                    .getDaily_forecast().get(i).getCond().getTxt_d();
                            forecastWindDir = response.body().getHeWeather5().get(0)
                                    .getDaily_forecast().get(i).getWind().getDir();
                            forecastWindSpeed = response.body().getHeWeather5().get(0)
                                    .getDaily_forecast().get(i).getWind().getSpd();
                            forecastPop = response.body().getHeWeather5().get(0)
                                    .getDaily_forecast().get(i).getPop();
                            Log.d("tag", forecastWeatherIcon + forecastTempMax + forecastTempMin +
                                    forecastWeather + forecastWindDir + forecastWindSpeed + forecastPop);
                            map.put("weather_icon",myMap.get(Integer.parseInt(forecastWeatherIcon)));
                            switch (i) {
                                case 0:
                                    map.put("date", "今天");
                                    break;
                                case 1:
                                    map.put("date", "明天");
                                    break;
                                case 2:
                                    map.put("date", "后天");
                                    break;
                            }
                            map.put("temp", forecastTempMin + "℃ - " + forecastTempMax + "℃");
                            map.put("description", forecastWeather + "。 " + forecastWindDir + forecastWindSpeed
                                    + "km/h。 降水几率" + forecastPop + "%。");
                            Log.d("tag",map.get("weather_icon").toString()+map.get("date")+map.get("temp")+map.get("description"));
                            dataList.add(map);
                        }
                        simpleAdapter = new SimpleAdapter(getActivity(), dataList, R.layout.forecast_layout, new String[]{
                                "weather_icon", "date", "temp", "description"}, new int[]{
                                R.id.image, R.id.date, R.id.temp, R.id.description});
                        listView.setAdapter(simpleAdapter);
                    }

                    @Override
                    public void onFailure(Call<ForecastJson> call, Throwable t) {
                        System.out.println("获取forecast失败");
                    }
                });
            }
        }.start();


    }

    public void onResume() {
        super.onResume();
        initGetFromServer();
    }

    public String getCountyName() {
        return countyName;
    }
}

