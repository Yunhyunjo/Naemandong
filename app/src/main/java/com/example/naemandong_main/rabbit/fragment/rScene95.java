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

public class rScene95 extends Fragment {

    private AnimationDrawable frameLion;
    private View view;
    private ImageView background, box, lion, front;
    private TextView subtitles;
    private String subs[] = {"“그래 일단 경주를 마저 다 해야지!”", "사자는 자지 않고 경주를 계속 이어나가기로 결정했어요."};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene95, container, false);

        background = view.findViewById(R.id.background);
        front = view.findViewById(R.id.front);
        box = view.findViewById(R.id.subtitlebox);
        lion = view.findViewById(R.id.turtle);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/66_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/79_fffront.png")
                .into(front);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/109_lion_front.png")
                .into(lion);


        subtitles.setText(subs[0]);


        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO

                Glide.with(getActivity())
                        .load(0)
                        .into(lion);

                lion.setBackgroundResource(R.drawable.lion_rightgo);
                frameLion = (AnimationDrawable) lion.getBackground();

                Animation liongo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene66_lion);
                lion.startAnimation(liongo);

                frameLion.start();
                subtitles.setText(subs[1]);
            }
        }, 3000);
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
                rScene96 rscene96 = new rScene96();
                transaction.replace(R.id.frame, rscene96);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}