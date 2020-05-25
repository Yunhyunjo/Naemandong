package com.example.naemandong_main;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.naemandong_main.Data.bookListData;
import com.example.naemandong_main.Data.percentResponse;
import com.example.naemandong_main.Network.RetrofitClient;
import com.example.naemandong_main.Network.ServiceApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PercentView extends View {
    public PercentView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }
    public int pcount, rcount;
    public int order = 0;

    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);


    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Paint pnt = new Paint();
        pnt.setStrokeWidth(30f);
        pnt.setColor(Color.parseColor("#FF8C00"));
        pnt.setStyle(Paint.Style.STROKE);

        switch(order) {
            case 0 :
               /* pcount = pigpercent(new bookListData(2));
                rcount = rabbitpercent(new bookListData(1));*/
                order++;
            case 1 :
                RectF rect = new RectF();
                rect.set(218, 188, 408, 378);
                canvas.drawArc(rect, (270), (float)pigpercent(new bookListData(2))/3, false, pnt);
                Log.d("그리기시작 : ", " "+pigpercent(new bookListData(2))+" "+order);

                rect = new RectF();
                rect.set(570, 188, 760, 378);
                canvas.drawArc(rect, (270), (float)rabbitpercent(new bookListData(1))/21, false, pnt);
                break;
        }


    }

    public int pigpercent(bookListData data) {
        final int[] c = new int[1];

        service.getPercent(data).enqueue(new Callback<percentResponse>() {
            @Override
            public void onResponse(Call<percentResponse> call, Response<percentResponse> response) {
                percentResponse resource = response.body();
                pcount = resource.getCount();
                c[0] = pcount;

                Log.d("ppercent함수 : ", " "+pcount);
            }

            @Override
            public void onFailure(Call<percentResponse> call, Throwable t) {
                //               Toast.makeText(rfinal01.this, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
                //               Log.e("회원가입에 실패했습니다.", t.getMessage());
            }
        });
        return c[0];
    }

    private int rabbitpercent(bookListData data) {
        final int[] a = new int[1];

        service.getPercent(data).enqueue(new Callback<percentResponse>() {
            @Override
            public void onResponse(Call<percentResponse> call, Response<percentResponse> response) {
                percentResponse resource = response.body();
                rcount = resource.getCount();
                a[0] = rcount;

                Log.d("rpercent함수 : ", " " + rcount);
            }

            @Override
            public void onFailure(Call<percentResponse> call, Throwable t) {
                //               Toast.makeText(rfinal01.this, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
                //               Log.e("회원가입에 실패했습니다.", t.getMessage());
            }
        });

        return a[0];
    }




}
