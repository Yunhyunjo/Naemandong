package com.example.naemandong_main.start;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.naemandong_main.R;

public class findpw extends AppCompatActivity {

    ImageButton id;
    ImageButton pw;
    ImageButton check;
    ImageButton exit;
    EditText userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpw);

        id = (ImageButton) findViewById(R.id.id);
        pw = (ImageButton) findViewById(R.id.pw);
        check = (ImageButton) findViewById(R.id.check);
        exit = (ImageButton) findViewById(R.id.exit);
        userid = (EditText) findViewById(R.id.userid);

        id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(findpw.this, findid.class));
            }
        });

        pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(findpw.this, findpw.class));
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(findpw.this, findpwresult.class);
                intent.putExtra("userid",userid.getText().toString());
                startActivity(intent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(findpw.this, Login.class));
            }
        });
    }
}
