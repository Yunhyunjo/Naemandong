package com.example.naemandong_main.original.pig;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.naemandong_main.R;
import com.example.naemandong_main.pig.activity.Pig01;


// 돼지 셋 벽돌집 엔딩
public class ori_pig17 extends Fragment {

    AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, box, pigs, house;
    private TextView subtitles;
    private ImageButton next;

    private String subs [] = {"늑대가 없는 평화로운 마을에서", "아기 돼지 삼형제는 막내 돼지의 집에서 오래 오래 행복하게 살았어요."};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ori_pig17, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        pigs = view.findViewById(R.id.pigs);
        house = view.findViewById(R.id.house);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.nmd);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/19_bg-01.png")
                .into(background);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/28_housebg-01.png")
                .into(house);

        pigs.setBackgroundResource(R.drawable.pig_final07);
        frameAnimation = (AnimationDrawable) pigs.getBackground();

        frameAnimation.start();
        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
            }
        }, 5000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                //mp3.start();
                box.setVisibility(View.INVISIBLE);
                subtitles.setVisibility(View.INVISIBLE);
                next.setVisibility(View.VISIBLE);

            }
        }, 12000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), Pig01.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}
