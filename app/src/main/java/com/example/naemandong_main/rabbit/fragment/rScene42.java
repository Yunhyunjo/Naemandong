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

public class rScene42 extends Fragment {

    private View view;
    private ImageView background, box, bush, rabbit, turtle, bulb, bike_turtle, rabbit_bed, bike;
    private TextView subtitles;
    private String subs [] = {"거북이“여기 오토바이가 있네?”", "거북이는 오토바이를 타고 달리기 시작했어요.", "부아아아앙!!", " 토끼“이게 무슨 소리지?”"};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene42, container,false);

        background = view.findViewById(R.id.background);
        bush = view.findViewById(R.id.bush);
        box = view.findViewById(R.id.subtitlebox);
        rabbit_bed = view.findViewById(R.id.rabbit_bed);
        bike = view.findViewById(R.id.bike);
        bike_turtle = view.findViewById(R.id.bike_turtle);
        rabbit = view.findViewById(R.id.rabbit);
        bulb = view.findViewById(R.id.bulb);
        turtle = view.findViewById(R.id.turtle);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/47_back.png")
                .into(background);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/47_bike.png")
                .into(bike);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/47_aa.png")
                .into(bulb);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/35_sleep.png")
                .into(rabbit_bed);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/47_front_tur.png")
                .into(turtle);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/47_front.png")
                .into(bush);

        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                turtle.setImageResource(0);
                bike.setImageResource(0);
                bulb.setImageResource(0);

                subtitles.setText(subs[1]);
            }
        }, 5000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                bike_turtle.setBackgroundResource(R.drawable.bike_tur_large);

                Animation turtlego = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene42);
                bike_turtle.startAnimation(turtlego);

                subtitles.setText(subs[2]);
            }
        }, 5000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[3]);

                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/rabbit/5/47_front_rabb.png")
                        .into(rabbit);

                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/rabbit/5/47_bed.png")
                        .into(rabbit_bed);
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
                rScene43 rscene43 = new rScene43();
                transaction.replace(R.id.frame,rscene43);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
