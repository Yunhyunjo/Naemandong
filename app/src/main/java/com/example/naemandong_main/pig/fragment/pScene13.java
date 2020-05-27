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
import com.example.naemandong_main.pig.activity.Pig03;
import com.example.naemandong_main.pig.activity.Pig04;
import com.example.naemandong_main.pig.activity.Pig05;

import java.io.IOException;

public class pScene13 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, wolf;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"\"아잇 너무 튼튼하잖아? 뭐 좋은 방법 없을까?\"", "주위를 두리번거리던 늑대는 상자 두 개를 발견했어요." };
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene13, container,false);

        background = view.findViewById(R.id.background);
        wolf = view.findViewById(R.id.wolf);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/12_BG-01.png")
                .into(background);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/pig/pScene13_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/pig/pScene13_2.mp3");
            mp2.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();

        wolf.setBackgroundResource(R.drawable.wolf_s13_1);
        frameAnimation = (AnimationDrawable) wolf.getBackground();

        frameAnimation.start();
        subtitles.setText(subs[0]);
        mp1.start();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
                wolf.setBackgroundResource(R.drawable.wolf_s13_2);
                frameAnimation = (AnimationDrawable) wolf.getBackground();
                frameAnimation.start();
                Animation wolfgo = AnimationUtils.loadAnimation(getActivity(), R.anim.pscene13);
                wolf.startAnimation(wolfgo);
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
                if (((Pig03)getActivity()).play){
                    if(((Pig03)getActivity()).getData() == 0){
                        ((Pig03)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig04.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else {
                        ((Pig03)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig05.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                else{
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    pScene14 pscene14 = new pScene14();
                    transaction.replace(R.id.frame, pscene14);
                    transaction.commit();  //저장
                }
            }
        });

        return view;
    }
}
