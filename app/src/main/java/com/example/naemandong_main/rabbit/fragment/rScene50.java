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
import com.example.naemandong_main.rabbit.activity.Rabbit19;
import com.example.naemandong_main.rabbit.activity.Rabbit20;
import com.example.naemandong_main.rabbit.activity.Rabbit23;

import java.io.IOException;

public class rScene50 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    MediaPlayer mp4 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    boolean sound, subtitle;
    private AnimationDrawable frameTurtle, frameSloth;
    private View view;
    private ImageView background, box, sloth, turtle;
    private TextView subtitles;
    private String subs [] = {"\"그래 그냥 한번 경주 해보자\"","거북이는 참고 나무늘보와 경주를 하기로 했어요", "하지만 나무늘보는 거북이보다 느려서 한참 뒤쳐지고 말았어요","\"거……….북….\""};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene07, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        sloth = view.findViewById(R.id.rabbit);
        turtle = view.findViewById(R.id.turtle);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/55_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/62_hwanagizon.png")
                .into(turtle);

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
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene50_1.MP3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene50_2.mp3");
            mp2.prepare();
            mp3.setDataSource("http://49.50.174.179:9000/voice/rScene50_3.mp3");
            mp3.prepare();
            mp4.setDataSource("http://49.50.174.179:9000/voice/rScene50_4.MP3");
            mp4.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (getArguments() != null){
            sound = getArguments().getBoolean("sound");
            subtitle = getArguments().getBoolean("subtitle");
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();
        int c = mp1.getDuration() + mp2.getDuration() + mp3.getDuration();
        int d = mp1.getDuration() + mp2.getDuration() + mp3.getDuration() + mp4.getDuration();

        sloth.setBackgroundResource(R.drawable.sloth_rightgo);
        frameSloth = (AnimationDrawable) sloth.getBackground();

        final Animation turtlego = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene50_turtle);
        final Animation slothgo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene50_sloth);

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
                turtle.setImageResource(0);
                turtle.setBackgroundResource(R.drawable.turtle_rightgo);
                frameTurtle = (AnimationDrawable) turtle.getBackground();
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
                frameTurtle.start();
                frameSloth.start();
                turtle.startAnimation(turtlego);
                sloth.startAnimation(slothgo);
                if(!((Setting_data) getContext().getApplicationContext()).isRecordPlay()){
                    mp3.start();
                }
            }
        }, b);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[3]);
                if(!((Setting_data) getContext().getApplicationContext()).isRecordPlay()){
                    mp4.start();
                }
            }
        }, c);
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
        }, d);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((Rabbit19)getActivity()).play){
                    if(((Rabbit19)getActivity()).getData() == 0){
                        ((Rabbit19)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit20.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else{
                        ((Rabbit19)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit23.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                else {
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    rScene51 rscene51 = new rScene51();
                    transaction.replace(R.id.frame, rscene51);
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
        if (mp4 != null) mp4.release();
    }
}
