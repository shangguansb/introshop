package com.example.IntroShop;

import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;

public class LogIn extends AppCompatActivity {
    private LinearLayout loginbac = null;
    ViewPager viewPager = null;
    private List<Fragment> loginList = new ArrayList<Fragment>();

    private FragmentPagerAdapter loginAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_register);
//        Bmob.initialize(this, "6dd4ae272f1997963617a2138874afd2");
//        // 使用推送服务时的初始化操作
//        BmobInstallation.getCurrentInstallation(this).save();
//        // 启动推送服务
//        BmobPush.startWork(this);
        getWindow()
                .addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // ͸��������
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        loginbac = (LinearLayout) findViewById(R.id.loginbac);
        // Animation animation = new AlphaAnimation(0.1f,
        // 1.0f);animation.setDuration(5000);
        Animation animation = AnimationUtils.loadAnimation(this,
                R.anim.alpha);
        loginbac.setAnimation(animation);
        viewPager = (ViewPager) findViewById(R.id.Viewpagerlogin);
        LoginFragement loginFragement = new LoginFragement();
        registerFragement registerFragement = new registerFragement();
        loginList.add(loginFragement);
        loginList.add(registerFragement);
        loginAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return loginList.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                // TODO Auto-generated method stub
                return loginList.get(arg0);
            }

        };
        viewPager.setAdapter(loginAdapter);
        if (getIntent().getIntExtra("page", 0) == 1) {
            viewPager.setCurrentItem(1);
        }
    }

}
