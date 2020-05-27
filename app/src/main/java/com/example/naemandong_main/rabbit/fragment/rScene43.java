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
import com.example.naemandong_main.rabbit.activity.Rabbit15;
import com.example.naemandong_main.rabbit.fragment.rFinal03;

import java.util.ArrayList;

public class rScene43 extends Fragment {

    private AnimationDrawable frameTurtle, frameRabbit;
    private View view;
    private ImageView background, box, rabbit, bike_turtle, bush1, bush2;
    private TextView subtitles;
    private String subs [] = {"놀란 토끼는 바로 일어나 거북이를 쫓아가기 시작했어요.", "“거북이가 벌써 저기까지 갔잖아? 거북아 기다려라~!!”", "하지만 토끼는 거북이를 따라잡지 못했어요."};
    private ImageButton next;
    private ArrayList<Integer> myList;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene43, container,false);

        background = view.findViewById(R.id.background);
        bush1 = view.findViewById(R.id.bush1);
        bush2 = view.findViewById(R.id.bush2);
        rabbit = view.findViewById(R.id.rabbit);
        bike_turtle = view.findViewById(R.id.bike_turtle);
        box = view.findViewById(R.id.subtitlebox);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/48_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/48_left.png")
                .into(bush1);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/48_right.png")
                .into(bush2);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/48_bike_tur.png")
                .into(bike_turtle);

        rabbit.setBackgroundResource(R.drawable.rabbit_backgo);
        frameRabbit = (AnimationDrawable) rabbit.getBackground();

        Animation rabbitgo = AnimationUtils.loadAnimation(getActivity(), R.anim.rabbit_backgo);

        frameRabbit.start();
        rabbit.startAnimation(rabbitgo);

        myList = (ArrayList<Integer>) ((Rabbit15)getActivity()).getMylist().clone();
        ((Rabbit15)getActivity()).clearList();

        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);

                bike_turtle.setBackgroundResource(R.drawable.bike_tur_small);
                frameRabbit.stop();

                Animation turtlego = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene42);
                bike_turtle.startAnimation(turtlego);
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
                Bundle bundle = new Bundle();
                if (((Rabbit15)getActivity()).play){
                    bundle.putBoolean("play",true);
                }
                else {
                    bundle.putIntegerArrayList("myList", myList);
                }
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rFinal03 rfinal03 = new rFinal03();
                rfinal03.setArguments(bundle);
                transaction.replace(R.id.frame,rfinal03);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
