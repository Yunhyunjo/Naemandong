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

public class rScene93 extends Fragment {

    private AnimationDrawable frameAnimation1;
    private View view;
    private ImageView background, box, turtle,lion, lion2, front, bike, light;
    private TextView subtitles;
    private String subs [] = {"\"거북이는 2인 자전거를 발견했어요.\"", "\"어? 이건 둘이서만 탈 수 있는 자전거잖아? 꼭 타보고 싶었는데.. 사자랑 같이 탈까?\""};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene93, container,false);

        background = view.findViewById(R.id.background);
        front = view.findViewById(R.id.front);
        box = view.findViewById(R.id.subtitlebox);
        turtle = view.findViewById(R.id.turtle);
        light = view.findViewById(R.id.light);
        lion = view.findViewById(R.id.lion);
        lion2 = view.findViewById(R.id.lion2);
        bike = view.findViewById(R.id.skate);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/8_back.jpg")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/8_front_rabbit.png")
                .into(front);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/90_2.png")
                .into(lion2);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/106_tur.png")
                .into(turtle);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/106_find.png")
                .into(light);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/106_cy.png")
                .into(bike);

        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO

                subtitles.setText(subs[1]);
            }
        }, 4000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
            }
        }, 6000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
            }
        }, 9000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 13000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    rScene94 rscene94 = new rScene94();
                    transaction.replace(R.id.frame,rscene94);
                    transaction.commit();  //저장
            }
        });

        return view;
    }
}
