package com.example.naemandong_main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Book extends AppCompatActivity {

    private Button voice,making,sketchbook;
    public String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        voice = (Button) findViewById(R.id.voice_btn);
        making = (Button) findViewById(R.id.making_btn);
        sketchbook = (Button) findViewById(R.id.sketchbook_btn);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fatale_select fatale_select = new Fatale_select();
        transaction.replace(R.id.framelayout, fatale_select);
        transaction.commit();  //저장

        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        making.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Book.this, Making.class));
                finish();
            }
        });
        sketchbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Book.this, SketchbookActivity.class));
                finish();
            }
        });

    }
}
