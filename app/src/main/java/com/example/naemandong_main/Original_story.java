package com.example.naemandong_main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.naemandong_main.original.pig.Pig_original;
import com.example.naemandong_main.original.rabbit.Rabbit_original;
import com.example.naemandong_main.pig.activity.Pig01;
import com.example.naemandong_main.rabbit.activity.Rabbit01;

public class Original_story extends AppCompatActivity {

    private ImageView background;
    private Button original, skip;
    private int story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_original_story);

        background = (ImageView) findViewById(R.id.background);
        original = (Button) findViewById(R.id.original);
        skip = (Button) findViewById(R.id.skip);

        Intent intent = getIntent();
        story = intent.getIntExtra("story",0);

        original.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (story == 1) {
                    Intent intent = new Intent(Original_story.this, Rabbit_original.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(Original_story.this, Pig_original.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (story == 1) {
                    Intent intent = new Intent(Original_story.this, Rabbit01.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(Original_story.this, Pig01.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
