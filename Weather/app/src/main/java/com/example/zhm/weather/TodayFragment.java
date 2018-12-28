package com.example.zhm.weather;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhm.weather.CityTypeActivity;
import com.example.zhm.weather.MainActivity;
import com.example.zhm.weather.NowEntity;
import com.example.zhm.weather.TodayEntity;
import com.example.zhm.weather.R;
import com.example.zhm.weather.MyImageView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class TodayFragment extends Fragment implements View.OnClickListener {

    private View view;    //当前布局
    private LinearLayout ll_main_choose_city;    //点击选择城市
    private TextView tv_mian_show_city;          //显示当前城市
    private TextView tv_mian_load_more;          //点击显示更多
    private String url="http://v.juhe.cn/weather/index?format=2";
    private String key="b280fde7e3b8cb822a77dc82a29800d6";
    private OkHttpClient okHttpClient;
    private NowEntity nowEntity;    //存储当前天气信息
    private TextView tv_tody_now_city;
    private TextView tv_tody_timp;
    private TextView tv_tody_wind_direction;
    private TextView tv_tody_wind_strength;
    private MyImageView iv_weather_icon;
    private MainActivity main;
    private String city;
    private TextView tv_tody_city;
    private TextView tv_tody_data_y;
    private TextView tv_tody_week;
    private TextView tv_today_weather;
    private TextView tv_today_temperature;
    private TextView tv_today_wind;
    private TextView tv_today_humidity;
    private ImageView iv_today_weather_icon;
    private TextView tv_today_warn;
    private TodayEntity todayEntity;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_today,container,false);
        init();
        return view;
    }

    /**
     * 初始化数据
     */
    private void init(){
        //初始化控件
        ll_main_choose_city= (LinearLayout) view.findViewById(R.id.ll_main_choose_city);
        ll_main_choose_city.setOnClickListener(this);
        tv_mian_show_city= (TextView) view.findViewById(R.id.tv_mian_show_city);
        tv_mian_show_city.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tv_mian_load_more= (TextView) view.findViewById(R.id.tv_mian_load_more);
        tv_mian_load_more.setOnClickListener(this);
        tv_tody_now_city= (TextView) view.findViewById(R.id.tv_tody_now_city);
        tv_tody_timp= (TextView) view.findViewById(R.id.tv_tody_timp);
        tv_tody_wind_direction= (TextView) view.findViewById(R.id.tv_tody_wind_direction);
        tv_tody_wind_strength= (TextView) view.findViewById(R.id.tv_tody_wind_strength);
        iv_weather_icon= (MyImageView) view.findViewById(R.id.iv_weather_icon);
        iv_weather_icon.setImageResource(R.mipmap.link);
        tv_tody_city= (TextView) view.findViewById(R.id.tv_tody_city);
        tv_tody_data_y= (TextView) view.findViewById(R.id.tv_tody_data_y);
        tv_tody_week= (TextView) view.findViewById(R.id.tv_tody_week);
        tv_today_weather= (TextView) view.findViewById(R.id.tv_today_weather);
        tv_today_temperature= (TextView) view.findViewById(R.id.tv_today_temperature);
        tv_today_wind= (TextView) view.findViewById(R.id.tv_today_wind);
        tv_today_humidity= (TextView) view.findViewById(R.id.tv_today_humidity);
        iv_today_weather_icon= (ImageView) view.findViewById(R.id.iv_today_weather_icon);
        tv_today_warn= (TextView) view.findViewById(R.id.tv_today_warn);
        //初始化数据
        main=(MainActivity)getActivity();
        //默认进入时加载北京天气情况
        LoadCity("北京");
        tv_mian_show_city.setText("北京");
    }

    /**
     *点击事件的监听
     */
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_main_choose_city:   //选择城市的点击事件
                Intent intent=new Intent(main, CityTypeActivity.class);
                startActivityForResult(intent,0);
                break;
            case R.id.tv_mian_load_more:   //加载更多的点击事件
                //当点击未来时，跳转fragment
                //加载未来6天的天气状况
                FutureFragment future=new FutureFragment();
                Bundle mBundle=new Bundle();
                mBundle.putString("city",city);
                future.setArguments(mBundle);
                main.addFragment(future);
                break;
        }
    }
    /**
     * 回调方法
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        city =data.getStringExtra("city");
        if(!city.equals("")){
            tv_mian_show_city.setText(city);    //设置当前选择的城市
            //加载当前所选城市的天气情况
            LoadCity(city);
        }
    }
    /**
     * 加载所选城市的天气情况
     * http://v.juhe.cn/weather/index?format=2&cityname=%E8%8B%8F%E5%B7%9E&key=您申请的KEY
     */
    private void LoadCity(String city){
        main.showLoadingDialog("正在加载...",true);
        String utlStr=url+"&cityname="+city+"&key="+key;
        okHttpClient=new OkHttpClient();
        Request request=new Request.Builder()
                .url(utlStr)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            public void onFailure(Request request, IOException e) {
            }
            public void onResponse(Response response) throws IOException {
                final String str=response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setResult(str);
                    }
                });

            }
        });
    }
    /**
     * 显示请求结果
     */
    private void setResult(String s){
        //对返回的s字符串进行解析
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONObject result=jsonObject.getJSONObject("result");
            JSONObject sk=result.getJSONObject("sk");
            //当前的实际天气情况
            String temp=sk.getString("temp");
            String wind_direction=sk.getString("wind_direction");
            String wind_strength=sk.getString("wind_strength");
            String humidity=sk.getString("humidity");
            String time=sk.getString("time");
            JSONObject today=result.getJSONObject("today");
            String today_city=today.getString("city");
            String date_y=today.getString("date_y");
            String week=today.getString("week");
            String temperature=today.getString("temperature");
            String weather=today.getString("weather");
            JSONObject weather_id=today.getJSONObject("weather_id");
            String fa=weather_id.getString("fa");
            String fb=weather_id.getString("fb");
            String wind=today.getString("wind");
            String dressing_index=today.getString("dressing_index");
            String dressing_advice=today.getString("dressing_advice");
            String uv_index=today.getString("uv_index");
            String comfort_index=today.getString("comfort_index");
            String wash_index=today.getString("wash_index");
            String travel_index=today.getString("travel_index");
            String exercise_index=today.getString("exercise_index");
            String drying_index=today.getString("drying_index");

            //当前时间天气情况----实体类
            nowEntity=new NowEntity(temp,wind_direction,wind_strength,humidity,time);

            //今天的总体天气情况----实体类
            todayEntity=new TodayEntity(today_city,date_y,week,temperature,weather,fa,fb
                    ,wind,dressing_index,dressing_advice,uv_index,comfort_index,wash_index,travel_index
                    ,exercise_index,drying_index);

            setNowWeather();
        }catch (Exception e){}
    }

    /**
     * 设置天气控件的属性
     */
    private void setNowWeather(){
        //当前时间控件属性设置
        city=todayEntity.getCity();
        tv_tody_now_city.setText(todayEntity.getCity());
        tv_tody_timp.setText(nowEntity.getTemp()+" °C");
        tv_tody_wind_strength.setText(nowEntity.getWind_strength());
        tv_tody_wind_direction.setText(nowEntity.getWind_direction());
        //今天总体天气状况   控件属性设置
        tv_tody_city.setText(todayEntity.getCity());
        tv_tody_data_y.setText(todayEntity.getDate_y());
        tv_tody_week.setText(todayEntity.getWeek());
        tv_today_weather.setText(todayEntity.getWeather());
        tv_today_temperature.setText(todayEntity.getTemperature());
        tv_today_wind.setText(nowEntity.getHumidity());
        tv_today_humidity.setText(nowEntity.getWind_direction()+nowEntity.getWind_strength());
        String warn="   今日"+todayEntity.getWind()+","+todayEntity.getDressing_index()+","+
                todayEntity.getDressing_advide()+",舒适度指数:"+todayEntity.getComfort_index()+";"+
                "紫外线强度:"+todayEntity.getUv_index()+";洗车指数："+todayEntity.getWash_index()+";"+
                "旅游指数:"+todayEntity.getTravel_index()+";晨练指数："+todayEntity.getExercise_index()+";"+
                "干燥指数:"+todayEntity.getDrying_index()+"。";
        tv_today_warn.setText(warn);
        main.cancelDialog();
    }
}
