package com.example.naemandong_main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;


public class Voice extends AppCompatActivity {

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);

        context = this;

        Bundle bundle = new Bundle();
        bundle.putString("what","Voice");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fatale_select fatale_select = new Fatale_select();
        fatale_select.setArguments(bundle);
        transaction.replace(R.id.framelayout, fatale_select);
        transaction.commit();  //저장

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void setSound(){
        ((Setting_data)this.getApplication()).setRecord(true);
    }
    public void setBook_no(int book_no){
        ((Setting_data)this.getApplication()).setBook_no(book_no);
    }
}