package com.example.naemandong_main.start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.naemandong_main.Data.registerData;
import com.example.naemandong_main.Data.registerResponse;
import com.example.naemandong_main.Data.validateData;
import com.example.naemandong_main.Data.validateResponse;
import com.example.naemandong_main.Network.RetrofitClient;
import com.example.naemandong_main.Network.ServiceApi;
import com.example.naemandong_main.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);

    ImageButton exit;
    ImageButton join;
    EditText id;
    EditText password;
    EditText nickname;
    EditText name;
    EditText phone;
    ImageButton id_check;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        exit = (ImageButton) findViewById(R.id.exit);
        join = (ImageButton) findViewById(R.id.join);
        id = (EditText) findViewById(R.id.id);
        password = (EditText) findViewById(R.id.password);
        name = (EditText) findViewById(R.id.name);
        nickname = (EditText) findViewById(R.id.nickname);
        phone = (EditText) findViewById(R.id.phone);
        id_check = (ImageButton) findViewById(R.id.id_check);



        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Register.this, Login.class));
            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mem_userid = id.getText().toString();
                String mem_password = password.getText().toString();
                String mem_username = name.getText().toString();
                String mem_nickname = nickname.getText().toString();
                String mem_phone = phone.getText().toString();

                startJoin(new registerData(mem_userid, mem_password, mem_username, mem_nickname, mem_phone));
                finish();
            }
        });

        id_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mem_userid = id.getText().toString();

                check_Id(new validateData(mem_userid));
            }
        });
    }

    private void startJoin(registerData data) {
        service.userJoin(data).enqueue(new Callback<registerResponse>() {
            @Override
            public void onResponse(Call<registerResponse> call, Response<registerResponse> response) {
                registerResponse result = response.body();
                Toast.makeText(Register.this, result.getMessage(), Toast.LENGTH_SHORT).show();

                if (result.getCode() == 200) {
                    finish();
                }
            }

            @Override
            public void onFailure(Call<registerResponse> call, Throwable t) {
                Toast.makeText(Register.this, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
                Log.e("회원가입에 실패했습니다.", t.getMessage());
            }
        });
    }

    private void check_Id(validateData data) {
        service.idValid(data).enqueue(new Callback<validateResponse>() {
            @Override
            public void onResponse(Call<validateResponse> call, Response<validateResponse> response) {
                validateResponse result = response.body();
                Toast.makeText(Register.this, result.getMessage(), Toast.LENGTH_SHORT).show();

                if (result.getCode() == 200) {
                    Toast.makeText(Register.this, "사용 가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Register.this, "이미 등록된 아이디입니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<validateResponse> call, Throwable t) {
                Toast.makeText(Register.this, "서버와 접속이 원활하지 않습니다.", Toast.LENGTH_SHORT).show();
                Log.e("서버와 접속이 원활하지 않습니다.", t.getMessage());
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
