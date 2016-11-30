package com.hyperchain.niceweather;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.hyperchain.niceweather.bean.WeatherJson;
import com.hyperchain.niceweather.db.NiceWeatherDB;
import com.hyperchain.niceweather.model.City;
import com.hyperchain.niceweather.model.County;
import com.hyperchain.niceweather.model.MultiCity;
import com.hyperchain.niceweather.model.Province;
import com.hyperchain.niceweather.retrofit.WeatherService;
import com.hyperchain.niceweather.util.HttpCallbackListener;
import com.hyperchain.niceweather.util.HttpUtil;
import com.hyperchain.niceweather.util.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Newcon on 2016/11/28.
 */
public class ChooseActivity extends Activity {
    Toolbar toolbar;
    ListView listView;
    List<String> dataList;
    ArrayAdapter adapter;

    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTY = 2;
    private ProgressDialog progressDialog;
    private NiceWeatherDB niceWeatherDB;
    /**
     * 省列表
     */
    private List<Province> provinceList;
    /**
     * 市列表
     */
    private List<City> cityList;
    /**
     * 县列表
     */
    private List<County> countyList;
    /**
     * 选中的省份
     */
    private Province selectedProvince;
    /**
     * 选中的城市
     */
    private City selectedCity;
    /**
     * 当前选中的级别
     */
    private int currentLevel;
    String oldCountyName=null;
    int view_item=0;
    int add_item =0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_layout);
        oldCountyName = getIntent().getStringExtra("county_name");
        view_item = getIntent().getIntExtra("view_item",0);
        add_item = getIntent().getIntExtra("add_item",0);
        initFindViewbyId();
        initListView();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initListView() {
        dataList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        niceWeatherDB = NiceWeatherDB.getInstance(this);
        queryProvinces();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentLevel == LEVEL_PROVINCE) {
                    selectedProvince = provinceList.get(position);
                    queryCities();
                } else if (currentLevel == LEVEL_CITY) {
                    selectedCity = cityList.get(position);
                    queryCounties();
                } else if (currentLevel == LEVEL_COUNTY) {
                    final String countyName = countyList.get(position).getCountyName();
                    final Intent intent = new Intent(ChooseActivity.this, MainActivity.class);
                    intent.putExtra("county_name", countyName);
                    if(add_item==0) {
                        startActivity(intent);
                        finish();
                    }else{
                        intent.putExtra("view_item",1);
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://free-api.heweather.com/v5/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        WeatherService weatherService = retrofit.create(WeatherService.class);
                        Call<WeatherJson> call = weatherService.getWeather(countyName, "532ba71fed214e29907ee788e8bf8812");
                        call.enqueue(new Callback<WeatherJson>() {
                            @Override
                            public void onResponse(Call<WeatherJson> call, Response<WeatherJson> response) {
                                String weather = response.body().getHeWeather5().get(0)
                                        .getNow().getCond().getTxt();
                                Log.d("tag","传回来的weather为"+weather);
                                String temp = response.body().getHeWeather5().get(0)
                                        .getNow().getTmp();
                                Log.d("tag","传回来的temp为"+temp);
                                MultiCity multiCity = new MultiCity();
                                multiCity.setCity(countyName);
                                multiCity.setWeather(weather);
                                multiCity.setTemp(temp);
                                niceWeatherDB.saveMultiCity(multiCity);
                                startActivity(intent);
                                finish();

                            }

                            @Override
                            public void onFailure(Call<WeatherJson> call, Throwable t) {
                                System.out.println("获取失败");
                            }
                        });
                    }

                }
            }
        });


    }

    private void initFindViewbyId() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.listView);
    }

    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在加载");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    private void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    /*
查询全国所有的省，优先从数据库查询，如果没有查询到再去服务器上查询。
 */
    private void queryProvinces() {
        provinceList = niceWeatherDB.loadProvince();
        if (provinceList.size() > 0) {
            dataList.clear();
            for (Province province : provinceList) {
                dataList.add(province.getProvinceName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            toolbar.setTitle("选择省份");
            currentLevel = LEVEL_PROVINCE;
        } else {
            queryFromServer(null, "province");
        }
    }

    /*
    查询省内所有的市，优先从数据库查询，如果没有查询到再去服务器上查询
     */
    private void queryCities() {
        cityList = niceWeatherDB.loadCities(selectedProvince.getId());
        if (cityList.size() > 0) {
            dataList.clear();
            for (City city : cityList) {
                dataList.add(city.getCityName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            toolbar.setTitle("选择城市");
            currentLevel = LEVEL_CITY;
        } else {
            queryFromServer(selectedProvince.getProvinceCode(), "city");
        }

    }

    /**
     * 查询选中市内所有的县，优先从数据库查询，如果没有查询到再去服务器上查询。
     */
    private void queryCounties() {
        countyList = niceWeatherDB.loadCounties(selectedCity.getId());
        if (countyList.size() > 0) {
            dataList.clear();
            for (County county : countyList) {
                dataList.add(county.getCountyName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            toolbar.setTitle("选择地区");
            currentLevel = LEVEL_COUNTY;
        } else {
            queryFromServer(selectedCity.getCityCode(), "county");
        }
    }

    /*
    根据传入的代号和类型从服务器上查询省市县数据。
     */
    private void queryFromServer(final String code, final String type) {
        String address;
        //504
        if (!TextUtils.isEmpty(code)) {
            address = "http://www.weather.com.cn/data/list3/city" + code +
                    ".xml";
        } else {
            address = "http://www.weather.com.cn/data/list3/city.xml";
        }
        showProgressDialog();
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                boolean result = false;
                if ("province".equals(type)) {
                    result = Utility.handleProvincesResponse(niceWeatherDB, response);
                } else if ("city".equals(type)) {
                    result = Utility.handleCitiesResponse(niceWeatherDB, response, selectedProvince.getId());

                } else if ("county".equals(type)) {
                    result = Utility.handleCountiesResponse(niceWeatherDB, response, selectedCity.getId());

                }
                if (result) {
                    //通过runOnUiThread()方法回到主线程处理逻辑
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideProgressDialog();
                            if ("province".equals(type)) {
                                queryProvinces();
                            } else if ("city".equals(type)) {
                                queryCities();
                            } else if ("county".equals(type)) {
                                queryCounties();
                            }
                        }
                    });
                }

            }

            @Override
            public void onError(Exception e) {
                System.out.println("加载失败");
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgressDialog();
                        Toast.makeText(ChooseActivity.this,
                                "加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void onBackPressed() {
        if (currentLevel == LEVEL_COUNTY) {
            queryCities();
        } else if (currentLevel == LEVEL_CITY) {
            queryProvinces();
        } else {
            Intent returnIntent = new Intent(ChooseActivity.this, MainActivity.class);
            Log.d("tag","oldCountyName"+oldCountyName);
            returnIntent.putExtra("county_name", oldCountyName);
            returnIntent.putExtra("view_item",view_item);
            startActivity(returnIntent);
            finish();
        }
    }
}
