package com.hyperchain.pageradapterdemo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        List<Map<String,Object>> data = new ArrayList<>();
        for(int i=0;i<100;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("image",R.mipmap.ic_launcher);
            map.put("title","Title"+i);
            map.put("content","Content"+i);
            data.add(map);
        }
        MyBaseAdapter myBaseAdapter = new MyBaseAdapter(data,this);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(myBaseAdapter);
    }
}
class MyBaseAdapter extends BaseAdapter{
    private List<Map<String,Object>> data;
    private Context mContext;


    public MyBaseAdapter(List<Map<String, Object>> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Map<String,Object> getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView =LayoutInflater.from(mContext).inflate(R.layout.array_item,null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.title_tv = (TextView) convertView.findViewById(R.id.title_tv);
            viewHolder.content_tv = (TextView) convertView.findViewById(R.id.content_tv);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.imageView.setImageResource((Integer) data.get(position).get("image"));
        viewHolder.title_tv.setText((String) data.get(position).get("title"));
        viewHolder.content_tv.setText((String) data.get(position).get("content"));
        return convertView;


    }
    class ViewHolder{
        ImageView imageView;
        TextView title_tv;
        TextView content_tv;
    }
}
