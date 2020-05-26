package com.example.naemandong_main.original.rabbit;


import android.graphics.drawable.AnimationDrawable;
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

public class ori_rabbit05 extends Fragment {

    private View view;
    private AnimationDrawable frameAnimation1;
    private ImageView background, box,front, rabbit, rabbit2;
    private TextView subtitles;
    private String subs [] = {"토끼 “거북이가 오려면 멀었겠지? 잠깐 자고 갈까?”", "토끼는 거북이가 올 때까지 낮잠을 자기로 했어요."};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ori_rabbit05, container,false);

        background = view.findViewById(R.id.background);
        front = view.findViewById(R.id.front);
        box = view.findViewById(R.id.subtitlebox);
        rabbit = view.findViewById(R.id.rabbit);
        rabbit2 = view.findViewById(R.id.rabbit2);
        subtitles = view.findViewById(R.id.subTitle);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/original/5_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/8_front_rabbit.png")
                .into(front);

//        try {
//            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene08_1.mp3");
//            mp1.prepare();
//            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene08_2.mp3");
//            mp2.prepare();
//            mp3.setDataSource("http://49.50.174.179:9000/voice/rScene08_3.mp3");
//            mp3.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        int a = mp1.getDuration();
//        int b = mp1.getDuration() + mp2.getDuration();
//        int c = mp1.getDuration() + mp2.getDuration() + mp3.getDuration();

        rabbit.setBackgroundResource(R.drawable.rabbit_rightgos08);
        frameAnimation1 = (AnimationDrawable) rabbit.getBackground();

        Animation rabbitgo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene08);

        frameAnimation1.start();
        rabbit.startAnimation(rabbitgo);
        subtitles.setText(subs[0]);
        //mp1.start();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                rabbit.setBackgroundResource(0);
                Glide.with(getActivity())
                        .load("http://49.50.174.179:9000/images/rabbit/5/8_run_r05.png")
                        .into(rabbit);

            }
        }, 2000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                rabbit.setImageResource(0);
                Glide.with(getActivity())
                        .load("http://49.50.174.179:9000/images/rabbit/original/5_sleep.png")
                        .into(rabbit2);
                subtitles.setText(subs[1]);
                //mp2.start();
            }
        }, 5000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                //mp3.start();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ori_rabbit06 ori_rabbit06 = new ori_rabbit06();
                transaction.replace(R.id.frame, ori_rabbit06);
                transaction.commit();  //저장
            }
        }, 10000);

        return view;
    }
}
