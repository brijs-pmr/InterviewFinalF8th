package com.example.try4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

public class AfterNotificationOpen extends AppCompatActivity {

    static int Count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        PackageManager packageManager = getPackageManager();
        ComponentName componentName = new ComponentName(AfterNotificationOpen.this,AfterNotificationOpen.class);
        packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                ,PackageManager.DONT_KILL_APP);

        Toast.makeText(AfterNotificationOpen.this,"Application Unhide!",Toast.LENGTH_LONG).show();

        Intent serviceIntent = new Intent(this, ExampleService.class);
        stopService(serviceIntent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            Count++;
            if(Count == 3){
                String input = "Application Running In BackGround";

                Intent serviceIntent = new Intent(this, ExampleService.class);
                serviceIntent.putExtra("inputExtra", input);
                ContextCompat.startForegroundService(this, serviceIntent);

                PackageManager packageManager = getPackageManager();
                ComponentName componentName = new ComponentName(AfterNotificationOpen.this,AfterNotificationOpen.class);
                packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED
                        ,PackageManager.DONT_KILL_APP);

                Toast.makeText(AfterNotificationOpen.this,"Application Hidden!",Toast.LENGTH_LONG).show();
                Count = 0;
                return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        return super.onKeyMultiple(keyCode, repeatCount, event);
    }
}