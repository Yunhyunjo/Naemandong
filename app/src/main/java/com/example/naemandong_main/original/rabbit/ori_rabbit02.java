package com.example.naemandong_main.original.rabbit;

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
import com.example.naemandong_main.rabbit.fragment.rScene02;

public class ori_rabbit02 extends Fragment {

    private View view;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    private AnimationDrawable frameRabbit;
    private ImageView background, box, rabbit, turtle;
    private TextView subtitles;
    boolean sound, subtitle;
    private String subs [] = {"“이 느림보 거북아! 느림보래요 느림보래요!”", "토끼가 놀려 대자 거북이는 화가났어요.","“그럼 너 나랑 달리기 경주할래?”"};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ori_rabbit02, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        rabbit = view.findViewById(R.id.rabbit);
        turtle = view.findViewById(R.id.turtle);
        subtitles = view.findViewById(R.id.subTitle);

//        try {
//            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene01_1.mp3");
//            mp1.prepare();
//            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene01_2.mp3");
//            mp2.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();

        if (getArguments() != null){
            sound = getArguments().getBoolean("sound");
            subtitle = getArguments().getBoolean("subtitle");
        }

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/original/1_back.jpg")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/original/1_tur.png")
                .into(turtle);

        rabbit.setBackgroundResource(R.drawable.rabbit_ori02);
        frameRabbit = (AnimationDrawable) rabbit.getBackground();
        final Animation angry = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene04);

//        if (subtitle) {
//            box.setVisibility(View.VISIBLE);
//            subtitles.setVisibility(View.VISIBLE);
//        }
//        else {
//            box.setVisibility(View.INVISIBLE);
//            subtitles.setVisibility(View.INVISIBLE);
//        }

        subtitles.setText(subs[0]);
        //mp1.start();
        frameRabbit.start();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                turtle.setImageResource(0);
                turtle.setBackgroundResource(R.drawable.angry_tur);
                subtitles.setText(subs[1]);
                turtle.startAnimation(angry);
                //mp2.start();
            }
        }, 3000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[2]);
            }
        }, 6000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ori_rabbit03 ori_rabbit03 = new ori_rabbit03();
                transaction.replace(R.id.frame, ori_rabbit03);
                transaction.commit();  //저장
            }
        }, 9500);

        return view;
    }

}
