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

public class rScene70 extends Fragment {

    private AnimationDrawable frameLion;
    private View view;
    private ImageView background, box, lion, front, front2;
    private TextView subtitles;
    private String subs [] = {"그런데 갑자기 사자는 배가 아프기 시작했어요.", "“윽, 갑자기 배가 왜 이렇게 아프지? 거북이가 오기 전에 얼른 싸야지.”"};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene69, container,false);

        background = view.findViewById(R.id.background);
//        front = view.findViewById(R.id.front);
        front2 = view.findViewById(R.id.front2);
        box = view.findViewById(R.id.subtitlebox);
        lion = view.findViewById(R.id.lion);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/82_fin1.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/79_ffront.png")
                .into(front2);




        subtitles.setText(subs[0]);

        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                Glide.with(getActivity())
                        .load("http://49.50.174.179:9000/images/rabbit/7/82_fin2.png")
                        .into(background);
            }
        }, 2000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                Glide.with(getActivity())
                        .load("http://49.50.174.179:9000/images/rabbit/5/10_back.png")
                        .into(background);
                lion.setBackgroundResource(R.drawable.lion_ddonggo);
                frameLion = (AnimationDrawable) lion.getBackground();
                frameLion.start();
                Animation liongo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene70_lion);
                lion.startAnimation(liongo);
                subtitles.setText(subs[1]);
            }
        }, 4000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 10000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rScene29 rscene29 = new rScene29();
                transaction.replace(R.id.frame,rscene29);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
