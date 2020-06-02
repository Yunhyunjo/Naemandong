package com.example.naemandong_main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.FragmentTransaction;




public class SketchbookActivity extends AppCompatActivity {

    public static Context context;
    private Button book,making,voice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sketchbook);

        book = (Button) findViewById(R.id.book_btn);
        making = (Button) findViewById(R.id.making_btn);
        voice = (Button) findViewById(R.id.voice_btn);

        context = this;

        Bundle bundle = new Bundle();
        bundle.putString("what","Sketchbook");
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
                startActivity(new Intent(SketchbookActivity.this, Book.class));
                finish();
            }
        });
        making.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SketchbookActivity.this, Making.class));
                finish();
            }
        });
        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SketchbookActivity.this, Voice.class));
                finish();
            }
        });
    }
}
