package com.example.naemandong_main.rabbit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.naemandong_main.R;
import com.example.naemandong_main.Setting;
import com.example.naemandong_main.Setting_data;
import com.example.naemandong_main.rabbit.fragment.rScene57;

import java.util.ArrayList;

public class Rabbit22 extends AppCompatActivity {

    Button setting;

    public boolean play = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rabbit16);

        Intent intent = getIntent();
        setting = (Button)findViewById(R.id.btn_setting);

        play = intent.getBooleanExtra("play",false);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        rScene57 rscene57 = new rScene57();
        transaction.replace(R.id.frame, rscene57);
        transaction.commit();  //저장

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Setting.class);
                startActivities(new Intent[]{intent});
            }
        });
    }

    public void onBackPressed(){
    }

    public ArrayList<Integer> getMylist(){
        return ((Setting_data)this.getApplication()).getMyList();
    }
    public void clearList(){
        ((Setting_data)this.getApplication()).clearList();
    }

    public Boolean isRecord() {
        return ((Setting_data)this.getApplication()).isRecord();
    }

    public void setRecord() {
        ((Setting_data)this.getApplication()).setRecord(false);
    }
}
