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
import com.example.naemandong_main.rabbit.activity.Rabbit01;
import com.example.naemandong_main.rabbit.activity.Rabbit02;
import com.example.naemandong_main.rabbit.activity.Rabbit17;
import com.example.naemandong_main.rabbit.activity.Rabbit26;

import java.io.IOException;

public class rScene04 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    boolean sound, subtitle, t;
    private View view;
    private ImageView background, turtle, box;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"동물들이 깔깔 웃으며 놀려대자 거북이는 화가 났어요.", "“그럼 너네 나하고 달리기 경주할래?”"};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene04, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
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
                .load("http://49.50.174.179:9000/images/rabbit/4/4_back.jpg")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/4/4_angry_tur.png")
                .into(turtle);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene04_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene04_2.MP3");
            mp2.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();

        if (getArguments() != null){
            sound = getArguments().getBoolean("sound");
            subtitle = getArguments().getBoolean("subtitle");
        }

        Animation angry = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene04);

        turtle.startAnimation(angry);
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
            }
        }, a);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                if ((((Setting_data) getContext().getApplicationContext()).getSubtitle() == true)&&(((Setting_data) getContext().getApplicationContext()).isRecord()==false)) {
                    subtitles.setVisibility(View.INVISIBLE);
                    box.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(getActivity(), Record.class);
                    startActivity(intent);
                }
                next.setVisibility(View.VISIBLE);
                t = true;
            }
        }, b);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((Rabbit01)getActivity()).play){
                    if(((Rabbit01)getActivity()).getData() == 0){
                        ((Rabbit01)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit02.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else if(((Rabbit01)getActivity()).getData() == 1){
                        ((Rabbit01)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit26.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else {
                        ((Rabbit01)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit17.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                else{
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    rScene05 rscene05 = new rScene05();
                    transaction.replace(R.id.frame,rscene05);
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
        if (recordmp != null) recordmp.release();
    }
}
