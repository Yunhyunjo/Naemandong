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
import com.example.naemandong_main.rabbit.activity.Rabbit16;
import com.example.naemandong_main.rabbit.fragment.rFinal05;

import java.io.IOException;
import java.util.ArrayList;

public class rScene45 extends Fragment {

    private AnimationDrawable frameAnimation1;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    private View view;
    private ImageView background, box, car_turtle, bush;
    private TextView subtitles;
    private String subs [] = {"차에 탄 거북이는 빠른 속도에 신이 났어요.", "“우와아 내가 이렇게 빨리 갈 수 있다니!”", "신이 난 거북이는 경주도 잊고 계속 자동차를 운전했어요."};
    private ImageButton next;
    private ArrayList<Integer> myList;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene45, container,false);

        background = view.findViewById(R.id.background);
        car_turtle = view.findViewById(R.id.car_turtle);
        bush = view.findViewById(R.id.bush);
        box = view.findViewById(R.id.subtitlebox);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/52_back.png")
                .into(background);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/52_front.png")
                .into(bush);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene45_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene45_2.MP3");
            mp2.prepare();
            mp3.setDataSource("http://49.50.174.179:9000/voice/rScene45_3.mp3");
            mp3.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();
        int c = mp1.getDuration() + mp2.getDuration() + mp3.getDuration();

        myList = (ArrayList<Integer>) ((Rabbit16)getActivity()).getMylist().clone();
        ((Rabbit16)getActivity()).clearList();

        car_turtle.setBackgroundResource(R.drawable.car);
        Animation turtlego = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene42);
        car_turtle.startAnimation(turtlego);

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
                    Bundle bundle = new Bundle();
                    if (((Rabbit16)getActivity()).play){
                        bundle.putBoolean("play",true);
                    } else if (((Setting_data) getContext().getApplicationContext()).isRecord()) {
                        ((Setting_data) getContext().getApplicationContext()).setRecord(false);
                    } else {
                        bundle.putIntegerArrayList("myList", myList);
                    }
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    rFinal05 rfinal05 = new rFinal05();
                    rfinal05.setArguments(bundle);
                    transaction.replace(R.id.frame,rfinal05);
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
