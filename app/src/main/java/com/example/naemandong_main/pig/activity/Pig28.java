package com.example.naemandong_main.pig.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.naemandong_main.R;
import com.example.naemandong_main.Setting;
import com.example.naemandong_main.Setting_data;
import com.example.naemandong_main.pig.fragment.pScene76;
import com.example.naemandong_main.pig.fragment.pScene86;

public class Pig28 extends AppCompatActivity {

    public boolean play = false;
    Button setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pig02);

        Intent intent = getIntent();
        play = intent.getBooleanExtra("play",false);
        setting = (Button)findViewById(R.id.btn_setting);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        pScene86 pscene86 = new pScene86();
        transaction.replace(R.id.frame, pscene86);
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
        ((Setting_data)this.getApplication()).myList.remove(0);
        return data;
    }
}
