package com.example.zhm.weather;

import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhm.weather.TodayFragment;
import com.example.zhm.weather.R;

public class MainActivity extends FragmentActivity {
    private Dialog dialog;
    private FragmentManager manager;
    private FrameLayout ll_main_fragment;
    private FragmentTransaction transaction;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        manager=getSupportFragmentManager();

        initView();
    }
    /**
     *初始化
     */
    private void initView(){
        //初始化控件
        ll_main_fragment= (FrameLayout) findViewById(R.id.ll_main_fragment);
        //初始化实例
        manager=getSupportFragmentManager();


        //方法执行
        addFragment(new TodayFragment());
    }

    /**
     * FramLayout添加
     */
    public void addFragment(Fragment fragment) {
        transaction = manager.beginTransaction();
        transaction.replace(R.id.ll_main_fragment, fragment);
        transaction.commit();
    }
    /**
     *加载动画页面
     */
    public void showLoadingDialog(String msg,boolean cancelable) {
        View v = getLayoutInflater().inflate(R.layout.dialog_loading, null);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);
        ImageView im = (ImageView) v.findViewById(R.id.iv_dialogloading_img);
        TextView tv = (TextView) v.findViewById(R.id.tv_dialogloading_msg);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.loading_animation);
        im.setAnimation(animation);
        if(msg!=null){
            tv.setText(msg);
        }
        dialog = new Dialog(this,R.style.loading_dialog);
        dialog.setCancelable(cancelable);
        dialog.setContentView(layout);
        dialog.show();
    }

    public void cancelDialog(){
        if(dialog != null){
            dialog.dismiss();
        }
    }
}
