package com.example.IntroShop;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.IntroShop.model._User;

import cn.bmob.v3.listener.SaveListener;

public class LoginFragement extends Fragment {
    private EditText loginEditText = null;
    private EditText logpasswordEditText = null;
    private Button loginButton = null;
    //	private HttpClient httpClient = null;
    private String md5password = null;
    private String jsonString = null;
    private Handler handler = null;
    public SharedPreferences share;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootvView2 = inflater.inflate(R.layout.loginfragement, container,
                false);
//        Bmob.initialize(getActivity(), "6dd4ae272f1997963617a2138874afd2");
//        // 使用推送服务时的初始化操作
//        BmobInstallation.getCurrentInstallation(getActivity()).save();
//        // 启动推送服务
//        BmobPush.startWork(getActivity());

        share = getActivity().getSharedPreferences("_User", Context.MODE_PRIVATE);
        editor = share.edit();
        loginEditText = (EditText) rootvView2.findViewById(R.id.login_edittext);
        logpasswordEditText = (EditText) rootvView2.findViewById(R.id.logpassword_edittext);
        loginButton = (Button) rootvView2.findViewById(R.id.log_in_button);
        final _User user = new _User();

        loginEditText = (EditText) rootvView2.findViewById(R.id.login_edittext);
        logpasswordEditText = (EditText) rootvView2
                .findViewById(R.id.logpassword_edittext);

        loginButton.setTextColor(getResources().getColor(R.color.white));
        loginButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                loginEditText.setEnabled(false);
                logpasswordEditText.setEnabled(false);
//                if (loginEditText.getText().toString().equals("") || logpasswordEditText.getText().toString().equals("")) {
                user.setUsername(loginEditText.getText().toString());
                user.setPassword(getMD5(logpasswordEditText.getText().toString()));
                user.login(getActivity(), new SaveListener() {
                    @Override
                    public void onSuccess() {

                        editor.putString("username", loginEditText.getText().toString());
                        editor.putString("password", getMD5(logpasswordEditText.getText().toString()));
                        editor.putBoolean("IS_LOGIN", true);
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra("success", 0);
                        getActivity().setResult(5, intent);
                        loginEditText.setEnabled(true);
                        logpasswordEditText.setEnabled(true);
                        editor.commit();
                        Toast.makeText(getActivity(), "欢迎回来！   " + loginEditText.getText().toString(), Toast.LENGTH_SHORT).show();
                        getActivity().finish();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        editor.putString("username", null);
                        editor.putString("password", null);
                        editor.putBoolean("IS_LOGIN", false);
                        editor.commit();
                        loginEditText.setEnabled(true);
                        logpasswordEditText.setEnabled(true);
                        Toast.makeText(getActivity(), "登录失败，请检查\n" + s, Toast.LENGTH_SHORT).show();
                    }
                });
//                    Log.e("dwad", "-----------" + loginEditText.getText().toString());
//                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
//                            .setTitleText("请输入用户名或密码")
//                            .setContentText("用户名或密码不能为空")
//                            .setConfirmText("确定")
//                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                @Override
//                                public void onClick(SweetAlertDialog sDialog) {
//                                    sDialog.dismissWithAnimation();
//                                }
//                            })
//                            .show();
//                }
//				// TODO Auto-generated method stub
//				md5password = getMD5(logpasswordEditText.getText().toString());
//				new Thread() {
//
//					@Override
//					public void run() {
//						// TODO Auto-generated method stub
//						try {
//							HttpPost post = new HttpPost(
//									"http://192.168.191.3:8080/DSDG/userLogin");
//							List<NameValuePair> parmas = new ArrayList<NameValuePair>();
//							parmas.add(new BasicNameValuePair("username",
//									loginEditText.getText().toString()));
//							parmas.add(new BasicNameValuePair("password",
//									md5password));
//
//							post.setEntity(new UrlEncodedFormEntity(parmas,
//									HTTP.UTF_8));
//							HttpResponse response = httpClient.execute(post);
//
//
//							JSONObject jsonObject = new JSONObject(EntityUtils
//									.toString(response.getEntity()));
//							jsonString = jsonObject.getString("mesg");
//							handler.sendEmptyMessage(0x123);
                // if (jsonObject.getString("status") == "200") {
                // loginButton.setText("success!");
                // }
//						} catch (UnsupportedEncodingException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (ClientProtocolException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (ParseException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (JSONException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}

//				}.start();
            }
        });

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
        loginEditText = null;
        logpasswordEditText = null;
        super.onDestroy();
    }
}
