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

public class rScene31 extends Fragment {

    private View view;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    boolean sound, subtitle;
    private ImageView background, box, bed, front;
    private TextView subtitles;
    private String subs [] = {"그때 마침 토끼는 베개를 발견했어요 ", "베개를 찾은 토끼는 쿨쿨 깊은 잠이 들었어요."};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene31, container,false);

        front = view.findViewById(R.id.front);
        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        bed = view.findViewById(R.id.bed);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/34_back.png")
                .into(background);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/34_bed.png")
                .into(bed);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/33_rabbit.png")
                .into(front);

        if(((Setting_data) getContext().getApplicationContext()).isRecordPlay()){
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
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene31_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene31_2.mp3");
            mp2.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (getArguments() != null){
            sound = getArguments().getBoolean("sound");
            subtitle = getArguments().getBoolean("subtitle");
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();

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
                front.setImageResource(0);
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/rabbit/5/34_r_bed.png")
                        .into(bed);
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
            }
        }, b);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rScene32 rscene32 = new rScene32();
                transaction.replace(R.id.frame,rscene32);
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
