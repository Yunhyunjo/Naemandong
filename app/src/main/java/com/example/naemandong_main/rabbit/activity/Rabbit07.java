package com.example.naemandong_main.rabbit.activity;

import androidx.annotation.Nullable;
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
import com.example.naemandong_main.rabbit.fragment.rScene24;

public class Rabbit07 extends AppCompatActivity {

    Button setting;

    public boolean play = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rabbit07);

        Intent intent = getIntent();
        setting = (Button)findViewById(R.id.btn_setting);

        play = intent.getBooleanExtra("play",false);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        rScene24 rscene24 = new rScene24();
        transaction.replace(R.id.frame, rscene24);
        transaction.commit();  //저장

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rabbit07.this, Setting.class);
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

    public void setMylist(int a){
        ((Setting_data)this.getApplication()).addMyList(a);
    }
    public int getData() {
        int data = ((Setting_data)this.getApplication()).myList.get(0);
        return data;
    }
    public void removeData() {
        ((Setting_data)this.getApplication()).myList.remove(0);
        Toast.makeText(this,String.valueOf(((Setting_data)this.getApplication()).myList),Toast.LENGTH_SHORT).show();
    }

}
