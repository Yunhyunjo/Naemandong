package com.example.naemandong_main.rabbit.fragment;

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
import com.example.naemandong_main.rabbit.activity.Rabbit15;

import java.io.IOException;
import java.util.ArrayList;

public class rScene91 extends Fragment {

    private AnimationDrawable frameTurtle, frameRabbit;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    boolean sound, subtitle;
    private View view;
    private ImageView background, box, lion, skate_turtle, bush1, bush2;
    private TextView subtitles;
    private String subs [] = {"거북이가 인라인 스케이트를 타고 빠르게 달려가던 바로 그때", "\"아얏! 아이쿠 아파라...\"", "인라인을 처음 탄 거북이는 그만 콰당 넘어지고 말았어요."};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene91, container,false);

        background = view.findViewById(R.id.background);
        bush1 = view.findViewById(R.id.bush1);
        bush2 = view.findViewById(R.id.bush2);
        lion = view.findViewById(R.id.lion);
        skate_turtle = view.findViewById(R.id.turtle);
        box = view.findViewById(R.id.subtitlebox);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/48_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/48_left.png")
                .into(bush1);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/48_right.png")
                .into(bush2);

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
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene91_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene91_2.MP3");
            mp2.prepare();
            mp3.setDataSource("http://49.50.174.179:9000/voice/rScene91_3.mp3");
            mp3.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (getArguments() != null){
            sound = getArguments().getBoolean("sound");
            subtitle = getArguments().getBoolean("subtitle");
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();
        int c = mp1.getDuration() + mp2.getDuration()+ mp3.getDuration();

        lion.setBackgroundResource(R.drawable.lion_s91);
        frameRabbit = (AnimationDrawable) lion.getBackground();
        skate_turtle.setBackgroundResource(R.drawable.tutle_s91);
        frameTurtle = (AnimationDrawable) skate_turtle.getBackground();
        frameTurtle.start();

        Animation rabbitgo = AnimationUtils.loadAnimation(getActivity(), R.anim.rabbit_backgo);
        Animation turtlego = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene91);

        frameRabbit.start();
        frameTurtle.start();
        lion.startAnimation(rabbitgo);
        skate_turtle.startAnimation(turtlego);
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

                if(!((Setting_data) getContext().getApplicationContext()).isRecordPlay()){
                    mp2.start();
                }
                subtitles.setText(subs[1]);
                frameTurtle.stop();
                skate_turtle.setBackgroundResource(0);
                skate_turtle.setImageResource(R.drawable.ggwadang1);
            }
        }, a);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO

                if(!((Setting_data) getContext().getApplicationContext()).isRecordPlay()){
                    mp3.start();
                }
                subtitles.setText(subs[2]);
                next.setVisibility(View.VISIBLE);
            }
        }, b);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                if(((Setting_data)getContext().getApplicationContext()).isRecord()){
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
                rScene92 rscene92 = new rScene92();
                transaction.replace(R.id.frame,rscene92);
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
    }
}
