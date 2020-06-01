package com.example.naemandong_main.pig.fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.naemandong_main.R;
import com.example.naemandong_main.Record;
import com.example.naemandong_main.Setting_data;

import java.io.IOException;

// 선택지 후
public class pScene26 extends Fragment {

    private View view;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    private ImageView background, pig, memo, wolf, box;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"집이 무너진 첫째 돼지와 둘째 돼지는 엄마 돼지의 집으로 도망쳤어요.", "메모 – 엄마 여행 갔다올게 ~ 밥 잘 먹고 사이좋게 있으렴~","그러나 엄마 돼지의 집에는 짧은 편지만 붙어 있었어요." };
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene26, container,false);
        box = view.findViewById(R.id.subtitlebox);
        background = view.findViewById(R.id.background);
        pig = view.findViewById(R.id.pig);
        memo = view.findViewById(R.id.memo);
        wolf = view.findViewById(R.id.wolf);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/14_bg-01.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/14_pg1-01.png")
                .into(pig);

        if (((Setting_data) getContext().getApplicationContext()).isRecordPlay()) {
            String path = ((Setting_data) getContext().getApplicationContext()).getRecordone();
            ((Setting_data) getContext().getApplicationContext()).removeRecordData();
            try {
                recordmp.setDataSource(path);
                recordmp.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/pig/pScene26_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/pig/pScene26_2.mp3");
            mp2.prepare();
            mp3.setDataSource("http://49.50.174.179:9000/voice/pig/pScene26_3.mp3");
            mp3.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();
        int c = mp1.getDuration() + mp2.getDuration() + mp3.getDuration();


        subtitles.setText(subs[0]);
        if (((Setting_data) getContext().getApplicationContext()).isRecordPlay()) {
            recordmp.start();
        } else {
            mp1.start();
        }
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/pig/1/14_memo-01.png")
                        .into(memo);
                subtitles.setText(subs[1]);
                if (!((Setting_data) getContext().getApplicationContext()).isRecordPlay()) {
                    mp2.start();
                }
            }
        }, a);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[2]);
                if (!((Setting_data) getContext().getApplicationContext()).isRecordPlay()) {
                    mp3.start();
                }
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/pig/1/14_wolf1.png")
                        .into(wolf);
            }
        }, b);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                if (((Setting_data) getContext().getApplicationContext()).isRecord()) {
                    subtitles.setVisibility(View.INVISIBLE);
                    box.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(getActivity(), Record.class);
                    startActivity(intent);
                }
                next.setVisibility(View.VISIBLE);
            }
        }, c);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                pScene27 pscene27 = new pScene27();
                transaction.replace(R.id.frame,pscene27);
                transaction.commit();  //저장
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mp1 != null) mp1.release();
        if (mp2 != null) mp2.release();
        if (mp3 != null) mp3.release();
        if (recordmp != null) recordmp.release();
    }

}
