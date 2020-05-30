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
import com.example.naemandong_main.rabbit.activity.Rabbit02;
import com.example.naemandong_main.rabbit.activity.Rabbit03;
import com.example.naemandong_main.rabbit.activity.Rabbit10;
import com.example.naemandong_main.rabbit.activity.Rabbit26;
import com.example.naemandong_main.rabbit.activity.Rabbit27;
import com.example.naemandong_main.rabbit.activity.Rabbit30;

import java.io.IOException;

public class rScene67 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    private AnimationDrawable frameAnimation1;
    private View view;
    private ImageView background, box,front, lion;
    private TextView subtitles;
    private String subs [] = {"한참을 달려나가던 사자는 너무 배가 고파서 잠시 멈추었어요.", "\"어휴 배고파. 뭐 먹을 것 좀 없나?\""};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene67, container,false);

        background = view.findViewById(R.id.background);
        front = view.findViewById(R.id.front);
        box = view.findViewById(R.id.subtitlebox);
        lion = view.findViewById(R.id.lion);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/79_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/8_front_rabbit.png")
                .into(front);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene67_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene67_2.MP3");
            mp2.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();

        lion.setBackgroundResource(R.drawable.lion_rightgo67);
        frameAnimation1 = (AnimationDrawable) lion.getBackground();

        Animation liongo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene08);

        frameAnimation1.start();
        lion.startAnimation(liongo);
        subtitles.setText(subs[0]);
        mp1.start();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                lion.setBackgroundResource(0);
                Glide.with(getActivity())
                        .load("http://49.50.174.179:9000/images/rabbit/7/97_front.png")
                        .into(lion);

            }
        }, 2000);
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
                if(((Setting_data)getContext().getApplicationContext()).isRecord()){
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
                if (((Rabbit26)getActivity()).play){
                    if(((Rabbit26)getActivity()).getData() == 0){
                        ((Rabbit26)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit27.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else {
                        ((Rabbit26)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit30.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                else {
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    rScene68 rscene68 = new rScene68();
                    transaction.replace(R.id.frame, rscene68);
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
