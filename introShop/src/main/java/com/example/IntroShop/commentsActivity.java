package com.example.IntroShop;


import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.IntroShop.customView.DiyViewpager;
import com.example.IntroShop.model.contenttable;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class commentsActivity extends Activity {

    private TextView maintext = null;
    private TextView textTitle = null;
    public double contentId;
    FloatingActionButton send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.comments_activity);
        send = (FloatingActionButton) findViewById(R.id.send);
        send.setPadding(0, 0, 0, 20);
    }
}