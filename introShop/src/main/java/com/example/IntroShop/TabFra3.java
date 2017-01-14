package com.example.IntroShop;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;

import android.os.Bundle;

import android.util.Log;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;


import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dd.CircularProgressButton;

import com.example.IntroShop.model._User;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;


public class TabFra3 extends Fragment implements OnClickListener {


    private ViewPager viewPager;
    private List<ImageView> list = new ArrayList<ImageView>();
    ;
    private int[] imagesid;
    private int num = 300;
    private Button buttonsetting = null;
    private ImageView imageView1, imageView2, imageView3;
    private ImageView idBackImageView = null;
    public TextView logintTextView = null;

    private Button rate;
    public CircleImageView avatar;
    public Animation animation = null;
    public Button exitButton;
    public SharedPreferences share;
    private SharedPreferences.Editor editor;
    public Button myCollectionbut;
    public ArrayList<String> mSelectPath;
    public BmobFile bmobFile;
    RadioButton radio0;
    RadioButton radio1;
    RadioButton radio2;
    RadioButton radio3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootvView3 = inflater.inflate(R.layout.tab3, container, false);
        logintTextView = (TextView) rootvView3.findViewById(R.id.logintext);
        buttonsetting = (Button) rootvView3.findViewById(R.id.Button_Setting);
        idBackImageView = (ImageView) rootvView3.findViewById(R.id.id_back);
        radio0 = (RadioButton) rootvView3.findViewById(R.id.radio0);
        radio1 = (RadioButton) rootvView3.findViewById(R.id.radio1);
        radio2 = (RadioButton) rootvView3.findViewById(R.id.radio2);
        radio3 = (RadioButton) rootvView3.findViewById(R.id.radio3);
        radio0.setOnClickListener(this);
        radio1.setOnClickListener(this);
        radio2.setOnClickListener(this);
        radio3.setOnClickListener(this);
        share = getActivity().getSharedPreferences("_User", Context.MODE_PRIVATE);
        editor = share.edit();
        avatar = (CircleImageView) rootvView3.findViewById(R.id.avater);
        if (share.getBoolean("IS_LOGIN", true)) {
            Glide.with(getActivity()).load(((MainActivity) getActivity()).user.getAvator()).into(avatar);
            logintTextView.setText(share.getString("username", null));
        }


        animation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.idback_anim);
        animation.setFillAfter(true);
        idBackImageView.startAnimation(animation);


        logintTextView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(getActivity(), LogIn.class);
                startActivity(intent);
            }
        });
        avatar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                if (share.getBoolean("IS_LOGIN", true)) {

                    intent.setClass(getActivity(), UserSocialInfoActivity.class);

                    intent.putExtra("username", share.getString("username", null));
                } else {
                    intent.setClass(getActivity(), LogIn.class);

                }
                startActivityForResult(intent, 5);
            }
        });

        buttonsetting.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(getActivity(), Activity_setting.class);
                startActivity(intent);
            }
        });
//
        if (share.getBoolean("IS_LOGIN", true)) {
            BmobQuery<_User> query = new BmobQuery<_User>();
            query.addWhereEqualTo("username", share.getString("username", null));
            query.findObjects(getActivity(), new FindListener<_User>() {
                @Override
                public void onSuccess(List<_User> object) {
                    // TODO Auto-generated method stub
                    Toast.makeText(getActivity(), "查询数据库成功！---", Toast.LENGTH_LONG).show();
                    for (_User user : object) {
                        if (user.getUAvatar() != null) {
                            Glide.with(getActivity()).load(user.getUAvatar().getFileUrl(getActivity())).into(avatar);
                        }
                        logintTextView.setText(share.getString("username", null));

                    }
                }

                @Override
                public void onError(int code, String msg) {
                    // TODO Auto-generated method stub
                    Toast.makeText(getActivity(), "查询数据库失败！---" + msg, Toast.LENGTH_LONG).show();
                }
            });
        }
        tab3ChildFragement0 tabchild0 = new tab3ChildFragement0();
        android.support.v4.app.FragmentManager fm = getChildFragmentManager();
        android.support.v4.app.FragmentTransaction trans = fm.beginTransaction();
        trans.replace(R.id.contain, tabchild0);
        trans.commit();


        return rootvView3;

    }

    private void simulateSuccessProgress(final CircularProgressButton button) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
        widthAnimation.setDuration(1500);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value);
            }
        });
        widthAnimation.start();
    }

    @Override
    public void onDestroy() {
        android.util.Log.e("da", "tab3destroy");
        animation.cancel();
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == -1) {
                mSelectPath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                StringBuilder sb = new StringBuilder();
                for (String p : mSelectPath) {
                    sb.append(p);
                }
                Log.e("main", "-----" + sb.toString());
                bmobFile = new BmobFile(new File(sb.toString()));
                bmobFile.uploadblock(getActivity(), new UploadFileListener() {
                    @Override
                    public void onSuccess() {
                        // TODO Auto-generated method stub

                        Toast.makeText(getActivity(), "上传 成功！", Toast.LENGTH_LONG).show();

                        share = getActivity().getSharedPreferences("_User", Context.MODE_PRIVATE);
                        editor = share.edit();
                        ((MainActivity) getActivity()).user.setUsername(share.getString("username", null));
                        ((MainActivity) getActivity()).user.setPassword(share.getString("password", null));
//                        while (bmobFile.getFileUrl(getActivity()) != null) {
//                ((MainActivity) getActivity()).user.setAvatar(bmobFile.getFileUrl(getActivity()));
                        ((MainActivity) getActivity()).user.setUAvatar(bmobFile);
//                        }
                        ((MainActivity) getActivity()).user.login(getActivity(), new SaveListener() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(getActivity(), "登陆成功！ " + ((MainActivity) getActivity()).user.getUsername(), Toast.LENGTH_SHORT).show();
                                editor.putBoolean("IS_LOGIN", true);
                                editor.commit();
                                ((MainActivity) getActivity()).user.update(getActivity(),
                                        ((MainActivity) getActivity()).user.getObjectId(), new UpdateListener() {
                                            @Override
                                            public void onSuccess() {
                                                Toast.makeText(getActivity(), "更新 成功！tab3", Toast.LENGTH_LONG).show();
                                                Glide.with(getActivity()).load(((MainActivity) getActivity()).user.getUAvatar().getFileUrl(getActivity())).into(avatar);
                                                logintTextView.setText(share.getString("username", null));

                                            }

                                            @Override
                                            public void onFailure(int i, String s) {
                                                Toast.makeText(getActivity(), "更新 失败 ！tab3--" + s, Toast.LENGTH_LONG).show();
                                                Log.e("main", "-----" + s);
                                            }
                                        });

                            }

                            @Override
                            public void onFailure(int i, String s) {

                                Toast.makeText(getActivity(), "登陆失败---" + s, Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onProgress(Integer value) {
                        // TODO Auto-generated method stub
                        // 返回的上传进度（百分比）
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        // TODO Auto-generated method stub
                        Toast.makeText(getActivity(), "上传失败！---" + msg, Toast.LENGTH_LONG).show();
                    }
                });

            }

        }
        if (requestCode == 5) {
            if (resultCode == 5) {
                ((MainActivity) getActivity()).user.setUsername(share.getString("username", null));
                ((MainActivity) getActivity()).user.setPassword(share.getString("password", null));
                ((MainActivity) getActivity()).user.login(getActivity(), new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getActivity(), "登陆成功！ " + ((MainActivity)
                                getActivity()).user.getUsername(), Toast.LENGTH_SHORT).show();
                        BmobQuery<_User> query = new BmobQuery<_User>();
                        query.addWhereEqualTo("username", share.getString("username", null));
                        query.findObjects(getActivity(), new FindListener<_User>() {
                            @Override
                            public void onSuccess(List<_User> object) {
                                // TODO Auto-generated method stub
                                Toast.makeText(getActivity(), "查询数据库成功！---", Toast.LENGTH_LONG).show();
                                for (_User user : object) {
                                    if (user.getUAvatar() != null) {
                                        Glide.with(getActivity()).load(user.getUAvatar().getFileUrl(getActivity())).into(avatar);

                                    }
                                    logintTextView.setText(share.getString("username", null));
                                }
                            }

                            @Override
                            public void onError(int code, String msg) {
                                // TODO Auto-generated method stub
                                Toast.makeText(getActivity(), "查询数据库失败！---" + msg, Toast.LENGTH_LONG).show();
                            }
                        });

                    }

                    @Override
                    public void onFailure(int i, String s) {

                        Toast.makeText(getActivity(), "登陆失败---" + s, Toast.LENGTH_LONG).show();
                    }
                });
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.radio0:
                android.support.v4.app.FragmentManager fm0 = getChildFragmentManager();
                android.support.v4.app.FragmentTransaction trans0 = fm0.beginTransaction();
                Log.e("tab3", "----------------go");
                tab3ChildFragement0 tab3child0 = new tab3ChildFragement0();
                trans0.replace(R.id.contain, tab3child0);
                trans0.commit();
                break;
            case R.id.radio1:
                android.support.v4.app.FragmentManager fm1 = getChildFragmentManager();
                android.support.v4.app.FragmentTransaction trans1 = fm1.beginTransaction();
                Log.e("tab3", "----------------go");
                tab3ChildFragement1 tab3child1 = new tab3ChildFragement1();
                trans1.replace(R.id.contain, tab3child1);
                trans1.commit();
                break;
            case R.id.radio2:
                android.support.v4.app.FragmentManager fm2 = getChildFragmentManager();
                android.support.v4.app.FragmentTransaction trans2 = fm2.beginTransaction();
                Log.e("tab3", "----------------go");
                tab3ChildFragement2 tab3child2 = new tab3ChildFragement2();
                trans2.replace(R.id.contain, tab3child2);
                trans2.commit();
                break;
            case R.id.radio3:
                android.support.v4.app.FragmentManager fm3 = getChildFragmentManager();
                android.support.v4.app.FragmentTransaction trans3 = fm3.beginTransaction();
                Log.e("tab3", "----------------go");
                tab3ChildFragement3 tab3child3 = new tab3ChildFragement3();
                trans3.replace(R.id.contain, tab3child3);
                trans3.commit();
                break;

        }
    }
}
