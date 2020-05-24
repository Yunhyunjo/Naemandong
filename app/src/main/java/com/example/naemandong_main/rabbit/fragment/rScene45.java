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
import com.example.naemandong_main.rabbit.activity.Rabbit16;
import com.example.naemandong_main.rabbit.fragment.rFinal05;

import java.util.ArrayList;

public class rScene45 extends Fragment {

    private AnimationDrawable frameAnimation1;
    private View view;
    private ImageView background, box, car_turtle, bush;
    private TextView subtitles;
    private String subs [] = {"차에 탄 거북이는 빠른 속도에 신이 났어요.", "거북이 “우와아 내가 이렇게 빨리 갈 수 있다니!”", "신이 난 거북이는 경주도 잊고 계속 자동차를 운전했어요."};
    private ImageButton next;
    private ArrayList<Integer> myList;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene45, container,false);

        background = view.findViewById(R.id.background);
        car_turtle = view.findViewById(R.id.car_turtle);
        bush = view.findViewById(R.id.bush);
        box = view.findViewById(R.id.subtitlebox);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/52_back.png")
                .into(background);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/52_front.png")
                .into(bush);

        myList = (ArrayList<Integer>) ((Rabbit16)getActivity()).getMylist().clone();
        ((Rabbit16)getActivity()).clearList();

        car_turtle.setBackgroundResource(R.drawable.car);
        Animation turtlego = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene42);
        car_turtle.startAnimation(turtlego);

        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
            }
        }, 2000);
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
                    Bundle bundle = new Bundle();
                    if (((Rabbit16)getActivity()).play){
                        bundle.putBoolean("play",true);
                    }
                    else {
                        bundle.putIntegerArrayList("myList", myList);
                    }
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    rFinal05 rfinal05 = new rFinal05();
                    rfinal05.setArguments(bundle);
                    transaction.replace(R.id.frame,rfinal05);
                    transaction.commit();  //저장
                }
        });

        return view;
    }
}
