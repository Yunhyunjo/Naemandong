package com.example.naemandong_main.rabbit.fragment;

import android.content.Intent;
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

import java.io.IOException;

public class rScene42 extends Fragment {

    private View view;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    MediaPlayer mp4 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    boolean sound, subtitle, t;
    private ImageView background, box, bush, rabbit, turtle, bulb, bike_turtle, rabbit_bed, bike;
    private TextView subtitles;
    private String subs [] = {"“여기 오토바이가 있네?”", "거북이는 오토바이를 타고 달리기 시작했어요.", "부아아아앙!!", " “이게 무슨 소리지?”"};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene42, container,false);

        background = view.findViewById(R.id.background);
        bush = view.findViewById(R.id.bush);
        box = view.findViewById(R.id.subtitlebox);
        rabbit_bed = view.findViewById(R.id.rabbit_bed);
        bike = view.findViewById(R.id.bike);
        bike_turtle = view.findViewById(R.id.bike_turtle);
        rabbit = view.findViewById(R.id.rabbit);
        bulb = view.findViewById(R.id.bulb);
        turtle = view.findViewById(R.id.turtle);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        (new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    try {
                        Thread.sleep(1000); //1초 간격으로 실행
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (((Setting_data) getContext().getApplicationContext()).getSubtitle() == true) {
                                    subtitles.setVisibility(View.VISIBLE);
                                    box.setVisibility(View.VISIBLE);
                                } else {
                                    subtitles.setVisibility(View.INVISIBLE);
                                    box.setVisibility(View.INVISIBLE);
                                }

                            }
                        });
                    } catch (InterruptedException e) {
                        // error
                    }
                    if (t)
                        break;
                }

            }
        })).start();

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/47_back.png")
                .into(background);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/47_bike.png")
                .into(bike);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/47_aa.png")
                .into(bulb);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/35_sleep.png")
                .into(rabbit_bed);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/47_front_tur.png")
                .into(turtle);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/47_front.png")
                .into(bush);

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
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene42_1.MP3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene42_2.mp3");
            mp2.prepare();
            mp3.setDataSource("http://49.50.174.179:9000/voice/rScene42_3.mp3");
            mp3.prepare();
            mp4.setDataSource("http://49.50.174.179:9000/voice/rScene42_4.mp3");
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
                turtle.setImageResource(0);
                bike.setImageResource(0);
                bulb.setImageResource(0);
                bike_turtle.setBackgroundResource(R.drawable.bike_tur_large);

                subtitles.setText(subs[1]);
                if(!((Setting_data) getContext().getApplicationContext()).isRecordPlay()){
                    mp2.start();
                }
            }
        }, a);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO

                Animation turtlego = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene42);
                bike_turtle.startAnimation(turtlego);

                subtitles.setText(subs[2]);

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

                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/rabbit/5/47_front_rabb.png")
                        .into(rabbit);

                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/rabbit/5/47_bed.png")
                        .into(rabbit_bed);
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
                t = true;
            }
        }, d);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rScene43 rscene43 = new rScene43();
                transaction.replace(R.id.frame,rscene43);
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
        if (mp4 != null) mp4.release();
        if (recordmp != null) recordmp.release();
    }
}
