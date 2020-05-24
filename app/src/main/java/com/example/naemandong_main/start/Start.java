package com.example.naemandong_main.start;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.naemandong_main.MainActivity;
import com.example.naemandong_main.R;

public class Start extends AppCompatActivity {

    ImageButton slogin;
    public static Activity activity;
    final int PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        activity = Start.this;

        slogin = (ImageButton) findViewById(R.id.slogin);

        if ( Build.VERSION.SDK_INT >= 23 ){
            // 퍼미션 체크
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET,Manifest.permission.RECORD_AUDIO},PERMISSION);
        }

        slogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Start.this, MainActivity.class));
 //               startActivity(new Intent(Start.this, Login.class));
            }
        });
    }
}
