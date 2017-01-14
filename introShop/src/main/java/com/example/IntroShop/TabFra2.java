package com.example.IntroShop;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import android.os.Handler;
import android.support.v4.app.Fragment;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;

import android.view.View.OnClickListener;

import android.widget.BaseAdapter;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.sliders.SlideInLeftAnimator;
import com.example.IntroShop.customView.DividerItemDecoration;
import com.example.IntroShop.customView.MySwipeRefreshLayout;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;

import cn.pedant.SweetAlert.SweetAlertDialog;
import it.gmariotti.recyclerview.adapter.AlphaAnimatorAdapter;
import it.gmariotti.recyclerview.adapter.ScaleInAnimatorAdapter;
import it.gmariotti.recyclerview.adapter.SlideInBottomAnimatorAdapter;
import it.gmariotti.recyclerview.adapter.SlideInLeftAnimatorAdapter;
import it.gmariotti.recyclerview.adapter.SlideInRightAnimatorAdapter;
import it.gmariotti.recyclerview.itemanimator.ScaleInOutItemAnimator;
import it.gmariotti.recyclerview.itemanimator.SlideInOutBottomItemAnimator;
import it.gmariotti.recyclerview.itemanimator.SlideInOutLeftItemAnimator;

public class TabFra2 extends Fragment {
    public static String PAGE_NUMBER = "pagenumber";
    private Button button = null;
    private ViewPager viewPager;
    private List<ImageView> list = new ArrayList<ImageView>();
    String[] string;
    ListView mlistView;
    private LinearLayoutManager mLinearLayoutManager;
    int a;
    List bean;
    private int[] imagesid;
    private int num = 300;
    private Button messageButton = null;
    private ImageView imageView1, imageView2, imageView3;
    private android.support.v4.widget.SwipeRefreshLayout SwipeRefreshLayout;
    public DrawerLayout Mydrawer;
    private ImageView map;
    private boolean isScroll = true;
    private RecyclerView mRecyclerView;

    private RecyclerViewAdapter adapter;
    public TestBean load;
    FloatingActionButton upbutton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // getActivity().getWindow()
        // .addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // // ͸��������
        // getActivity().getWindow().addFlags(
        // WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        final View rootvView2 = inflater.inflate(R.layout.tab2_, container, false);
//        Button button = (Button) rootvView2.findViewById(R.id.testac);
//        button.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent=new Intent(getActivity(),UserInfoActivity);
//            }
//        });
        mRecyclerView = (RecyclerView) rootvView2.findViewById(R.id.recyclerview);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        upbutton = (FloatingActionButton) rootvView2.findViewById(R.id.upbutton);

        load = new TestBean(3);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

//        messageButton = (Button) rootvView2.findViewById(R.id.tabmessage);
//        messageButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String string;
//                if (Mydrawer == null) {
//                    string = "Mydrawer=null";
//                } else {
//                    string = "Mydrawer!=null";
//                }
//                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
//                        .setTitleText("" + string)
//                        .setContentText("Won't be able to recover this file!")
//                        .setCancelText("No,cancel plx!")
//                        .setConfirmText("Yes,delete it!")
//                        .showCancelButton(true)
//                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                            @Override
//                            public void onClick(SweetAlertDialog sDialog) {
//
//                                // reuse previous dialog instance, keep widget user state, reset them if you need
//                                sDialog.setTitleText("Cancelled!")
//                                        .setContentText("Your imaginary file is safe :)")
//                                        .setConfirmText("OK")
//                                        .showCancelButton(false)
//                                        .setCancelClickListener(null)
//                                        .setConfirmClickListener(null)
//                                        .changeAlertType(SweetAlertDialog.ERROR_TYPE);
//
//                            }
//                        })
//                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//
//                            @Override
//                            public void onClick(SweetAlertDialog sDialog) {
//                                sDialog.setTitleText("Deleted!")
//                                        .setContentText("Your imaginary file has been deleted!")
//                                        .setConfirmText("OK")
//                                        .showCancelButton(false)
//                                        .setCancelClickListener(null)
//                                        .setConfirmClickListener(null)
//                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
//                            }
//                        })
//                        .show();
//            }
//        });
        Mydrawer = (DrawerLayout) rootvView2.findViewById(R.id.MyDrawer);
        LinearLayout lin = (LinearLayout) rootvView2.findViewById(R.id.lin);
        lin.requestDisallowInterceptTouchEvent(true);
        Mydrawer.setScrimColor(Color.TRANSPARENT);
        map = (ImageView) rootvView2.findViewById(R.id.map);
        map.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), mapActivity.class);
                startActivity(intent);
            }
        });

//        Mydrawer.set
        Mydrawer.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                ((MainActivity) getActivity()).mviewPager.setEnableScoll(true);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

//        messageButton = (Button) rootvView2.findViewById(R.id.tabmessage);
//        messageButton.setOnClickListener(new ClickListener());
        SwipeRefreshLayout = (SwipeRefreshLayout) rootvView2.findViewById(R.id.swipeTab2);
        SwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
//        SwipeRefreshLayout.setBottomColor(android.R.color.holo_blue_light,
//                android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
//        SwipeRefreshLayout.setOnLoadListener(new MySwipeRefreshLayout.OnLoadListener() {
//            @Override
//            public void onLoad() {
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        Toast.makeText(getActivity(), "加载完成", Toast.LENGTH_SHORT).show();
//                        SwipeRefreshLayout.setLoading(false);
//
//                    }
//                }, 5000);
//            }
//        });
        SwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (getActivity() != null) {
                            if (getActivity() != null) {
                                SuperToast.create(getActivity(), "刷新完成", SuperToast.Duration.MEDIUM,
                                        Style.getStyle(Style.BLUE, SuperToast.Animations.FLYIN)).show();
                                SwipeRefreshLayout.setRefreshing(false);
                            }
                        }
                    }
                }, 5000);
            }
        });
        bean = new ArrayList<TestBean>();
        for (int i = 0; i < 20; i++) {
            bean.add(new TestBean(1));
            bean.add(new TestBean(2));
            bean.add(new TestBean(1));
        }
        bean.add(load);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            Boolean isScrolling = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && isScroll) {
                    int lastVisibleItem = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();


                    int totalItemCount = mLinearLayoutManager.getItemCount();

                    if (lastVisibleItem + 1 == adapter.getItemCount()) {
                        LoadMore();
                        isScroll = false;
                    }
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    isScrolling = true;
                    upbutton.setVisibility(View.GONE);
                } else {
                    isScrolling = false;
                    upbutton.setVisibility(View.VISIBLE);
                }
            }
        });

        adapter = new RecyclerViewAdapter(getActivity(), bean);
        SlideInLeftAnimatorAdapter animatorAdapter = new SlideInLeftAnimatorAdapter(adapter, mRecyclerView);
        mRecyclerView.setItemAnimator(new SlideInOutLeftItemAnimator(mRecyclerView));
        mRecyclerView.setAdapter(animatorAdapter);

        upbutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.scrollToPosition(0);
            }
        });
        return rootvView2;


    }

    public void onPreLoadMore() {
//        bean.add(load);
        adapter.notifyDataSetChanged();
    }

    public void onPostLoadMore() {
        bean.remove(load);
        isScroll = true;
        adapter.notifyDataSetChanged();
        bean.add(load);
        adapter.notifyDataSetChanged();
    }

    public void LoadMore() {
        onPreLoadMore();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                bean.add(new TestBean(2));
                bean.add(new TestBean(2));
                bean.add(new TestBean(1));
                bean.add(new TestBean(2));
                bean.add(new TestBean(2));
                bean.add(new TestBean(1));
                bean.add(new TestBean(1));
                bean.add(new TestBean(1));
                bean.add(new TestBean(1));
                bean.add(new TestBean(1));
                bean.add(new TestBean(1));
                bean.add(new TestBean(1));
                bean.add(new TestBean(1));
                bean.add(new TestBean(1));
                bean.add(new TestBean(1));
                onPostLoadMore();
            }
        }, 2000);

    }

    public class ClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
                case R.id.tabmessage:
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), MessageActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }

    }

    @Override
    public void onResume() {
        Log.e("tab2", "-------------resume");
        ((MainActivity) getActivity()).mdrawer = Mydrawer;
        super.onResume();
    }

    @Override
    public void onStart() {
        Log.e("tab2", "-------------start");

        super.onStart();
    }

    @Override
    public void onStop() {
        Log.e("tab2", "-------------stop");
        super.onStop();
    }

    @Override
    public void onPause() {
        Log.e("tab2", "-------------pause");

        super.onPause();
    }

    @Override
    public void onDestroy() {
        Log.e("tab2", "-------------des");

        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.e("tab2", "-------------desview");
        super.onDestroyView();
    }


}
