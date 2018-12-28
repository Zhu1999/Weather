package com.example.zhm.weather;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.zhm.weather.CityTypeAdapter;
import com.example.zhm.weather.R;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class CityTypeActivity extends Activity {
    private ListView lv_city_type;
    private CityTypeAdapter cityTypeAdapter;
    private ArrayList<String> arrayList;
    private String city="";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_city_type);
        arrayList=new ArrayList<String>();
        addArray();
        init();
    }
    //初始化数据
    private void init(){
        lv_city_type= (ListView) findViewById(R.id.lv_city_type);
        cityTypeAdapter=new CityTypeAdapter(this);
        cityTypeAdapter.setArrayList(arrayList);
        lv_city_type.setAdapter(cityTypeAdapter);
        listViewLintener();
    }
    /**
     * listview的点击事件
     */
    private void listViewLintener(){
        lv_city_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                city=arrayList.get(position);
                intent.putExtra("city",city);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK) {
            Intent intent=new Intent();
            intent.putExtra("city","");
            setResult(RESULT_OK, intent);
            finish();
        }
        return true;
    }

    /**
     * 返回按钮的点击事件重写
     */

    private void addArray(){
        arrayList.add("北京");
        arrayList.add("上海");
        arrayList.add("天津");
        arrayList.add("深圳");
        arrayList.add("广东");
        arrayList.add("成都");
        arrayList.add("重庆");
        arrayList.add("杭州");
        arrayList.add("南京");
        arrayList.add("沈阳");
        arrayList.add("苏州");
        arrayList.add("武汉");
        arrayList.add("西安");
        arrayList.add("长沙");
        arrayList.add("大连");
        arrayList.add("济南");
        arrayList.add("宁波");
        arrayList.add("青岛");
        arrayList.add("无锡");
        arrayList.add("厦门");
        arrayList.add("郑州");
        arrayList.add("长春");
        arrayList.add("常州");
        arrayList.add("哈尔滨");
        arrayList.add("福州");
        arrayList.add("昆明");
        arrayList.add("东莞");
    }
}
