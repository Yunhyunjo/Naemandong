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
import com.example.naemandong_main.rabbit.activity.Rabbit08;

import java.io.IOException;
import java.util.ArrayList;

public class rScene27 extends Fragment {

    private AnimationDrawable frameRabbit;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    boolean sound, subtitle;
    private View view;
    private ImageView background, box, rabbit;
    private TextView subtitles;
    private String subs [] = {"“아냐아냐! 시합이 끝나고 먹으러 오자!” ","토끼는 먹는걸 포기하고 열심히 달려 산꼭대기에 도착했어요."};
    private ImageButton next;
    private ArrayList<Integer> myList;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene27, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        rabbit = view.findViewById(R.id.rabbit);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/28_back.png")
                .into(background);
        rabbit.setBackgroundResource(0);
        Glide.with(getActivity())
                .load("http://49.50.174.179:9000/images/rabbit/5/28_noneat.png")
                .into(rabbit);

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
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene27_1.MP3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene27_2.mp3");
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

        myList = (ArrayList<Integer>) ((Rabbit08)getActivity()).getMylist().clone();
        ((Rabbit08)getActivity()).clearList();

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
                rabbit.setImageResource(0);
                rabbit.setBackgroundResource(R.drawable.rabbit_rightgo2425 );
                frameRabbit = (AnimationDrawable) rabbit.getBackground();
                Animation rabbitgo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene27);
                rabbit.startAnimation(rabbitgo);
                frameRabbit.start();
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
                Bundle bundle = new Bundle();
                if (((Rabbit08)getActivity()).play){
                    bundle.putBoolean("play",true);
                } else if (((Setting_data) getContext().getApplicationContext()).isRecord()) {
                    ((Setting_data) getContext().getApplicationContext()).setRecord(false);
                } else {
                    bundle.putIntegerArrayList("myList", myList);
                }
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rFinal02 rfinal02 = new rFinal02();
                rfinal02.setArguments(bundle);
                transaction.replace(R.id.frame,rfinal02);
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
