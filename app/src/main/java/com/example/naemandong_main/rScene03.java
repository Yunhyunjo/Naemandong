package com.example.naemandong_main;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;

import java.io.IOException;

public class rScene03 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    private AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, turtle, plant, box;
    private ImageButton next;
    private TextView subtitles;
    boolean sound, subtitle;
    private String subs [] = {"그 때 느림보 거북이가 엉금엉금 기어왔어요.", "“거북이는 느림보래요, 느림보래요.”","“하하하하하하.”"};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene03, container,false);

        background = view.findViewById(R.id.background);
        plant = view.findViewById(R.id.plant);
        box = view.findViewById(R.id.subtitlebox);
        turtle = view.findViewById(R.id.turtle);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

//        if (getArguments() != null){
//            sound = getArguments().getBoolean("sound");
//            subtitle = getArguments().getBoolean("subtitle");
//        }

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/3/3_back.jpg")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/3/3_front_tur.png")
                .into(plant);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene03_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene03_2.mp3");
            mp2.prepare();
            mp3.setDataSource("http://49.50.174.179:9000/voice/rScene03_3.mp3");
            mp3.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();
        int c = mp1.getDuration() + mp2.getDuration() + mp3.getDuration();

        turtle.setBackgroundResource(R.drawable.turtle_leftgo);
        frameAnimation = (AnimationDrawable) turtle.getBackground();

        Animation tgo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene03);

//        if (subtitle) {
//            box.setVisibility(View.VISIBLE);
//            subtitles.setVisibility(View.VISIBLE);
//        }
//        else {
//            box.setVisibility(View.INVISIBLE);
//            subtitles.setVisibility(View.INVISIBLE);
//        }

        frameAnimation.start();
        turtle.startAnimation(tgo);
        subtitles.setText(subs[0]);
        mp1.start();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
                mp2.start();
            }
        }, a);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[2]);
                mp3.start();
            }
        }, b);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, c);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rScene04 rscene04 = new rScene04();
                transaction.replace(R.id.frame,rscene04);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
