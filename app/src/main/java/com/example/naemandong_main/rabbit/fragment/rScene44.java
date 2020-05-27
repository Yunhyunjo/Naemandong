package com.example.naemandong_main.rabbit.fragment;

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

public class rScene44 extends Fragment {

    private View view;
    private ImageView background, box, bush, turtle, car_turtle, car;
    private TextView subtitles;
    private String subs [] = {"“여기 자동차가 있네?”", "자동차를 찾은 거북이는 자동차에 올라탔어요.", "“한번 가볼까!”"};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene44, container,false);

        background = view.findViewById(R.id.background);
        bush = view.findViewById(R.id.bush);
        box = view.findViewById(R.id.subtitlebox);
        turtle = view.findViewById(R.id.turtle);
        car_turtle = view.findViewById(R.id.car_turtle);
        car = view.findViewById(R.id.car);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/51_back.png")
                .into(background);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/51_tur.png")
                .into(turtle);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/51_front.png")
                .into(bush);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/51_car.png")
                .into(car);

        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                turtle.setImageResource(0);
                car.setImageResource(0);

                subtitles.setText(subs[1]);
            }
        }, 5000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                car_turtle.setBackgroundResource(R.drawable.t_car);

                Animation turtlego = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene42);
                car_turtle.startAnimation(turtlego);

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
                rScene45 rscene45 = new rScene45();
                transaction.replace(R.id.frame,rscene45);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
