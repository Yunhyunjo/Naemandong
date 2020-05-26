package com.example.naemandong_main.pig.fragment;

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

public class pScene03 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    private AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, pig;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"첫째 돼지는 지푸라기로 집을 짓기로 결정했어요.", "\"지푸라기로 집을 지으면 얼마나 간단한지~\"" };
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene01, container,false);

        background = view.findViewById(R.id.background);
        pig = view.findViewById(R.id.pig);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/3_bg-01.png")
                .into(background);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/pig/pScene03_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/pig/pScene03_2.mp3");
            mp2.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();

        pig.setBackgroundResource(R.drawable.pig_s3);
        frameAnimation = (AnimationDrawable) pig.getBackground();
        Animation piggo = AnimationUtils.loadAnimation(getActivity(), R.anim.pscene03);

        subtitles.setText(subs[0]);
        frameAnimation.start();
        pig.startAnimation(piggo);
        mp1.start();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                pig.setBackgroundResource(R.drawable.pig_s3_2);
                frameAnimation = (AnimationDrawable) pig.getBackground();
                frameAnimation.start();
                subtitles.setText(subs[1]);
                mp2.start();
            }
        }, a);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, b);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                pScene04 pscene04 = new pScene04();
                transaction.replace(R.id.frame,pscene04);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
