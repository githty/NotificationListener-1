package com.xiaom.notificationlistener;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

@SuppressLint("NewApi")
public class NotificationMonitorService extends NotificationListenerService {

    // 在收到消息时触发
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        String text = MainActivity.editText.getText().toString();
        if(!text.isEmpty()){
            Notification notification = sbn.getNotification();
            if (notification != null) {
                long time = System.currentTimeMillis();
                Bundle extras = notification.extras;
                // 获取接收消息APP的包名
                //String notificationPkg = sbn.getPackageName();
                // 获取接收消息的抬头
                String notificationTitle = extras.getString(Notification.EXTRA_TITLE);
                // 获取接收消息的内容
                String notificationText = extras.getString(Notification.EXTRA_TEXT);
                //以下写自己的逻辑
                if (notificationTitle.trim().contains(text) || notificationText.trim().contains(text)) {
                    StringBuffer sb = new StringBuffer();
                    sb.append("接收时间：" + time + "\r\n");
                    sb.append("消息抬头：" + notificationTitle + "\r\n");
                    sb.append("消息内容：" + notificationText + "\r\n");
                    sb.append("---------------------------------------\r\n");
                    Log.i("XiaoM_Test", sb.toString());
                    FileUtil.writeToSdCardByTxt(MainActivity.FilePath , sb.toString());
                }
                // 移除通知
            /*final PendingIntent pendingIntent = notification.fullScreenIntent;
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }*/
            }
        }

    }

    // 在删除消息时触发
    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {

    }
}

