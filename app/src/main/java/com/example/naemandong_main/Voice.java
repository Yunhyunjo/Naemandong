package com.example.naemandong_main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Voice extends AppCompatActivity {

    public static Context context;
    private Button book,making,sketchbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);

        book = (Button) findViewById(R.id.book_btn);
        making = (Button) findViewById(R.id.making_btn);
        sketchbook = (Button) findViewById(R.id.sketchbook_btn);

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

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Voice.this, Book.class));
                finish();

            }
        });
        making.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Voice.this, Making.class));
                finish();
            }
        });
        sketchbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Voice.this, SketchbookActivity.class));
                finish();
            }
        });
    }

    public void setSound(){
        ((Setting_data)this.getApplication()).setRecord(true);
    }
    public void setBook_no(int book_no){
        ((Setting_data)this.getApplication()).setBook_no(book_no);
    }

    public void clearRecordList(){
        ((Setting_data)this.getApplication()).clearRecordList();
    }
}