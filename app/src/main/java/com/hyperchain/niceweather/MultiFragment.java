package com.hyperchain.niceweather;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
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
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                        .setMessage("是否删除该城市？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(getActivity(), "点击了确定"+position, Toast.LENGTH_SHORT).show();
                                String city = listMap.get(position).get("city").toString();
                                Log.d("tag",city);
                                niceWeatherDB.deleteMultiCity(city);
                                listMulti = niceWeatherDB.loadMultiCity();
                                Log.d("load",niceWeatherDB.loadMultiCity().toString());
                                listMap.clear();
                                listMap.addAll(getData(listMulti));
                                /*
                                内容有个坑点
                                在notifyDataSetChanged更新数据时，会检测listMap的指针是否发生变化，
                                如果变化了，则不做操作。所以不能直接用listMap=getData(listMulti);
                                而是使用listMap.addAll(getData(listMulti));
                                 */
                                Log.d("load",""+listMap.size());
                                simpleAdapter.notifyDataSetChanged();
                                listView.setSelection(0);

                            }
                        });
                builder.create().show();
                return false;
            }
        });
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
