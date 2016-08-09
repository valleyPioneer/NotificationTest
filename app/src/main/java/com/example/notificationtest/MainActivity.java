package com.example.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.send_notice);
        button.setOnClickListener(this);
    }

    public void onClick(View v){

        switch (v.getId()){
            case R.id.send_notice:

                NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
               /* Notification notification = new Notification(R.mipmap.ic_launcher,"This is ticker text",System.currentTimeMillis());
                notification.setLatestEventInfo(this,"This is content title","this is content text",null);*/
               //此方法已经被高版本api取缔，建议使用notification.bulider(context)方法进行通知的设置

                Intent intent = new Intent(this,NotificationActivity.class);
                PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                Notification notification = new Notification.Builder(this)
                                            .setAutoCancel(true)
                                            .setContentTitle("This is content title")
                                            .setContentText("This is content text")
                                            .setContentIntent(pi)
                                            .setSmallIcon(R.mipmap.ic_launcher)
                                            .setWhen(System.currentTimeMillis())
                                            .setTicker("it is just a little test!")
                                            .setDefaults(Notification.DEFAULT_ALL)//此设置可以直接将振动、LED灯、铃声设为默认
                                            .build();
                //为什么不能运行，已弃疗
                //我竟然没有注册监听器。。。。点击后当然没有反应
                //MIUI系统通知的ticker不显示，而且点击通知事件后，通知自动消失
                manager.notify(0, notification);

                break;
            default:
        }
    }
}
