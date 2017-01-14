package com.example.IntroShop;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.View.OnClickListener;

import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.IntroShop.customView.DiyViewpager;
import com.example.IntroShop.model._User;
import com.squareup.leakcanary.LeakCanary;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends AppCompatActivity implements OnClickListener,
        OnPageChangeListener, DiyViewpager.OnPageChangeListener {
    public DiyViewpager mviewPager = null;
    private List<Fragment> mtabs = new ArrayList<Fragment>();

    private FragmentPagerAdapter mAdapter = null;
    private TabFra0 tabfragement0;
    private TabFra1 tabfragement1;
    private TabFra2 tabfragement2;
    private TabFra3 tabfragement3;
    private ChangeColor tab3menu;
    private int i = 0;
    private List<ChangeColor> mTabIndicators = new ArrayList<ChangeColor>();
    public DrawerLayout mdrawer;
    public SharedPreferences share;
    private SharedPreferences.Editor editor;
    public _User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LeakCanary.install(getApplication());
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Bmob.initialize(getApplication(), "6dd4ae272f1997963617a2138874afd2");
        // 使用推送服务时的初始化操作
        BmobInstallation.getCurrentInstallation(getApplication()).save();
        // 启动推送服务
        BmobPush.startWork(getApplication());
        user = new _User();
        share = this.getSharedPreferences("_User", Context.MODE_PRIVATE);
        editor = share.edit();
        user.setUsername(share.getString("username", null));
        user.setPassword(share.getString("password", null));
//        user.setAvatar("ddawdawdawd");
        user.login(this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this, "登陆成功！ " + user.getUsername(), Toast.LENGTH_SHORT).show();
                editor.putBoolean("IS_LOGIN", true);
                editor.commit();
//                user.update(MainActivity.this, user.getObjectId(), new UpdateListener() {
//                    @Override
//                    public void onSuccess() {
//                        Toast.makeText(MainActivity.this, "baocun 成功！", Toast.LENGTH_LONG).show();
//
//                    }
//
//                    @Override
//                    public void onFailure(int i, String s) {
//                        Toast.makeText(MainActivity.this, "baocun shibai ！--" + s, Toast.LENGTH_LONG).show();
//                        Log.e("main", "-----" + s);
//                    }
//                });
            }

            @Override
            public void onFailure(int i, String s) {
//                editor.putBoolean("IS_LOGIN", false);
//                editor.commit();
                Toast.makeText(MainActivity.this, "登陆失败---" + s, Toast.LENGTH_LONG).show();
            }
        });


//        getActionBar().hide();
//        getActionBar().setDisplayShowHomeEnabled(false);

        // getWindow()
        // .addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // ͸��������
        // getWindow().addFlags(
        // WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        mviewPager = (DiyViewpager) findViewById(R.id.viewpager);
        ChangeColor id1 = (ChangeColor) findViewById(R.id.id1);
        ChangeColor id2 = (ChangeColor) findViewById(R.id.id2);
        ChangeColor id3 = (ChangeColor) findViewById(R.id.id3);
        ChangeColor id4 = (ChangeColor) findViewById(R.id.id4);
        tab3menu = (ChangeColor) findViewById(R.id.tab3menu);
        mTabIndicators.add(id1);
        mTabIndicators.add(id2);
        mTabIndicators.add(id3);
        mTabIndicators.add(id4);

        id1.setOnClickListener(this);
        id2.setOnClickListener(this);
        id3.setOnClickListener(this);
        id4.setOnClickListener(this);

        tabfragement0 = new TabFra0();
        mtabs.add(tabfragement0);
        tabfragement1 = new TabFra1();
        mtabs.add(tabfragement1);
        tabfragement2 = new TabFra2();
        mtabs.add(tabfragement2);
        tabfragement3 = new TabFra3();
        mtabs.add(tabfragement3);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return mtabs.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                // TODO Auto-generated method stub
                return mtabs.get(arg0);
            }

        };


        mviewPager.setAdapter(mAdapter);
        id1.setIconAlpha(1.0f);
        initEvent();
//        mTabIndicators.indexOf(3).seton
    }

    private void initEvent() {
        // TODO Auto-generated method stub
        mviewPager.setOnPageChangeListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        if (id == R.id.action_search) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        resetptoers();
        switch (v.getId()) {
            case R.id.id1:
                mTabIndicators.get(2).setAlpha(1);
                tab3menu.setVisibility(View.INVISIBLE);
                if (mviewPager.getCurrentItem() == 2 && tabfragement2 != null) {
                    YoYo.with(Techniques.DropOut)
                            .duration(600)
                            .playOn(mTabIndicators.get(2));
                }
                mviewPager.setEnableScoll(true);
                mTabIndicators.get(0).setIconAlpha(1.0f);
                mviewPager.setCurrentItem(0, false);


                break;
            case R.id.id2:
                mTabIndicators.get(2).setAlpha(1);

                tab3menu.setVisibility(View.INVISIBLE);
                if (mviewPager.getCurrentItem() == 2 && tabfragement2 != null) {
                    YoYo.with(Techniques.DropOut)
                            .duration(600)
                            .playOn(mTabIndicators.get(2));
                }
                mviewPager.setEnableScoll(true);
                mTabIndicators.get(1).setIconAlpha(1.0f);
                mviewPager.setCurrentItem(1, false);


                break;
            case R.id.id3:
                mTabIndicators.get(2).setAlpha(0);
                tab3menu.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.Tada)
                        .duration(300)
                        .playOn(tab3menu);
                if (tabfragement2.Mydrawer != null) {
                    mdrawer = tabfragement2.Mydrawer;
                }
                Log.e("dada", "--------------id3_pressed");
                if (mviewPager.getCurrentItem() == 2 && mdrawer != null) {
                    mTabIndicators.get(2).setAlpha(0);
                    if (!mdrawer.isDrawerOpen(Gravity.RIGHT)) {
                        mviewPager.setEnableScoll(false);
                        mdrawer.openDrawer(Gravity.RIGHT);

                    } else {
                        mdrawer.closeDrawer(Gravity.RIGHT);

                    }

                } else {
                    YoYo.with(Techniques.FadeOut)
                            .duration(300)
                            .playOn(mTabIndicators.get(2));

                }

//                mTabIndicators.get(2).setIconAlpha(1.0f);
                mviewPager.setCurrentItem(2, false);

                break;
            case R.id.id4:
                mTabIndicators.get(2).setAlpha(1);
                tab3menu.setVisibility(View.INVISIBLE);
                if (mviewPager.getCurrentItem() == 2) {
                    YoYo.with(Techniques.DropOut)
                            .duration(600)
                            .playOn(mTabIndicators.get(2));
                }
                mviewPager.setEnableScoll(true);
                mTabIndicators.get(3).setIconAlpha(1.0f);
                mviewPager.setCurrentItem(3, false);


                break;
        }
        Log.e("dawd", "------------当前" + mviewPager.getCurrentItem());
    }

    private void resetptoers() {
        for (int i = 0; i < mTabIndicators.size(); i++) {
            mTabIndicators.get(i).setIconAlpha(0);
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {
        // TODO Auto-generated method stub
        if (positionOffset > 0) {
            ChangeColor left = mTabIndicators.get(position);
            ChangeColor right = mTabIndicators.get(position + 1);
            left.setIconAlpha(1 - positionOffset);
            right.setIconAlpha(positionOffset);
        }

    }

    @Override
    public void onPageSelected(int arg0) {
        if (tabfragement2.Mydrawer != null)
            mdrawer = tabfragement2.Mydrawer;
        // TODO Auto-generated method stub
        if (arg0 != 2 && mdrawer != null) {
            mdrawer.closeDrawer(Gravity.RIGHT);
        }

    }


    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        super.addContentView(view, params);
    }


    @Override
    protected void onDestroy() {
        mtabs.clear();
        super.onDestroy();
        for (ChangeColor changeColor : mTabIndicators) {
            changeColor.onDestroy();
        }
    }

    @Override
    protected void onRestart() {

        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    protected void onPause() {


        super.onPause();
    }

    public String getMD5(String info) {
        if (info == null) {
            return null;
        }
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));
            byte[] encryption = md5.digest();

            StringBuffer strBuf = new StringBuffer();
            for (int i = 0; i < encryption.length; i++) {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
                    strBuf.append("0").append(
                            Integer.toHexString(0xff & encryption[i]));
                } else {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }

            return strBuf.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
