package com.example.naemandong_main.rabbit.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.naemandong_main.R;
import com.example.naemandong_main.Setting;
import com.example.naemandong_main.Setting_data;
import com.example.naemandong_main.rabbit.fragment.rScene28;

import java.util.ArrayList;

public class Rabbit09 extends AppCompatActivity {

    Button setting;
    public boolean play = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rabbit09);

        setting = (Button)findViewById(R.id.btn_setting);
        Intent intent = getIntent();
        play = intent.getBooleanExtra("play",false);


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        rScene28 rscene28 = new rScene28();
        transaction.replace(R.id.frame, rscene28);
        transaction.commit();  //저장

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Setting.class);
                startActivities(new Intent[]{intent});
            }
        });
    }

    @Override
    public void onBackPressed(){
    }

    public ArrayList<Integer> getMylist(){
        return ((Setting_data)this.getApplication()).getMyList();
    }
    public void clearList(){
        ((Setting_data)this.getApplication()).clearList();
    }
}
