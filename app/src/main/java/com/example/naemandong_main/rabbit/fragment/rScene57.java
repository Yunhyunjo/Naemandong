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

import java.io.IOException;

public class rScene57 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    boolean sound, subtitle, t;
    private AnimationDrawable frameAnimation1;
    private View view;
    private ImageView background, box, front, sloth;
    private TextView subtitles;
    private String subs [] = {"“ㄴ..ㅏ아는… 괜차……나…….”", "나무늘보는 물약을 찾지 않고 경주를 계속 하기로 했어요."};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene57, container,false);

        background = view.findViewById(R.id.background);
        front = view.findViewById(R.id.front);
        box = view.findViewById(R.id.subtitlebox);
        sloth = view.findViewById(R.id.sloth);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        if(!((Setting_data) getContext().getApplicationContext()).isRecord() && !((Setting_data) getContext().getApplicationContext()).isRecordPlay()){
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
        }
        else{

        }

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/66_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/66_front.png")
                .into(front);

        if(((Setting_data) getContext().getApplicationContext()).isRecordPlay()){
            String path = ((Setting_data) getContext().getApplicationContext()).getRecordone();
            ((Setting_data) getContext().getApplicationContext()).removeRecordData();
            box.setVisibility(View.INVISIBLE);
            subtitles.setVisibility(View.INVISIBLE);
            try {
                recordmp.setDataSource(path);
                recordmp.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        sloth.setBackgroundResource(R.drawable.sloth_rightgo);
        frameAnimation1 = (AnimationDrawable) sloth.getBackground();

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene57_1.MP3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene57_2.mp3");
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

        final Animation slothgo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene50_sloth);

        frameAnimation1.start();
        sloth.startAnimation(slothgo);
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
                if (((Setting_data) getContext().getApplicationContext()).isRecord()) {
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
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rScene58 rscene58 = new rScene58();
                transaction.replace(R.id.frame, rscene58);
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
        if (recordmp != null) recordmp.release();
    }
}
