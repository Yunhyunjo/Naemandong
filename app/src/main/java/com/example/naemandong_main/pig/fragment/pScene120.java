package com.example.naemandong_main.pig.fragment;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
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
import com.example.naemandong_main.pig.activity.Pig28;
import com.example.naemandong_main.pig.activity.Pig31;

import java.io.IOException;

public class pScene120 extends Fragment {

    AnimationDrawable frameAnimation;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    private View view;
    private ImageView background, wolf, pig, box;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"막내 돼지가 문을 열어주지 않자 늑대는 모래집을 발로 뻥 하고 찼어요.", "그러자 막내 돼지의 모래 집이 와르르 무너지고 말았어요.", "“으하하! 힘 센 나 늑대는 모든 걸 다 부숴버릴 수 있지!!”" };
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene97, container,false);

        background = view.findViewById(R.id.background);
        pig = view.findViewById(R.id.pig);
        box = view.findViewById(R.id.subtitlebox);
        wolf = view.findViewById(R.id.wolf);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/34_bg-01.png")
                .into(background);

        Glide.with(view)
                .load("http://49.50.174.179:9000/images/pig/1/20_pig1-01.png")
                .into(pig);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/pig/pScene97_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/pig/pScene97_2.mp3");
            mp2.prepare();
            mp3.setDataSource("http://49.50.174.179:9000/voice/pig/pScene120_3.mp3");
            mp3.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();
        int c = mp1.getDuration() + mp2.getDuration() + mp3.getDuration();

        wolf.setBackgroundResource(R.drawable.wolf_s32);
        frameAnimation = (AnimationDrawable) wolf.getBackground();

        subtitles.setText(subs[0]);
        frameAnimation.start();
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
                frameAnimation.stop();
                wolf.setBackgroundResource(0);

                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/pig/1/34_bg-01.png")
                        .into(background);

                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/pig/1/23_wolf-01.png")
                        .into(wolf);

                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/pig/1/22_pigs-01.png")
                        .into(pig);
                if(!((Setting_data) getContext().getApplicationContext()).isRecordPlay()){
                    mp2.start();
                }

            }
        }, a);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[2]);
                if(!((Setting_data) getContext().getApplicationContext()).isRecordPlay()){
                    mp3.start();
                }
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
                pScene121 pscene121 = new pScene121();
                transaction.replace(R.id.frame,pscene121);
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
