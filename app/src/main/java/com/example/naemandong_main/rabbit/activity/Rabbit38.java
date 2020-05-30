package com.example.naemandong_main.rabbit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.naemandong_main.R;
import com.example.naemandong_main.Setting;
import com.example.naemandong_main.Setting_data;
import com.example.naemandong_main.rabbit.fragment.rScene80;
import com.example.naemandong_main.rabbit.fragment.rScene95;

public class Rabbit38 extends AppCompatActivity {

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
        rScene95 rscene95 = new rScene95();
        transaction.replace(R.id.frame, rscene95);
        transaction.commit();  //저장

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Setting.class);
                startActivities(new Intent[]{intent});
            }
        });
    }

    public void onBackPressed() {
    }

    public void setMylist(int a) {
        ((Setting_data) this.getApplication()).addMyList(a);
    }

    public int getData() {
        int data = ((Setting_data) this.getApplication()).myList.get(0);
        return data;
    }

    public void removeData() {
        ((Setting_data) this.getApplication()).myList.remove(0);
        Toast.makeText(this, String.valueOf(((Setting_data) this.getApplication()).myList), Toast.LENGTH_SHORT).show();
    }

}
