package com.example.naemandong_main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class Rabbit05 extends AppCompatActivity {

    Button setting;

    boolean play = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rabbit04);

        setting = (Button)findViewById(R.id.btn_setting);

        Intent intent = getIntent();
        play = intent.getBooleanExtra("play",false);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        rScene17 rscene17 = new rScene17();
        transaction.replace(R.id.frame, rscene17);
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
