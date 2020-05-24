package com.example.naemandong_main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.naemandong_main.Data.loginData;
import com.example.naemandong_main.Data.loginResponse;
import com.example.naemandong_main.Network.RetrofitClient;
import com.example.naemandong_main.Network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);

    ImageButton login, exit;
    Button register,find;
    EditText id, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        final Start start = (Start) Start.activity;

        login = (ImageButton) findViewById(R.id.login);
        register = (Button) findViewById(R.id.Register);
        exit = (ImageButton) findViewById(R.id.exit);
        id = (EditText) findViewById(R.id.ID);
        password = (EditText) findViewById(R.id.password);
        find = (Button) findViewById(R.id.button2);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mem_userid = id.getText().toString();
                String mem_password = password.getText().toString();

                startlogin(new loginData(mem_userid, mem_password));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Login.this, Register.class));
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Login.this, findid.class));
            }
        });
    }

    private void startlogin(loginData data) {
        service.userLogin(data).enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                loginResponse result = response.body();
                Toast.makeText(Login.this, result.getMessage(), Toast.LENGTH_SHORT).show();

                if (result.getCode() == 200) {
                    Toast.makeText(Login.this, result.getUsername()+"님 환영합니다.", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(Login.this, MainActivity.class));
                }
                else {
                    Toast.makeText(Login.this, "아이디, 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                Toast.makeText(Login.this, "서버 연결에 실패했습니다.", Toast.LENGTH_SHORT).show();
                Log.e("서버 연결에 실패했습니다.", t.getMessage());
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }


}
