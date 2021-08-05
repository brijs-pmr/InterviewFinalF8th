package com.example.try4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        editTextInput = findViewById(R.id.edit_text_input);

        String input = "Application Running In BackGround";//editTextInput.getText().toString();

        Intent serviceIntent = new Intent(this, ExampleService.class);
        serviceIntent.putExtra("inputExtra", input);

//        startService(serviceIntent);
        ContextCompat.startForegroundService(this, serviceIntent);

        PackageManager packageManager = getPackageManager();
        ComponentName componentName = new ComponentName(MainActivity.this,MainActivity.class);
        packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED
                ,PackageManager.DONT_KILL_APP);

        Toast.makeText(MainActivity.this,"Application Hidden!",Toast.LENGTH_SHORT).show();

    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
//            PackageManager packageManager = getPackageManager();
//            ComponentName componentName = new ComponentName(MainActivity.this, MainActivity.class);
//            packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED
//                    , PackageManager.DONT_KILL_APP);
//            Toast.makeText(MainActivity.this,"Application Unhide!",Toast.LENGTH_SHORT).show();
//            event.startTracking();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }


}