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

public class ori_rabbit06 extends Fragment {

    private View view;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    private AnimationDrawable frameAnimation1;
    private ImageView background, box,front,front2, turtle, rabbit2;
    private TextView subtitles;
    private String subs [] = {"하지만 거북이는 포기하지 않고 엉금엉금 열심히 달렸어요.", "“아휴 힘들어”", "거북이는 쿨쿨 자는 토끼를 지나 산꼭대기로 올라갔어요."};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ori_rabbit06, container,false);

        background = view.findViewById(R.id.background);
        front = view.findViewById(R.id.front);
        front2 = view.findViewById(R.id.front2);
        box = view.findViewById(R.id.subtitlebox);
        turtle = view.findViewById(R.id.turtle);
        rabbit2 = view.findViewById(R.id.rabbit2);
        subtitles = view.findViewById(R.id.subTitle);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/original/5_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/8_front_rabbit.png")
                .into(front);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/original/5_fffront.png")
                .into(front2);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/original/5_sleep.png")
                .into(rabbit2);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/ori_rabbit06_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/ori_rabbit06_2.MP3");
            mp2.prepare();
            mp3.setDataSource("http://49.50.174.179:9000/voice/ori_rabbit06_3.mp3");
            mp3.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();
        int c = mp1.getDuration() + mp2.getDuration() + mp3.getDuration();

        turtle.setBackgroundResource(R.drawable.turtle_rightgo);
        frameAnimation1 = (AnimationDrawable) turtle.getBackground();

        Animation turtlego = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene07_turtle);

        frameAnimation1.start();
        turtle.startAnimation(turtlego);
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
                subtitles.setText(subs[1]);
                mp3.start();
            }
        }, b);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ori_rabbit07 ori_rabbit07 = new ori_rabbit07();
                transaction.replace(R.id.frame, ori_rabbit07);
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
