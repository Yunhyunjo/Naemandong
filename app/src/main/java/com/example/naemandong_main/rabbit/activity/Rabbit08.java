package com.example.naemandong_main.rabbit.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.naemandong_main.R;
import com.example.naemandong_main.Setting;
import com.example.naemandong_main.Setting_data;
import com.example.naemandong_main.rabbit.fragment.rScene27;

import java.util.ArrayList;

public class Rabbit08 extends AppCompatActivity {

    Button setting;
    public boolean play = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rabbit08);

        setting = (Button)findViewById(R.id.btn_setting);
        Intent intent = getIntent();
        play = intent.getBooleanExtra("play",false);


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        rScene27 rscene27 = new rScene27();
        transaction.replace(R.id.frame, rscene27);
        transaction.commit();  //저장

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rabbit08.this, Setting.class);
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
