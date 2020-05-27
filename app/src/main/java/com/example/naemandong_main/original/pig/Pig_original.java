package com.example.naemandong_main.original.pig;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.naemandong_main.R;
import com.example.naemandong_main.Setting;
import com.example.naemandong_main.original.rabbit.ori_rabbit01;

public class Pig_original extends AppCompatActivity {

    Button setting;
    public boolean sound;
    public boolean subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rabbit01);

        setting = (Button)findViewById(R.id.btn_setting);

        Bundle bundle = new Bundle();
        bundle.putBoolean("sound", sound);
        bundle.putBoolean("subtitle", subtitle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ori_pig01 pig01 = new ori_pig01();
        transaction.replace(R.id.frame, pig01);
        pig01.setArguments(bundle);
        transaction.commit();  //저장

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Setting.class);
                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    public void onBackPressed(){
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== 0) {
            sound = getIntent().getBooleanExtra("sound",true);
            subtitle = getIntent().getBooleanExtra("subtitle", true);
        }
    }
}
