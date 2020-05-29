package com.example.naemandong_main.rabbit.fragment;

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
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.naemandong_main.R;

public class rScene77 extends Fragment {

    private AnimationDrawable frameLion;
    private View view;
    private ImageView background, box, lion, front, light, bone;
    private TextView subtitles;
    private String subs [] = {"\"어, 여기 고기가 있네? 냠냠 이 고기 정말 맛있는데?\"", "배가 고팠던 사자는 허겁지겁 고기를 먹기 시작했어요."};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene77, container,false);

        background = view.findViewById(R.id.background);
        front = view.findViewById(R.id.front);
        box = view.findViewById(R.id.subtitlebox);
        lion = view.findViewById(R.id.lion);
        bone = view.findViewById(R.id.bone);
        light = view.findViewById(R.id.light);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/10_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/88_aa.png")
                .into(lion);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/23_light.png")
                .into(light);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/87_meat.png")
                .into(bone);


        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                bone.setImageResource(0);
                light.setImageResource(0);
                lion.setImageResource(0);
                lion.setBackgroundResource(R.drawable.lion_eat77);
                frameLion = (AnimationDrawable) lion.getBackground();
                frameLion.start();
            }
        }, 4000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
            }
        }, 6000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 11000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rScene78 rscene78 = new rScene78();
                transaction.replace(R.id.frame,rscene78);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
