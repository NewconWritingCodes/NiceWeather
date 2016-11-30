package com.hyperchain.niceweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.hyperchain.niceweather.db.NiceWeatherDB;
import com.hyperchain.niceweather.model.MultiCity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Newcon on 2016/11/21.
 */
public class MultiFragment extends Fragment {
    NiceWeatherDB niceWeatherDB;
    List<MultiCity> listMulti = null;
    //SimpleAdapter的数据
    List<Map<String, Object>> listMap = null;
    SimpleAdapter simpleAdapter;
    ListView listView;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.multi_layout, container, false);
        niceWeatherDB = NiceWeatherDB.getInstance(getActivity());
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag", "fab触摸");
                Intent intent = new Intent(getActivity(), ChooseActivity.class);
                intent.putExtra("view_item", 1);
                intent.putExtra("add_item", 1);
                startActivity(intent);
                getActivity().finish();
            }
        });
        listView = (ListView) view.findViewById(R.id.listViewMulti);
        loadCity();
        return view;
    }

    public List<Map<String, Object>> getData(List<MultiCity> data) {
        List<Map<String, Object>> listData = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("city", data.get(i).getCity());
            map.put("temp", data.get(i).getTemp() + "℃");
            map.put("weather", data.get(i).getWeather());
            listData.add(map);
        }
        return listData;
    }


    private void loadCity() {
        listMulti = niceWeatherDB.loadMultiCity();
        Log.d("load",niceWeatherDB.loadMultiCity().toString());
        if (listMulti.size() > 0) {
            listMap = getData(listMulti);
            simpleAdapter = new SimpleAdapter(getActivity(), listMap, R.layout.multi_simple_adapter,
                    new String[]{"city", "weather", "temp"}, new int[]{R.id.city_tv, R.id.weather_tv, R.id.temp_tv});
            listView.setAdapter(simpleAdapter);
            simpleAdapter.notifyDataSetChanged();
            listView.setSelection(0);
        }
    }
}
