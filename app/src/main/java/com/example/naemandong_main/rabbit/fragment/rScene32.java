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
import com.example.naemandong_main.rabbit.activity.Rabbit10;
import com.example.naemandong_main.rabbit.activity.Rabbit11;
import com.example.naemandong_main.rabbit.activity.Rabbit14;

import java.io.IOException;

public class rScene32 extends Fragment {

    private AnimationDrawable frameAnimation1;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    boolean sound, subtitle;
    private View view;
    private ImageView background, box, rabbit_sleep, turtle, bush1, bush2;
    private TextView subtitles;
    private String subs [] = {"토끼가 잠든 사이 거북이는 어느새 토끼가 있는 곳에 도착했어요.", "그리고 거북이는 토끼의 자는 모습을 바라보았어요."};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene32, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        rabbit_sleep = view.findViewById(R.id.rabbit_sleep);
        bush1 = view.findViewById(R.id.bush1);
        bush2 = view.findViewById(R.id.bush2);
        turtle = view.findViewById(R.id.turtle);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/35_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/35_sleep.png")
                .into(rabbit_sleep);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/35_01.png")
                .into(bush1);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/35_2.png")
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
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene32_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene32_2.mp3");
            mp2.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (getArguments() != null){
            sound = getArguments().getBoolean("sound");
            subtitle = getArguments().getBoolean("subtitle");
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();

        turtle.setBackgroundResource(R.drawable.turtle_rightgo);
        frameAnimation1 = (AnimationDrawable) turtle.getBackground();

        Animation turtlego = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene32);

        frameAnimation1.start();
        turtle.startAnimation(turtlego);
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
                if(!((Setting_data) getContext().getApplicationContext()).isRecordPlay()){
                    mp2.start();
                }
                turtle.setBackgroundResource(0);
                Glide.with(getActivity())
                        .load("http://49.50.174.179:9000/images/rabbit/5/35_t_front.png")
                        .into(turtle);
            }
        }, a);
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
        }, b);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((Rabbit10)getActivity()).play){
                    if(((Rabbit10)getActivity()).getData() == 0){
                        ((Rabbit10)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit11.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else {
                        ((Rabbit10)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit14.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                else {
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    rScene33 rscene33 = new rScene33();
                    transaction.replace(R.id.frame, rscene33);
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
    }
}
