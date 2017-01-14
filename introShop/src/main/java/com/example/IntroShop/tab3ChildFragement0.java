package com.example.IntroShop;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dd.CircularProgressButton;

import java.io.File;
import java.util.ArrayList;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by jamase on 2016-04-09.
 */
public class tab3ChildFragement0 extends Fragment {
    Button exitButton;
    public SharedPreferences share;
    private SharedPreferences.Editor editor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.tab3_child_fragement0, container, false);
        share = getActivity().getSharedPreferences("_User", Context.MODE_PRIVATE);
        editor = share.edit();
        exitButton = (Button) root.findViewById(R.id.exit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("退出登录？")
                        .setCancelText("取消")
                        .setConfirmText("确定")
                        .showCancelButton(true)
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {

                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                Constants.IS_LOGIN = false;
                                editor.putString("username", null);
                                editor.putString("password", null);
                                editor.putBoolean("IS_LOGIN", false);
                                editor.commit();
                                Glide.with(getActivity()).load(R.drawable.register)
                                        .into((ImageView) getParentFragment().getView().findViewById(R.id.avater));
                                ((TabFra3) getParentFragment()).logintTextView.setText("登录/注册");
                                sDialog.cancel();

                            }
                        })
                        .show();
            }
        });
        final CircularProgressButton circularButton1 = (CircularProgressButton) root.findViewById(R.id.btnWithText);
        circularButton1.setIndeterminateProgressMode(true);
        circularButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circularButton1.getProgress() == 0) {
                    circularButton1.setProgress(50);
                } else if (circularButton1.getProgress() == 100) {
                    circularButton1.setProgress(0);
                } else {
                    circularButton1.setProgress(100);
                }
            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == -1) {
                ((TabFra3) getParentFragment()).mSelectPath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                StringBuilder sb = new StringBuilder();
                for (String p : ((TabFra3) getParentFragment()).mSelectPath) {
                    sb.append(p);
                }
                Log.e("main", "-----" + sb.toString());
                ((TabFra3) getParentFragment()).bmobFile = new BmobFile(new File(sb.toString()));
                ((TabFra3) getParentFragment()).bmobFile.uploadblock(getActivity(), new UploadFileListener() {
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
                        ((MainActivity) getActivity()).user.setUAvatar(((TabFra3) getParentFragment()).bmobFile);
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
                                                Glide.with(getActivity()).load(((MainActivity) getActivity()).user.getUAvatar().getFileUrl(getActivity()))
                                                        .into(((TabFra3) getParentFragment()).avatar);
                                                ((TabFra3) getParentFragment()).logintTextView.setText(share.getString("username", null));

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
    }
}
