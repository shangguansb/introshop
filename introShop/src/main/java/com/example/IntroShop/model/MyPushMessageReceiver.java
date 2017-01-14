package com.example.IntroShop.model;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.IntroShop.MainActivity;
import com.example.IntroShop.R;

import cn.bmob.push.PushConstants;


/**
 * Created by jamase on 2016-04-11.
 */
public class MyPushMessageReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //定义notification
        Notification notification = new Notification.Builder(context)
                .setAutoCancel(true)
                .setTicker("新消息")
                .setSmallIcon(R.drawable.a4u)
                .setContentTitle(intent.getStringExtra("msg"))
                .build();
        nm.notify(1, notification);
        //通知消息与Intent关联
//        Intent it=new Intent(context,Second.class);
//        it.putExtra("name", "name:"+1);
//        PendingIntent pi=PendingIntent.getActivity(MainActivity.this, 100, it, PendingIntent.FLAG_CANCEL_CURRENT);
        //具体的通知内容

        if (intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {
            Log.e("bmob", "客户端收到推送内容：" + intent.getStringExtra("msg"));
        }
    }
}