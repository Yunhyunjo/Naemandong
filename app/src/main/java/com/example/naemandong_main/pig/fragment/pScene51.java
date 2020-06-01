package com.example.naemandong_main.pig.fragment;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import com.example.naemandong_main.pig.activity.Pig06;
import com.example.naemandong_main.pig.activity.Pig07;
import com.example.naemandong_main.pig.activity.Pig15;
import com.example.naemandong_main.pig.activity.Pig16;
import com.example.naemandong_main.pig.activity.Pig19;

import java.io.IOException;

// 선택지 전
public class pScene51 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    boolean sound, subtitle;
    private ImageView box;
    AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, wolf, pigs;
    private ImageButton next;
    private TextView subtitles;
    private String subs[] = {"막내 돼지가 문을 열어주지 않자 늑대는 돌집을 발로 뻥 하고 찼어요.", "하지만 튼튼한 막내 돼지의 돌집은 무너지지 않았어요.", "\"우리집은 단단해서 나쁜 늑대 너는 무너뜨릴 수 없어\""};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene50, container,false);

        background = view.findViewById(R.id.background);
        pigs = view.findViewById(R.id.pigs);
        wolf = view.findViewById(R.id.wolf);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);
        box = view.findViewById(R.id.subtitlebox);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/19_bg-01.png")
                .into(background);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/20_house-01.png")
                .into(pigs);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/pig/pScene32_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/pig/pScene32_2.mp3");
            mp2.prepare();
            mp3.setDataSource("http://49.50.174.179:9000/voice/pig/pScene33_1.mp3");
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

        if (getArguments() != null){
            sound = getArguments().getBoolean("sound");
            subtitle = getArguments().getBoolean("subtitle");
        }

        wolf.setBackgroundResource(R.drawable.wolf_s32);
        frameAnimation = (AnimationDrawable) wolf.getBackground();
//        Animation wolfgo = AnimationUtils.loadAnimation(getActivity(), R.anim.pscene05);

        subtitles.setText(subs[0]);
        frameAnimation.start();
        //      wolf.startAnimation(wolfgo);


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
                if(((Pig15)getActivity()).play){
                    if(((Pig15)getActivity()).getData() == 0){
                        ((Pig15)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig19.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else {
                        ((Pig15)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig16.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                else{
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    pScene52 pscene52 = new pScene52();
                    transaction.replace(R.id.frame, pscene52);
                    transaction.commit();  //저장
                }
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
