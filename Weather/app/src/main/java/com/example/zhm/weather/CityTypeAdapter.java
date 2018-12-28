package com.example.zhm.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zhm.weather.R;

import java.util.ArrayList;

public class CityTypeAdapter extends BaseAdapter {
    private ArrayList<String> mList;
    private LayoutInflater inflater;
    public CityTypeAdapter(Context context){
        mList=new ArrayList<>();
        inflater=LayoutInflater.from(context);
    }
    public int getCount() {
        return mList.size();
    }
    public  void setArrayList(ArrayList<String> list){
        mList=list;
    }
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.item_city_type,null);
            viewHolder.tv_item_city= (TextView) convertView.findViewById(R.id.tv_item_city);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_item_city.setText(mList.get(position));

        return convertView;
    }
    public class ViewHolder{
        private TextView tv_item_city;
    }
}
