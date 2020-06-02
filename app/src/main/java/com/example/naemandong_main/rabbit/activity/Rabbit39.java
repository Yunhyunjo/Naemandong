package com.example.naemandong_main.rabbit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.naemandong_main.R;
import com.example.naemandong_main.Setting;
import com.example.naemandong_main.Setting_data;
import com.example.naemandong_main.rabbit.fragment.rScene93;
import com.example.naemandong_main.rabbit.fragment.rScene98;

import java.util.ArrayList;

public class Rabbit39 extends AppCompatActivity {

    Button setting;

    public boolean play = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rabbit16);

        Intent intent = getIntent();
        setting = (Button) findViewById(R.id.btn_setting);

        play = intent.getBooleanExtra("play", false);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        rScene98 rscene98 = new rScene98();
        transaction.replace(R.id.frame, rscene98);
        transaction.commit();  //저장

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rabbit39.this, Setting.class);
                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== 0) {
            boolean exit = data.getBooleanExtra("exit", false);
            if(exit){
                finish();
            }
        }
    }

    public void onBackPressed() {
    }

    public ArrayList<Integer> getMylist() {
        return ((Setting_data) this.getApplication()).getMyList();
    }

    public void clearList() {
        ((Setting_data) this.getApplication()).clearList();
    }
}
