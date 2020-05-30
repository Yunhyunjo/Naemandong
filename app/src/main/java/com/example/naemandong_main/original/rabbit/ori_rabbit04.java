package com.example.naemandong_main.original.rabbit;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.naemandong_main.R;

import java.io.IOException;

public class ori_rabbit04 extends Fragment {

    private View view;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    private AnimationDrawable frameTurtle, frameRabbit;
    private ImageView background, box, rabbit, turtle;
    private TextView subtitles;
    private String subs [] = {"탕 소리와 함께 토끼는 깡총깡총, 거북이는 엉금엉금 달려가기 시작했어요.","“토끼님이 나가신다 길을 비켜라~”", "토끼는 시작과 동시에 거북이보다 훌쩍 앞서기 시작했어요."};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene07, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        rabbit = view.findViewById(R.id.rabbit);
        turtle = view.findViewById(R.id.turtle);
        subtitles = view.findViewById(R.id.subTitle);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/7_back.jpg")
                .into(background);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene07_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene07_2.MP3");
            mp2.prepare();
            mp3.setDataSource("http://49.50.174.179:9000/voice/rScene07_3.mp3");
            mp3.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();
        int c = mp1.getDuration() + mp2.getDuration() + mp3.getDuration();

        turtle.setBackgroundResource(R.drawable.turtle_rightgo);
        frameTurtle = (AnimationDrawable) turtle.getBackground();
        rabbit.setBackgroundResource(R.drawable.rabbit_rightgo);
        frameRabbit = (AnimationDrawable) rabbit.getBackground();

        Animation turtlego = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene07_turtle);
        Animation rabbitgo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene07_rabbit);

        frameTurtle.start();
        turtle.startAnimation(turtlego);
        frameRabbit.start();
        rabbit.startAnimation(rabbitgo);
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
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ori_rabbit05 ori_rabbit05 = new ori_rabbit05();
                transaction.replace(R.id.frame, ori_rabbit05);
                transaction.commit();  //저장
            }
        }, c);
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
