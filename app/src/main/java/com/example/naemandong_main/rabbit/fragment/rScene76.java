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
import com.example.naemandong_main.rabbit.activity.Rabbit15;
import com.example.naemandong_main.rabbit.activity.Rabbit28;
import com.example.naemandong_main.rabbit.activity.Rabbit29;

import java.io.IOException;
import java.util.ArrayList;

public class rScene76 extends Fragment {

    private AnimationDrawable frameLion;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    boolean sound, subtitle;
    private View view;
    private ImageView background, box, lion, turtle, front, front2, effect;
    private TextView subtitles;
    private String subs[] = {"그런데 갑자기 거북이가 타고 가던 자동차가 고장이 나고 말았어요.", "“뭐야? 이게 왜 이러지.. 자동차가 이상해!”", "“흥, 나 먼저 간다 거북아!”"};
    private ImageButton next;
    private ArrayList<Integer> myList;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene76, container, false);

        background = view.findViewById(R.id.background);
        front = view.findViewById(R.id.front);
        front2 = view.findViewById(R.id.front2);
        effect = view.findViewById(R.id.effect);
        box = view.findViewById(R.id.subtitlebox);
        lion = view.findViewById(R.id.lion);
        turtle = view.findViewById(R.id.turtle);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/86_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/86_right.png")
                .into(front2);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/86_left.png")
                .into(front);

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
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene76_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene76_2.MP3");
            mp2.prepare();
            mp3.setDataSource("http://49.50.174.179:9000/voice/rScene76_3.MP3");
            mp3.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (getArguments() != null){
            sound = getArguments().getBoolean("sound");
            subtitle = getArguments().getBoolean("subtitle");
        }


        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();
        int c = mp1.getDuration() + mp2.getDuration()+ mp3.getDuration();

        turtle.setBackgroundResource(R.drawable.driving_turtle);

        lion.setBackgroundResource(R.drawable.lion_backgo);
        frameLion = (AnimationDrawable) lion.getBackground();

        Animation turtlego = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene76);
        turtle.startAnimation(turtlego);


        frameLion.start();

        subtitles.setText(subs[0]);

        myList = (ArrayList<Integer>) ((Rabbit29) getActivity()).getMylist().clone();
        ((Rabbit29) getActivity()).clearList();

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
                Glide.with(getActivity())
                        .load("http://49.50.174.179:9000/images/rabbit/7/115_broken.png")
                        .into(effect);
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
                lion.setBackgroundResource(R.drawable.lion_rightgo67);
                frameLion = (AnimationDrawable) lion.getBackground();
                frameLion.start();

                Animation liongo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene76_2);
                lion.startAnimation(liongo);

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
                if(((Setting_data)getContext().getApplicationContext()).isRecord()){
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
                if (((Rabbit29) getActivity()).play) {
                    bundle.putBoolean("play", true);
                } else {
                    bundle.putIntegerArrayList("myList", myList);
                }
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rFinal10 rfinal10 = new rFinal10();
                rfinal10.setArguments(bundle);
                transaction.replace(R.id.frame, rfinal10);
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
        if (recordmp != null) recordmp.release();
    }
}
