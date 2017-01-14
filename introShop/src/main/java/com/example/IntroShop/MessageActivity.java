package com.example.IntroShop;

import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;

public class MessageActivity extends AppCompatActivity {
    private ViewPager messageViewPager = null;
    private List<LinearLayout> pagerlList = new ArrayList<LinearLayout>();
    private Button settingButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);
        messageViewPager = (ViewPager) findViewById(R.id.viewpager_message);
        settingButton = (Button) findViewById(R.id.Button_Setting2);
        settingButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(MessageActivity.this, Activity_setting.class);
                startActivity(intent);
            }
        });

    }

}
