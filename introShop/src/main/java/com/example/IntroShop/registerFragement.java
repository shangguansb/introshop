package com.example.IntroShop;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.IntroShop.model._User;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class registerFragement extends Fragment {
    private EditText userEditText;
    private EditText passwordEditText;
    private Button registerButton = null;
    private String md5password = null;
    public SharedPreferences share;
    SharedPreferences.Editor editor;
//	private HttpClient httpClient = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootvView2 = inflater.inflate(R.layout.registerfragement,
                container, false);
//        Bmob.initialize(getActivity(), "6dd4ae272f1997963617a2138874afd2");
//        // 使用推送服务时的初始化操作
//        BmobInstallation.getCurrentInstallation(getActivity()).save();
//        // 启动推送服务
//        BmobPush.startWork(getActivity());
//		httpClient = new DefaultHttpClient();
        userEditText = (EditText) rootvView2.findViewById(R.id.register_edittext);
        passwordEditText = (EditText) rootvView2
                .findViewById(R.id.register_edittext_password);
        registerButton = (Button) rootvView2.findViewById(R.id.register_button);
        final _User user = new _User();
        share = getActivity().getSharedPreferences("_User", Context.MODE_PRIVATE);
        editor = share.edit();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userEditText.setEnabled(false);
                passwordEditText.setEnabled(false);
                user.setUsername(userEditText.getText().toString());
                user.setPassword(getMD5(passwordEditText.getText().toString()));
                user.signUp(getActivity(), new SaveListener() {
                    @Override
                    public void onSuccess() {

                        Toast.makeText(getActivity(), "欢迎加入！  " + userEditText.getText().toString(), Toast.LENGTH_SHORT).show();
                        userEditText.setEnabled(true);
                        passwordEditText.setEnabled(true);
                        editor.putString("username", userEditText.getText().toString());
                        editor.putString("password", getMD5(passwordEditText.getText().toString()));
                        editor.putBoolean("IS_LOGIN", true);
                        editor.commit();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra("success", 0);
                        getActivity().setResult(5, intent);
//                        startActivity(intent);
                        getActivity().finish();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        editor.putString("username", null);
                        editor.putString("password", null);
                        editor.putBoolean("IS_LOGIN", false);
                        Toast.makeText(getActivity(), "注册失败，请检查\n" + s, Toast.LENGTH_SHORT).show();
                        userEditText.setEnabled(true);
                        passwordEditText.setEnabled(true);

                    }
                });
//                attemptLogin();
            }
        });
//		registerButton.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//
//				md5password = getMD5(passwordEditText.getText().toString());
//				new Thread() {
//
//					@Override
//					public void run() {
//						// TODO Auto-generated method stub
//						try {
//							HttpPost post = new HttpPost(
//									"http://192.168.191.3:8080/DSDG/userRegist");
//							List<NameValuePair> parmas = new ArrayList<NameValuePair>();
//							parmas.add(new BasicNameValuePair("username",
//									userEditText.getText().toString()));
//							parmas.add(new BasicNameValuePair("password",
//									md5password));
//
//							post.setEntity(new UrlEncodedFormEntity(parmas,
//									HTTP.UTF_8));
//							HttpResponse response = httpClient.execute(post);
//						} catch (UnsupportedEncodingException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (ClientProtocolException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}

//					}
//
//				}.start();
//			}
//		});
        return rootvView2;
    }

    public String getMD5(String info) {
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

    @Override
    public void onDestroy() {

        userEditText = null;
        passwordEditText = null;
        super.onDestroy();
    }
}
