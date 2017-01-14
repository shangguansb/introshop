package com.example.IntroShop;


import com.bumptech.glide.Glide;
import com.example.IntroShop.customView.MySwipeRefreshLayout;
import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;


import java.io.InputStreamReader;
;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.ViewParent;

import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class TabFra0 extends Fragment implements OnPageChangeListener {

    private Animation animation;
    public ProgressBar bar = null;
    private ImageView tag1 = null;
    private ImageView imagetag1 = null;
    private ViewPager viewPager;
    private List<ImageView> list = new ArrayList<ImageView>();
    private List<ImageView> yuanlist = new ArrayList<ImageView>();
    private int[] imagesid;
    private int num = 0;

    private GuidePageAdapter adapter = null;

    private ViewGroup viewGroup = null;

    public Handler myHandler = null;

    private Timer timer = null;


    private InputStreamReader is = null;


    public Handler handler = null;
    private Button messageButton = null;
    private Button searchButton = null;

    private Button registerbButton = null;
    private Button loginButton = null;
    private FrameLayout decr = null;
    private ImageView rect1 = null;
    private LinearLayout showliLinearLayout = null;
    private ImageView[] rects = null;
    MySwipeRefreshLayout swipeRefreshLayout = null;

    int a;
    public SharedPreferences share;
    private SharedPreferences.Editor editor;

    FrameLayout logresParent;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootvView0 = inflater.inflate(R.layout.tab0, container,
                false);

        logresParent = (FrameLayout) rootvView0.findViewById(R.id.log_res_parent);
        share = getActivity().getSharedPreferences("_User", Context.MODE_PRIVATE);

        decr = (FrameLayout) rootvView0.findViewById(R.id.description_layout);
        showliLinearLayout = (LinearLayout) rootvView0
                .findViewById(R.id.showline);
        swipeRefreshLayout = (MySwipeRefreshLayout) rootvView0.findViewById(R.id.swiprefesh);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);

        swipeRefreshLayout.setOnRefreshListener(new MySwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (getActivity() != null) {
                            SuperToast.create(getActivity(), "刷新完成", SuperToast.Duration.MEDIUM,
                                    Style.getStyle(Style.BLUE, SuperToast.Animations.FLYIN)).show();
                            swipeRefreshLayout.setRefreshing(false);
                        }


                    }
                }, 5000);


            }
        });
        swipeRefreshLayout.setBottomColor(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setOnLoadListener(new MySwipeRefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (getActivity() != null) {
                            SuperToast.create(getActivity(), "加载完成", SuperToast.Duration.MEDIUM,
                                    Style.getStyle(Style.BLUE, SuperToast.Animations.FLYIN)).show();
                            swipeRefreshLayout.setLoading(false);

                        }
                    }
                }, 5000);

            }
        });

        imagetag1 = (ImageView) rootvView0.findViewById(R.id.imgtag1);
        ImageView rect1 = (ImageView) rootvView0.findViewById(R.id.rect1);
        ImageView rect2 = (ImageView) rootvView0.findViewById(R.id.rect2);
        ImageView rect3 = (ImageView) rootvView0.findViewById(R.id.rect3);
        ImageView rect4 = (ImageView) rootvView0.findViewById(R.id.rect4);
        ImageView rect5 = (ImageView) rootvView0.findViewById(R.id.rect5);
        ImageView rect6 = (ImageView) rootvView0.findViewById(R.id.rect6);
        ImageView rect7 = (ImageView) rootvView0.findViewById(R.id.rect7);
        rects = new ImageView[]{rect1, rect2, rect3, rect4, rect5, rect6, rect7};
        bar = (ProgressBar) rootvView0.findViewById(R.id.process_bar);
        bar.setVisibility(View.GONE);
        viewPager = (ViewPager) rootvView0.findViewById(R.id.Viewpagershow);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        swipeRefreshLayout.setEnabled(false);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        swipeRefreshLayout.setEnabled(true);
                        break;
                }
                return false;
            }
        });
        tag1 = (ImageView) rootvView0.findViewById(R.id.tag1);
        messageButton = (Button) rootvView0.findViewById(R.id.Button_message);
        searchButton = (Button) rootvView0.findViewById(R.id.button_search);
        loginButton = (Button) rootvView0.findViewById(R.id.login_tab0);
        loginButton.setOnClickListener(new loginLIstener());
        registerbButton = (Button) rootvView0.findViewById(R.id.register_tab0);
        registerbButton.setOnClickListener(new loginLIstener());
        searchButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
        messageButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(getActivity(), MessageActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
            }
        });
        tag1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(getActivity(), LogIn.class);
                startActivity(intent);
            }
        });

//        ImageRequest imageRequest = new ImageRequest(
//                "http://pic6.nipic.com/20100427/2988027_151327852687_2.jpg",
//                new Response.Listener<Bitmap>() {
//                    @Override
//                    public void onResponse(Bitmap arg0) {
//                        // TODO Auto-generated method stub
//                        tag1.setImageBitmap(arg0);
//                    }
//                }, 0, 0, Config.ARGB_8888, new ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError arg0) {
//                // TODO Auto-generated method stub
//                tag1.setBackgroundResource(R.drawable.tag1);
//            }
//        });
//        MainActivity.requestQueue.add(imageRequest);
        String[] urls = new String[]{

        };
        for (int i = 0; i < urls.length; i++) {


            ImageView haibaoImageView = new ImageView(getActivity());
            Glide.with(getActivity()).load(urls[i]).into(haibaoImageView);
            haibaoImageView.setLayoutParams(new LinearLayout.LayoutParams(1500,
                    1000));
            showliLinearLayout.addView(haibaoImageView);

        }
        String[] recturls = new String[]{
                "http://i12.tietuku.cn/ae788d27068954e7.png",
                "http://i11.tietuku.cn/006984667ce24fec.png",
                "http://i11.tietuku.cn/d806252808b14061.png",
                "http://i11.tietuku.cn/327f8be2b4b639c9.png",
                "http://i11.tietuku.cn/965a85764b91f58f.png",
                "http://i11.tietuku.cn/9bd106ff3105b764.png"
        };
        for (int i = 0; i < recturls.length; i++) {
            Glide.with(getActivity()).load(recturls[i]).into(rects[i]);
        }
        Glide.with(getActivity()).load("http://i3.piimg.com/a9eb559a4bee4da4.jpg").into(imagetag1);
        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                if (msg.what == 0x123) {

                    // Json json = new Json();
                    // json.jsonutil(jsonString);
                }

            }

        };

        // downloadThread = new DownloadThread(handler,
        // "http://pic6.nipic.com/20100427/2988027_151327852687_2.jpg");
        //
        // new Thread(downloadThread).start();

        list.clear();
        if (list.size() < 4) {
            ImageView imageView0 = new ImageView(rootvView0.getContext());
//            imageView0.setLayoutParams(fi);
            Glide.with(this).load("http://i12.tietuku.cn/b6db401cce66841e.jpg").centerCrop().into(imageView0);
            ImageView imageView1 = new ImageView(rootvView0.getContext());
            Glide.with(getActivity()).load("http://i12.tietuku.cn/74d28c89f2857852.jpg").centerCrop().
                    into(imageView1);

            ImageView imageView2 = new ImageView(rootvView0.getContext());
            Glide.with(getActivity()).load("http://i12.tietuku.cn/029168290e434623.jpg").centerCrop().
                    into(imageView2);

            ImageView imageView3 = new ImageView(rootvView0.getContext());
            Glide.with(getActivity()).load("http://i12.tietuku.cn/0ceed8e2f8f0c87a.jpg").
                    centerCrop().into(imageView3);

            list.add(imageView0);
            list.add(imageView1);
            list.add(imageView2);
            list.add(imageView3);
        }

        // DisplayMetrics dm=getResources().getDisplayMetrics();

        viewGroup = (ViewGroup) rootvView0
                .findViewById(R.id.description_layout);
        yuanlist.clear();

        for (int i = 0; i < list.size(); i++) {
            FrameLayout.LayoutParams margin = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT);
            // ����ÿ��СԲ�������ߵļ��
            DisplayMetrics dm = getResources().getDisplayMetrics();

            int width = dm.widthPixels;

            margin.setMargins((width / 2) - list.size() * 50 / 2 + 50 * i - 40,
                    10, 20, 10);

            ImageView yuanimgview = new ImageView(rootvView0.getContext());
            // ����ÿ��СԲ��Ŀ��
            yuanimgview.setLayoutParams(new LayoutParams(500, 500));
            // yuanimageViews = new ImageView[list.size()];

            yuanlist.add(yuanimgview);
            if (i == 0) {
                // Ĭ��ѡ�е�һ��ͼƬ
                yuanlist.get(i).setBackgroundResource(R.drawable.yuan_white);
            } else {
                // ����ͼƬ������δѡ��״̬
                yuanlist.get(i).setBackgroundResource(R.drawable.yuan_white);
                yuanlist.get(i).setAlpha(0.3F);
            }

            viewGroup.addView(yuanlist.get(i), margin);

        }

        viewPager.setOnPageChangeListener(this);

        adapter = new GuidePageAdapter();
        viewPager.setAdapter(adapter);

        if (myHandler == null) {
            myHandler = new Handler() {

                public void handleMessage(Message msg) {
                    if (viewPager != null && tag1 != null) {

                        if (num == 3) {
                            viewPager.setCurrentItem(num);
                            num = 0;
                        } else {

                            viewPager.setCurrentItem(num);
                            num++;
                        }

                    }

                }

                ;

            };
        }
        mytask task = new mytask();
        if (timer == null) {
            timer = new Timer();
            timer.schedule(task, 0, 2000);
        }

        return rootvView0;

    }

    public class loginLIstener implements OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent intent = new Intent();
            intent.setClass(getActivity(), LogIn.class);
            if (v.getId() == R.id.login_tab0) {
                intent.putExtra("page", 0);
            } else {
                intent.putExtra("page", 1);
            }

            startActivity(intent);
        }

    }

    class mytask extends TimerTask {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            if (myHandler != null) {
                myHandler.sendEmptyMessageDelayed(0, 2000);
            }

        }

    }

    class GuidePageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 10000;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getItemPosition(Object object) {
            // TODO Auto-generated method stub
            return super.getItemPosition(object);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            // TODO Auto-generated method stub
            // ((ViewPager) arg0).addView(list.get(arg1));
            // return list.get(arg1);
            arg1 %= list.size();
            if (arg1 < 0) {
                arg1 = list.size() + arg1;
            }
            ImageView view = list.get(arg1);

            ViewParent vp = view.getParent();
            if (vp != null) {
                ViewGroup parent = (ViewGroup) vp;
                parent.removeView(view);
            }
            viewPager.addView(view);
            // add listeners here if necessary
            return view;
        }

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageSelected(int arg0) {
        // TODO Auto-generated method stub
        showText();
        num = arg0;
        for (int i = 0; i < list.size(); i++) {

            yuanlist.get(i).setBackgroundResource(R.drawable.yuan_white);
            yuanlist.get(i).setAlpha(1F);

            if (arg0 % list.size() != i) {
                yuanlist.get(i).setBackgroundResource(R.drawable.yuan_white);
                yuanlist.get(i).setAlpha(0.3F);
            }
        }

        // // yuanimgview.setBackgroundResource(R.drawable.yuan_white);
        // yuanlist.get(3).setBackgroundResource(R.drawable.yuan);
        // yuanlist.get(3).setAlpha(1);
        // // this.();

    }

    @Override
    public void onResume() {
        Log.e("dd", "-----------------tab0resume");

//        if (Constants.IS_LOGIN == true) {
        if (share.getBoolean("IS_LOGIN", true) == true) {
            logresParent.setVisibility(View.GONE);
        } else

        {
            logresParent.setVisibility(View.VISIBLE);
        }

        super.onResume();

    }

    @Override
    public void onDestroyView() {
        // TODO Auto-generated method stub

        super.onDestroyView();

        viewPager.removeAllViews();
        viewPager = null;
        list.clear();

        myHandler = null;
        System.gc();
        super.onDestroyView();

    }

    public void showText() {
        animation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.viewpager_translate);
        animation.setFillAfter(true);
        decr.startAnimation(animation);

    }

    @Override
    public void onDestroy() {
        list.clear();
        rects = null;
        list = null;
        yuanlist.clear();
        yuanlist = null;
        timer.cancel();
        super.onDestroy();
//        animation.cancel();
    }
}
