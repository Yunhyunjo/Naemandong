package com.example.naemandong_main.rabbit.fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.naemandong_main.R;
import com.example.naemandong_main.Record;
import com.example.naemandong_main.Setting;
import com.example.naemandong_main.Setting_data;

import java.io.IOException;

public class rScene01 extends Fragment {

    private View view;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    private ImageView background, box;
    private ImageButton next;
    private TextView subtitles;
    boolean sound, subtitle;
    boolean t = false;
    private String subs [] = {"어느 숲 속에 토끼, 사자, 나무늘보, 거북이가 살고 있었어요.", "동물들은 매일같이 자신이 가장 빠르다며 싸우곤 했지요."};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.rscene01, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        if(!((Setting_data) getContext().getApplicationContext()).isRecord() && !((Setting_data) getContext().getApplicationContext()).isRecordPlay()){
            (new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!Thread.interrupted()) {
                        try {
                            Thread.sleep(1000); //1초 간격으로 실행
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (((Setting_data) getContext().getApplicationContext()).getSubtitle() == true) {
                                        subtitles.setVisibility(View.VISIBLE);
                                        box.setVisibility(View.VISIBLE);
                                    } else {
                                        subtitles.setVisibility(View.INVISIBLE);
                                        box.setVisibility(View.INVISIBLE);
                                    }

                                }
                            });
                        } catch (InterruptedException e) {
                            // error
                        }
                        if (t)
                            break;
                    }

                }
            })).start();
        }
        else{

        }

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene01_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene01_2.mp3");
            mp2.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(((Setting_data) getContext().getApplicationContext()).isRecordPlay()){
            String path = ((Setting_data) getContext().getApplicationContext()).getRecordone();
            ((Setting_data) getContext().getApplicationContext()).removeRecordData();
            box.setVisibility(View.INVISIBLE);
            subtitles.setVisibility(View.INVISIBLE);
            try {
                recordmp.setDataSource(path);
                recordmp.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();

        if (getArguments() != null){
            sound = getArguments().getBoolean("sound");
            subtitle = getArguments().getBoolean("subtitle");
        }

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/1/1_fin.jpg")
                .into(background);

        subtitles.setText(subs[0]);
        if(((Setting_data) getContext().getApplicationContext()).isRecordPlay()){
            recordmp.start();
        }
        else {
            mp1.start();
        }
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
                if(!((Setting_data) getContext().getApplicationContext()).isRecordPlay()){
                    mp2.start();
                }
            }
        }, a);
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
                t = true;
            }
        }, b);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rScene02 rscene02 = new rScene02();
                transaction.replace(R.id.frame,rscene02);
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
        if (recordmp != null) recordmp.release();
    }

}
