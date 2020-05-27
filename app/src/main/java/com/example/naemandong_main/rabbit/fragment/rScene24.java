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

public class rScene24 extends Fragment {

    private View view;
    private ImageView background, box, rabbit, front;
    private TextView subtitles;
    private String subs [] = {"“오른쪽으로 가야지!”","토끼는 열심히 달리기 시작했어요."};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene24, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        rabbit = view.findViewById(R.id.rabbit);
        front = view.findViewById(R.id.front);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);


        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/25_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/25_front.png")
                .into(rabbit);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/19_grass.png")
                .into(front);

        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                rabbit.setImageResource(0);
                rabbit.setBackgroundResource(R.drawable.rabbit_rightgo2425);
                AnimationDrawable frameRabbit = (AnimationDrawable) rabbit.getBackground();
                Animation rabbitgo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene24);
                frameRabbit.start();
                rabbit.startAnimation(rabbitgo);
                subtitles.setText(subs[1]);
            }
        }, 2500);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 6000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rScene25 rscene25 = new rScene25();
                transaction.replace(R.id.frame,rscene25);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
