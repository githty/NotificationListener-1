package com.xiaom.notificationlistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.util.Log;

public class SmsReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean check = MainActivity.listennerSMS.isChecked();
        if (check) {
            Bundle bundle = intent.getExtras();
            StringBuffer sb = new StringBuffer();
            String strFrom = null;
            if (null != bundle) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                for (SmsMessage message : messages) {
                    strFrom = message.getDisplayOriginatingAddress();
                    String strMsg = message.getDisplayMessageBody();
                    sb.append(strMsg);
                }
                sb.append("\r\n---------------------------------------\r\n");
                strFrom = strFrom + "\r\n" + sb.toString();
                //在这里写自己的逻辑
                Log.i("XiaoM_Test", sb.toString());
                FileUtil.writeToSdCardByTxt(MainActivity.FilePath, strFrom);
            }
        }
    }
}

