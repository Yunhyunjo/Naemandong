package com.example.naemandong_main.rabbit.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.naemandong_main.R;
import com.example.naemandong_main.Setting;
import com.example.naemandong_main.Setting_data;
import com.example.naemandong_main.rabbit.fragment.rScene01;

import java.util.ArrayList;

public class Rabbit01 extends AppCompatActivity {

    public boolean play = false;
    Button setting;
    public ArrayList<Integer> mySelect = new ArrayList<Integer>();
    public boolean sound;
    public boolean subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rabbit01);

        setting = (Button)findViewById(R.id.btn_setting);

        Intent intent = getIntent();
        mySelect = intent.getIntegerArrayListExtra("select");
        play = intent.getBooleanExtra("play",false);

        if (play){
            ((Setting_data)this.getApplication()).myList = mySelect;
        }

        Bundle bundle = new Bundle();
        bundle.putBoolean("sound", sound);
        bundle.putBoolean("subtitle", subtitle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        rScene01 rscene01 = new rScene01();
        transaction.replace(R.id.frame, rscene01);
        rscene01.setArguments(bundle);
        transaction.commit();  //저장

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rabbit01.this, Setting.class);
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
            sound = data.getBooleanExtra("sound",true);
            subtitle = data.getBooleanExtra("subtitle", true);
            boolean exit = data.getBooleanExtra("exit", false);
            if(exit){
                finish();
            }
            Log.d("exit", String.valueOf(exit));
        }
    }

    public void setMylist(int a){
        ((Setting_data)this.getApplication()).addMyList(a);
    }
    public int getData() {
        int data = ((Setting_data)this.getApplication()).myList.get(0);
        return data;
    }
    public void removeData() {
        ((Setting_data)this.getApplication()).myList.remove(0);
        //Toast.makeText(this,String.valueOf(((Setting_data)this.getApplication()).myList),Toast.LENGTH_LONG).show();
    }
}
