package com.example.naemandong_main.rabbit.fragment;

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

import java.io.IOException;

public class rScene13 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    private AnimationDrawable framerabbit;
    private View view;
    private ImageView background, box, rabbit, front;
    private TextView subtitles;
    private String subs [] = {"토끼는 왼쪽 길로 가기로 결정했어요.","토끼 “아직 거북이는 오려면 멀었겠지? 역시 너무 느리다니까”", "토끼는 다시 산꼭대기까지 달리기 시작했어요."};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene13, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        rabbit = view.findViewById(R.id.rabbit);
        front = view.findViewById(R.id.front);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/13_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/13_front_r.png")
                .into(rabbit);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/19_grass.png")
                .into(front);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene13_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene13_2.mp3");
            mp2.prepare();
            mp3.setDataSource("http://49.50.174.179:9000/voice/rScene13_3.mp3");
            mp3.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();
        int c = mp1.getDuration() + mp2.getDuration() + mp3.getDuration();

        subtitles.setText(subs[0]);
        mp1.start();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
                mp2.start();
                rabbit.setImageResource(0);

                rabbit.setBackgroundResource(R.drawable.rabbit_leftgo);
                framerabbit = (AnimationDrawable) rabbit.getBackground();

                final Animation rabbitgo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene13);

                framerabbit.start();
                rabbit.startAnimation(rabbitgo);
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
                rScene14 rscene14 = new rScene14();
                transaction.replace(R.id.frame,rscene14);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
