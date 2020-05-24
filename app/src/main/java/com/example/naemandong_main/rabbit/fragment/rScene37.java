package com.example.naemandong_main.rabbit.fragment;

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

public class rScene37 extends Fragment {

    private AnimationDrawable frameAnimation1;
    private View view;
    private ImageView background, box, rabbit;
    private TextView subtitles;
    private String subs [] = {"토끼 “아 맞다 경주 중이었지!! 너무 오래 잠들었잖아?”","깜짝 놀라 잠에서 깬 토끼는 꺠워준 거북이를 못보고 달려갔어요.", "거북이 “ㅌ..토끼야….!”"};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene37, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        rabbit = view.findViewById(R.id.rabbit);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/40_back.png")
                .into(background);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/40_front.png")
                .into(rabbit);

        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
                rabbit.setImageResource(0);
                rabbit.setBackgroundResource(R.drawable.rabbit_sleep_rightgo);
                frameAnimation1 = (AnimationDrawable) rabbit.getBackground();

                Animation rabbitgo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene37);

                frameAnimation1.start();
                rabbit.startAnimation(rabbitgo);
            }
        }, 5000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[2]);
            }
        }, 5000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 8300);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rScene38 rscene38 = new rScene38();
                transaction.replace(R.id.frame,rscene38);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
