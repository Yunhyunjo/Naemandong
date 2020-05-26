package com.example.naemandong_main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.naemandong_main.original.rabbit.Rabbit_original;
import com.example.naemandong_main.pig.activity.Pig01;
import com.example.naemandong_main.rabbit.activity.Rabbit01;

public class Making extends AppCompatActivity {

    private ImageButton pig, rabbit;
    private Button book,voice,sketchbook;
    public static Activity making;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_making);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        making = this;

        pig = (ImageButton) findViewById(R.id.pig);
        rabbit = (ImageButton) findViewById(R.id.rabbit);
        voice = (Button) findViewById(R.id.voice_btn);
        book = (Button) findViewById(R.id.book_btn);
        sketchbook = (Button) findViewById(R.id.sketchbook_btn);

        rabbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Making.this, Original_story.class);
                intent.putExtra("story", 1);
                startActivity(intent);
                finish();
            }
        });

        pig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Making.this, Original_story.class);
                intent.putExtra("story", 2);
                startActivity(intent);
                finish();
            }
        });

        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Making.this, Book.class));
                finish();
            }
        });
        sketchbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Making.this, SketchbookActivity.class));
                finish();
            }
        });
    }
}
