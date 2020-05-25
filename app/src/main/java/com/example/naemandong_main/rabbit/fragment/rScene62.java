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
import com.example.naemandong_main.rabbit.activity.Rabbit24;

import java.io.IOException;
import java.util.ArrayList;

public class rScene62 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    private AnimationDrawable framerabbit;
    private View view;
    private ImageView background, box, sloth, front;
    private TextView subtitles;
    private String subs [] = {"나무늘보는 왼쪽 길로 가기로 결정했어요.","풀 숲을 열심히 헤치고 가자 나무늘보의 눈앞에 결승점이 보이기 시작했어요."};
    private ImageButton next;
    private ArrayList<Integer> myList;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene13, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        sloth = view.findViewById(R.id.rabbit);
        front = view.findViewById(R.id.front);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        myList = (ArrayList<Integer>) ((Rabbit24)getActivity()).getMylist().clone();
        ((Rabbit24)getActivity()).clearList();


        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/13_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/19_grass.png")
                .into(front);

//        try {
//            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene13_1.mp3");
//            mp1.prepare();
//            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene13_2.mp3");
//            mp2.prepare();
//            mp3.setDataSource("http://49.50.174.179:9000/voice/rScene13_3.mp3");
//            mp3.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();
        int c = mp1.getDuration() + mp2.getDuration() + mp3.getDuration();

        subtitles.setText(subs[0]);
        mp1.start();

        sloth.setBackgroundResource(R.drawable.sloth_62);
        framerabbit = (AnimationDrawable) sloth.getBackground();

        final Animation rabbitgo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene62);

        framerabbit.start();
        sloth.startAnimation(rabbitgo);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
                //mp2.start();
            }
        }, 4000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 9000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                if (((Rabbit24)getActivity()).play){
                    bundle.putBoolean("play",true);
                }
                else {
                    bundle.putIntegerArrayList("myList", myList);
                }
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rFinal06 rfinal06 = new rFinal06();
                rfinal06.setArguments(bundle);
                transaction.replace(R.id.frame,rfinal06);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
