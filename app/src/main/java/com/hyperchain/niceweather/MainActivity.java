package com.hyperchain.niceweather;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyperchain.niceweather.adapter.MyFragmentAdapter;
import com.hyperchain.niceweather.bean.WeatherJson;
import com.hyperchain.niceweather.retrofit.WeatherService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TabLayout.OnTabSelectedListener {

    TabLayout tabLayout;
    ViewPager viewPager;
    LinearLayout titleLayout;
    Toolbar toolbar;
    double startX = 0;
    double startY = 0;
    double endX = 0;
    double endY = 0;

    //存储当前的城市
    String countyName;
    MainFragment mainFragment;
    MultiFragment multiFragment;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initViewPager();

    }


    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        String[] titles = {"主页面", "多城市"};
        List<Fragment> fragments = new ArrayList<>();
        mainFragment =new MainFragment();
        multiFragment = new MultiFragment();
        fragments.add(mainFragment);
        fragments.add(multiFragment);
        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(myFragmentAdapter);
        tabLayout.setOnTabSelectedListener(this);
        tabLayout.setupWithViewPager(viewPager);
        int i = getIntent().getIntExtra("view_item",0);
        if(i==1) viewPager.setCurrentItem(1);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.nav_choose_city:
//                startActivity(new Intent(MainActivity.this,ChooseActivity.class));
//                finish();
                Intent intent = new Intent(MainActivity.this, ChooseActivity.class);
                Log.d("tag","mainFragment.getCountyName"+mainFragment.getCountyName());
                intent.putExtra("county_name", mainFragment.getCountyName());
                intent.putExtra("view_item",viewPager.getCurrentItem());
                startActivity(intent);
                finish();
                break;
            case R.id.nav_multicity_management:
                viewPager.setCurrentItem(1);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //tab点击选择功能
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    //隐藏动画
//    private void hideToolBar(){
//
//        toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateDecelerateInterpolator());
//    }
//    //显示动画
//    private void showToolBar(){
//        toolbar.animate().translationY(0).setInterpolator(new AccelerateDecelerateInterpolator());
//    }
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                startX = ev.getX();
//                startY = ev.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                endX = ev.getX();
//                endY = ev.getY();
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.v("tag", "startX:" + startX);
//                Log.v("tag", "startY:" + startY);
//                Log.v("tag", "endX:" + endX);
//                Log.v("tag", "endY:" + endY);
//                if (endY - startY > 0 && (Math.abs(endY - startY) > 25)) {
//                    //向下滑動
//                    Log.d("tag", "向下滑动");
////                    showToolBar();
//                } else if (endY - startY < 0 && (Math.abs(endY - startY) > 25)) {
//                    //向上滑动
//                    Log.d("tag", "向上滑动");
////                    hideToolBar();
//                }
//                startX = startY = endX = endY = 0;
//                break;
//        }
//        return super.dispatchTouchEvent(ev);//不消费触摸事件
//    }
}
