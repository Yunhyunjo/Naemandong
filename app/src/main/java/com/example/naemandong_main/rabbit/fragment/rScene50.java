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
import com.example.naemandong_main.rabbit.activity.Rabbit19;
import com.example.naemandong_main.rabbit.activity.Rabbit20;
import com.example.naemandong_main.rabbit.activity.Rabbit23;

public class rScene50 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    MediaPlayer mp4 = new MediaPlayer();
    private AnimationDrawable frameTurtle, frameSloth;
    private View view;
    private ImageView background, box, sloth, turtle;
    private TextView subtitles;
    private String subs [] = {"거북이 \"그래 그냥 한번 경주 해보자\"","거북이는 참고 나무늘보와 경주를 하기로 했어요", "하지만 나무늘보는 거북이보다 느려서 한참 뒤쳐지고 말았어요","나무늘보 \"거……….북….\""};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene07, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        sloth = view.findViewById(R.id.rabbit);
        turtle = view.findViewById(R.id.turtle);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/55_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/62_hwanagizon.png")
                .into(turtle);

//        try {
//            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene07_1.mp3");
//            mp1.prepare();
//            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene07_2.mp3");
//            mp2.prepare();
//            mp3.setDataSource("http://49.50.174.179:9000/voice/rScene07_3.mp3");
//            mp3.prepare();
//            mp4.setDataSource("http://49.50.174.179:9000/voice/rScene07_3.mp3");
//            mp4.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();
        int c = mp1.getDuration() + mp2.getDuration() + mp3.getDuration();
        int d = mp1.getDuration() + mp2.getDuration() + mp3.getDuration() + mp4.getDuration();

        sloth.setBackgroundResource(R.drawable.sloth_rightgo);
        frameSloth = (AnimationDrawable) sloth.getBackground();

        final Animation turtlego = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene50_turtle);
        final Animation slothgo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene50_sloth);

        subtitles.setText(subs[0]);
        //mp1.start();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
                turtle.setImageResource(0);
                turtle.setBackgroundResource(R.drawable.turtle_rightgo);
                frameTurtle = (AnimationDrawable) turtle.getBackground();
                //mp2.start();
            }
        }, 3000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[2]);
                frameTurtle.start();
                frameSloth.start();
                turtle.startAnimation(turtlego);
                sloth.startAnimation(slothgo);
                //mp3.start();
            }
        }, 6000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[3]);
            }
        }, 11000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 14000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((Rabbit19)getActivity()).play){
                    if(((Rabbit19)getActivity()).getData() == 0){
                        ((Rabbit19)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit20.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else{
                        ((Rabbit19)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit23.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                else {
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    rScene51 rscene51 = new rScene51();
                    transaction.replace(R.id.frame, rscene51);
                    transaction.commit();  //저장
                }
            }
        });

        return view;
    }
}
