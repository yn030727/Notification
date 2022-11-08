package com.example.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //对通知进行管理
        val manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //构建一个通知渠道
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            //创建一个通知渠道至少需要渠道ID，渠道名称以及重要等级这3个参数
            //渠道ID：保证其全局唯一性
            //渠道名称：是给用户看的，清楚表达这个渠道的用途
            //重要等级：通知的重要等级，会决定通知的不同行为
            val chnnel=NotificationChannel("1","重要信息",NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(chnnel)
        }
        //通知一般创建在Service，BroadcastReceiver里，因为一般只有当程序进入后台的时候才需要使用通知
        ////因为API不稳定的问题在通知上很明显，所以通知需要使用AndroidX库中提供的NotificationCompat类
        val notification = NotificationCompat.Builder(this,"1")
                //它的构造函数传入的渠道ID也必须叫做1
                //如果传入一个不存在的渠道ID,通知是无法显示出来的
            .setContentTitle("This is content title")
            .setContentText("this is content text")
            .setSmallIcon(androidx.appcompat.R.drawable.notification_icon_background)
            .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.ic_launcher_background))
            .build()
        manager.notify(1,notification)
    }
}