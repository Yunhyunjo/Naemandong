package com.example.naemandong_main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class findid extends AppCompatActivity {

    ImageButton id;
    ImageButton pw;
    ImageButton check;
    ImageButton exit;
    EditText phonenum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findid);

        id = (ImageButton) findViewById(R.id.id);
        pw = (ImageButton) findViewById(R.id.pw);
        check = (ImageButton) findViewById(R.id.check);
        exit = (ImageButton) findViewById(R.id.exit);
        phonenum = (EditText) findViewById(R.id.phonenum);

        id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(findid.this, findid.class));
            }
        });

        pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(findid.this, findpw.class));
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

                Intent intent = new Intent(findid.this, findidresult.class);
                intent.putExtra("phonenum",phonenum.getText().toString());
                startActivity(intent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(findid.this, Login.class));
            }
        });
    }
}
