package com.example.IntroShop;

import java.util.ArrayList;
import java.util.List;


import android.R.integer;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;

public class TabFra1 extends Fragment {


    private Button button = null;

    private List<ImageView> list = new ArrayList<ImageView>();
    ;

    private int num = 300;
    private Button selectButton1 = null;
    private Button selectButton2 = null;
    private Button selectButton3 = null;
    private Button selectButton4 = null;
    private Button selectButton5 = null;
    private Button selectButton6 = null;
    private Button selectButton7 = null;
    private Button selectButton8 = null;
    private Button selectButton9 = null;
    private Button selectButton10 = null;
    private Button selectButton11 = null;
    private Button selectButton12 = null;
    private Button selectButton13 = null;
    private Button settingButton = null;
    private ImageView imageView1, imageView2, imageView3;
    ImageView img = null;
    private MySwipe swipeRefreshLayout;
    private ScrollView parent;
    LinearLayout lin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootvView1 = inflater.inflate(R.layout.tab1, container, false);

//        selectButton1 = (Button) rootvView1.findViewById(R.id.select_button1);
//        selectButton2 = (Button) rootvView1.findViewById(R.id.select_button2);
//        selectButton3 = (Button) rootvView1.findViewById(R.id.select_button3);
//        selectButton4 = (Button) rootvView1.findViewById(R.id.select_button4);
//        selectButton5 = (Button) rootvView1.findViewById(R.id.select_button5);
//        selectButton6 = (Button) rootvView1.findViewById(R.id.select_button6);
//        selectButton7 = (Button) rootvView1.findViewById(R.id.select_button7);
//        selectButton8 = (Button) rootvView1.findViewById(R.id.select_button8);
//        selectButton9 = (Button) rootvView1.findViewById(R.id.select_button9);
//        selectButton10 = (Button) rootvView1.findViewById(R.id.select_button10);
//        selectButton11 = (Button) rootvView1.findViewById(R.id.select_button11);
//        selectButton12 = (Button) rootvView1.findViewById(R.id.select_button12);
//        selectButton13 = (Button) rootvView1.findViewById(R.id.select_button13);
//        setButtonListener();
        //        parent = (ScrollView) rootvView1.findViewById(R.id.ItemsParent);
        settingButton = (Button) rootvView1.findViewById(R.id.Button_Setting);
        settingButton.setOnClickListener(new SelectListener());
//        parent.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    swipeRefreshLayout.setEnabled(true);
//                }
//
//                return false;
//            }
//        });

        img = (ImageView) rootvView1.findViewById(R.id.gride);
        Glide.with(getActivity()).
                load("http://i12.tietuku.cn/3db89f0d6162f62d.png")
                .into(img);
        swipeRefreshLayout = (MySwipe) rootvView1.findViewById(R.id.swipeTab1);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(new MySwipe.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(getActivity(), "刷新完成", Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                        SuperToast.create(getActivity(), "刷新完成", SuperToast.Duration.MEDIUM,
                                Style.getStyle(Style.BLUE, SuperToast.Animations.FLYIN)).show();
                    }
                }, 5000);
            }
        });

        return rootvView1;

    }

//    public void setButtonListener() {
//        selectButton13.setOnClickListener(new SelectListener());
//        selectButton1.setOnTouchListener(this);
//        selectButton2.setOnTouchListener(this);
//        selectButton3.setOnTouchListener(this);
//        selectButton4.setOnTouchListener(this);
//        selectButton5.setOnTouchListener(this);
//        selectButton6.setOnTouchListener(this);
//        selectButton7.setOnTouchListener(this);
//        selectButton8.setOnTouchListener(this);
//        selectButton9.setOnTouchListener(this);
//        selectButton10.setOnTouchListener(this);
//        selectButton11.setOnTouchListener(this);
//        selectButton12.setOnTouchListener(this);
//        selectButton13.setOnTouchListener(this);
//        selectButton1.setOnClickListener(new SelectListener());
//        selectButton2.setOnClickListener(new SelectListener());
//        selectButton3.setOnClickListener(new SelectListener());
//        selectButton4.setOnClickListener(new SelectListener());
//        selectButton5.setOnClickListener(new SelectListener());
//        selectButton6.setOnClickListener(new SelectListener());
//        selectButton7.setOnClickListener(new SelectListener());
//        selectButton8.setOnClickListener(new SelectListener());
//        selectButton9.setOnClickListener(new SelectListener());
//        selectButton10.setOnClickListener(new SelectListener());
//        selectButton11.setOnClickListener(new SelectListener());
//        selectButton12.setOnClickListener(new SelectListener());
//    }
//
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        if (MotionEvent.ACTION_DOWN == event.getAction()) {
//            switch (v.getId()) {
//                case R.id.select_button1:
//                case R.id.select_button2:
//                case R.id.select_button6:
//                case R.id.select_button9:
//                case R.id.select_button3:
//                case R.id.select_button7:
//                case R.id.select_button10:
//                case R.id.select_button4:
//                case R.id.select_button8:
//                case R.id.select_button5:
//                case R.id.select_button11:
//                case R.id.select_button12:
//                case R.id.select_button13:
//                    swipeRefreshLayout.setEnabled(false);
//                    break;
//
//
//            }
//        }

//
//        return false;
//    }

    private class SelectListener implements OnClickListener {
        //
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
//                case R.id.select_button1:
//                    this.setcolor();
//                    this.setTextSize();
//                    selectButton1.setTextColor(Color.rgb(230, 0, 0));
//                    selectButton1.setTextSize((float) this.px2sp(75));
//
//                    break;
//                case R.id.select_button2:
//                    this.setcolor();
//                    this.setTextSize();
//                    selectButton2.setTextColor(Color.rgb(60, 170, 60));
//                    selectButton2.setTextSize((float) this.px2sp(75));
//
//                    break;
//                case R.id.select_button3:
//                    this.setcolor();
//                    this.setTextSize();
//                    selectButton3.setTextColor(Color.rgb(60, 170, 60));
//                    selectButton3.setTextSize((float) this.px2sp(75));
//                    break;
//                case R.id.select_button4:
//                    this.setcolor();
//                    this.setTextSize();
//                    selectButton4.setTextColor(Color.rgb(60, 170, 60));
//                    selectButton4.setTextSize((float) this.px2sp(75));
//                    break;
//                case R.id.select_button5:
//                    this.setcolor();
//                    this.setTextSize();
//                    selectButton5.setTextColor(Color.rgb(60, 170, 60));
//                    selectButton5.setTextSize((float) this.px2sp(75));
//                    break;
//                case R.id.select_button6:
//                    this.setcolor();
//                    this.setTextSize();
//                    selectButton6.setTextColor(Color.rgb(60, 170, 60));
//                    selectButton6.setTextSize((float) this.px2sp(75));
//                    break;
//                case R.id.select_button7:
//                    this.setcolor();
//                    this.setTextSize();
//                    selectButton7.setTextColor(Color.rgb(60, 170, 60));
//                    selectButton7.setTextSize((float) this.px2sp(75));
//                    break;
//                case R.id.select_button8:
//                    this.setcolor();
//                    this.setTextSize();
//                    selectButton8.setTextColor(Color.rgb(60, 170, 60));
//                    selectButton8.setTextSize((float) this.px2sp(75));
//                    break;
//                case R.id.select_button9:
//                    this.setcolor();
//                    this.setTextSize();
//                    selectButton9.setTextColor(Color.rgb(60, 170, 60));
//                    selectButton9.setTextSize((float) this.px2sp(75));
//                    break;
//                case R.id.select_button10:
//                    this.setcolor();
//                    this.setTextSize();
//                    selectButton10.setTextColor(Color.rgb(60, 170, 60));
//                    selectButton10.setTextSize((float) this.px2sp(75));
//                    break;
//                case R.id.select_button11:
//                    this.setcolor();
//                    this.setTextSize();
//                    selectButton11.setTextColor(Color.rgb(60, 170, 60));
//                    selectButton11.setTextSize((float) this.px2sp(75));
//                    break;
//                case R.id.select_button12:
//                    this.setcolor();
//                    this.setTextSize();
//                    selectButton12.setTextColor(Color.rgb(60, 170, 60));
//                    selectButton12.setTextSize((float) this.px2sp(75));
//                    break;
//                case R.id.select_button13:
//                    this.setcolor();
//                    this.setTextSize();
//                    selectButton13.setTextColor(Color.rgb(60, 170, 60));
//                    selectButton13.setTextSize((float) this.px2sp(75));
//                    break;
                case R.id.Button_Setting:
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), Activity_setting.class);
                    startActivity(intent);

                    break;
                default:
                    break;
            }
        }
//
//        public void setcolor() {
//            selectButton1.setTextColor(Color.rgb(0, 0, 0));
//            selectButton2.setTextColor(Color.rgb(0, 0, 0));
//            selectButton3.setTextColor(Color.rgb(0, 0, 0));
//            selectButton4.setTextColor(Color.rgb(0, 0, 0));
//            selectButton5.setTextColor(Color.rgb(0, 0, 0));
//            selectButton6.setTextColor(Color.rgb(0, 0, 0));
//            selectButton7.setTextColor(Color.rgb(0, 0, 0));
//            selectButton8.setTextColor(Color.rgb(0, 0, 0));
//            selectButton9.setTextColor(Color.rgb(0, 0, 0));
//            selectButton10.setTextColor(Color.rgb(0, 0, 0));
//            selectButton11.setTextColor(Color.rgb(0, 0, 0));
//            selectButton12.setTextColor(Color.rgb(0, 0, 0));
//            selectButton13.setTextColor(Color.rgb(0, 0, 0));
//
//        }
//
//        public float px2dip(double d) {
//            final float scale = getResources().getDisplayMetrics().density;
//            return (int) (d / scale + 0.5f);
//        }
//
//        public void setTextSize() {
//            selectButton1.setTextSize(this.px2dip(58));
//            selectButton2.setTextSize(this.px2dip(58));
//            selectButton3.setTextSize(this.px2dip(58));
//            selectButton4.setTextSize(this.px2dip(58));
//            selectButton5.setTextSize(this.px2dip(58));
//            selectButton6.setTextSize(this.px2dip(58));
//            selectButton7.setTextSize(this.px2dip(58));
//            selectButton8.setTextSize(this.px2dip(58));
//            selectButton9.setTextSize(this.px2dip(58));
//            selectButton10.setTextSize(this.px2dip(58));
//            selectButton11.setTextSize(this.px2dip(58));
//            selectButton12.setTextSize(this.px2dip(58));
//            selectButton13.setTextSize(this.px2dip(58));
//        }
//
//        public double px2sp(int pxValue) {
//            final double fontScale = getResources().getDisplayMetrics().scaledDensity;
//            return (double) (pxValue / fontScale + 0.5f);
//        }
    }

}
