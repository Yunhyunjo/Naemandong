package com.example.naemandong_main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.naemandong_main.Data.bookListData;
import com.example.naemandong_main.Data.percentResponse;
import com.example.naemandong_main.Network.RetrofitClient;
import com.example.naemandong_main.Network.ServiceApi;
import com.example.naemandong_main.start.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    Button setting;
    Button back_but1;
    Button back_but2;
    Button back_but3;

    Button sketchbook_btn;
    Button book_btn;
    Button making_btn;
    Button voice_btn;

    ImageView imageView1;
    PercentView percentView;

    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.InitializeView();
        this.SetListener();

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        Toast.makeText(MainActivity.this, username + "님 환영합니다.", Toast.LENGTH_LONG).show();
    }


    public void InitializeView() {
        setting = (Button)findViewById(R.id.btn_setting);
        back_but1 = (Button)findViewById(R.id.back_btn1);
        back_but2 = (Button)findViewById(R.id.back_btn2);
        back_but3 = (Button)findViewById(R.id.back_btn3);

        sketchbook_btn = (Button)findViewById(R.id.sketchbook_btn);
        book_btn = (Button) findViewById(R.id.book_btn);
        making_btn = (Button) findViewById(R.id.making_btn);
        voice_btn = (Button) findViewById(R.id.voice_btn);

        imageView1 = (ImageView)findViewById(R.id.image1);
        percentView = (PercentView)findViewById(R.id.percentView);
    }

    public void SetListener() {
        back_but1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                imageView1.setImageResource(R.drawable.back01_2);
            }
        });
        back_but2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                imageView1.setImageResource(R.drawable.back02);
            }
        });
        back_but3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                imageView1.setImageResource(R.drawable.back03);
            }
        });
        book_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), Book.class);
                startActivities(new Intent[]{intent});
            }
        });
        making_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), Making.class);
                startActivities(new Intent[]{intent});
            }
        });
        sketchbook_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), SketchbookActivity.class);
                startActivities(new Intent[]{intent});
            }
        });
        voice_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), Voice.class);
                startActivities(new Intent[]{intent});
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Setting.class);
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

}
