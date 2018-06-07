package com.xiaom.notificationlistener;

import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    public static String FilePath;
    public static EditText editText;
    public static CompoundButton listennerSMS;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FilePath = this.getExternalCacheDir() +"/Notification.txt" ;
        editText = findViewById(R.id.filtration);
        listennerSMS = findViewById(R.id.listennerSMS);
        textView = findViewById(R.id.textView);
        //通知栏监控器开关
        CompoundButton notificationMonitorOnBtn = findViewById(R.id.Test);
        notificationMonitorOnBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!isEnabled()) {
                        startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "监控器开关已打开,无需重复开启", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    textView.setVisibility(View.VISIBLE);
                    editText.setVisibility(View.VISIBLE);
                    listennerSMS.setTranslationY(100);
                } else {
                    if (isEnabled()) {
                        startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "监控器开关已关闭，无需重复关闭", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    textView.setVisibility(View.INVISIBLE);
                    editText.setVisibility(View.INVISIBLE);
                    listennerSMS.setTranslationY(0);
                }
            }
        });
    }

    // 判断是否打开了通知监听权限
    private boolean isEnabled() {
        if (NotificationManagerCompat.getEnabledListenerPackages(this).contains(getPackageName())) {
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
//        super.onBackPressed(); //注释super,拦截返回键功能
    }
}

