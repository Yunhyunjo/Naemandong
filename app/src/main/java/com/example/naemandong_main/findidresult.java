package com.example.naemandong_main;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class findidresult extends AppCompatActivity {

    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);

    ImageButton id;
    ImageButton pw;
    ImageButton check;
    ImageButton exit;
    TextView userid;
    String phonenum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findidresult);

        id = (ImageButton) findViewById(R.id.id);
        pw = (ImageButton) findViewById(R.id.pw);
        check = (ImageButton) findViewById(R.id.check);
        exit = (ImageButton) findViewById(R.id.exit);
        userid = (TextView) findViewById(R.id.userid);

        Intent intent = getIntent();
        phonenum = intent.getStringExtra("phonenum");
        startFind(new findidpwData(phonenum));



        id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(findidresult.this, findid.class));
            }
        });

        pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(findidresult.this, findpw.class));
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(findidresult.this, Login.class));
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(findidresult.this, Login.class));
            }
        });
    }

    private void startFind(findidpwData data) {
        service.userFind(data).enqueue(new Callback<findResponse>() {
            @Override
            public void onResponse(Call<findResponse> call, Response<findResponse> response) {
                findResponse result = response.body();

                userid.setText(result.getId());
            }

            @Override
            public void onFailure(Call<findResponse> call, Throwable t) {
                Log.e("서버와 접속이 원활하지 않습니다.", t.getMessage());
            }
        });
    }


}
