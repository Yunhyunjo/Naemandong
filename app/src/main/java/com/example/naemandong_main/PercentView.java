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

import java.io.IOException;
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

    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);


    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Paint pnt = new Paint();
        pnt.setStrokeWidth(30f);
        pnt.setColor(Color.parseColor("#FF8C00"));
        pnt.setStyle(Paint.Style.STROKE);

        pcount = pigpercent(new bookListData(2));
        rcount = rabbitpercent(new bookListData(1));

        RectF rect = new RectF();
        rect.set(218, 188, 408, 378);
        canvas.drawArc(rect, (270), (float) pcount / 17 * 360, false, pnt);
        Log.d("그리기시작 : ", " " + pcount + " " + rcount);

        rect = new RectF();
        rect.set(570, 188, 760, 378);
        canvas.drawArc(rect, (270), (float) rcount / 21 * 360, false, pnt);


    }

    private int rabbitpercent(bookListData data) {
        final Call<percentResponse> res = service.getPercent(data);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    rcount = res.execute().body().getCount();
                } catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rcount;
    }

    private int pigpercent(bookListData data) {
        final Call<percentResponse> res = service.getPercent(data);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pcount = res.execute().body().getCount();
                } catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pcount;
    }


}
