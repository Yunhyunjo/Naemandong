package com.example.naemandong_main.pig.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.naemandong_main.R;
import com.example.naemandong_main.Setting;
import com.example.naemandong_main.Setting_data;
import com.example.naemandong_main.pig.fragment.pScene01;

import java.util.ArrayList;

public class Pig01 extends AppCompatActivity {

    public boolean play = false;
    Button setting;
    public ArrayList<Integer> mySelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pig01);

        setting = (Button)findViewById(R.id.btn_setting);

        Intent intent = getIntent();
        mySelect = intent.getIntegerArrayListExtra("select");
        play = intent.getBooleanExtra("play",false);

        if (play){
            ((Setting_data)this.getApplication()).myList = mySelect;
        }

        Bundle bundle = new Bundle();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        pScene01 pscene01 = new pScene01();
        transaction.replace(R.id.frame, pscene01);
        pscene01.setArguments(bundle);
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

    public void setMylist(int a){
        ((Setting_data)this.getApplication()).addMyList(a);
    }
    public int getData() {
        int data = ((Setting_data)this.getApplication()).myList.get(0);
        return data;
    }
    public void removeData() {
        ((Setting_data)this.getApplication()).myList.remove(0);
        Toast.makeText(this,String.valueOf(((Setting_data)this.getApplication()).myList),Toast.LENGTH_LONG).show();
    }
}
