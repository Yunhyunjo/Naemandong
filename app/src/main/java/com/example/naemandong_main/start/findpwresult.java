package com.example.naemandong_main.start;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.naemandong_main.Data.findResponse;
import com.example.naemandong_main.Data.findidpwData;
import com.example.naemandong_main.Network.RetrofitClient;
import com.example.naemandong_main.Network.ServiceApi;
import com.example.naemandong_main.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class findpwresult extends AppCompatActivity {

    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);

    ImageButton id;
    ImageButton pw;
    ImageButton gologin;
    ImageButton exit;
    TextView userpw;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpwresult);

        id = (ImageButton) findViewById(R.id.id);
        pw = (ImageButton) findViewById(R.id.pw);
        gologin = (ImageButton) findViewById(R.id.gologin);
        exit = (ImageButton) findViewById(R.id.exit);
        userpw = (TextView) findViewById(R.id.userpw);

        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");
        startFind(new findidpwData(userid));

        id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(findpwresult.this, findid.class));
            }
        });

        pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(findpwresult.this, findpw.class));
            }
        });

        gologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(findpwresult.this, Login.class));
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(findpwresult.this, Login.class));
            }
        });
    }

    private void startFind(findidpwData data) {
        service.userFind(data).enqueue(new Callback<findResponse>() {
            @Override
            public void onResponse(Call<findResponse> call, Response<findResponse> response) {
                findResponse result = response.body();

                userpw.setText(result.getPw());
            }

            @Override
            public void onFailure(Call<findResponse> call, Throwable t) {
                Log.e("서버와 접속이 원활하지 않습니다.", t.getMessage());
            }
        });
    }
}
