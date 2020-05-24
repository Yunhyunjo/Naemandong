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
import com.example.naemandong_main.rabbit.activity.Rabbit09;

import java.util.ArrayList;

public class rScene29 extends Fragment {

    private AnimationDrawable frameRabbit;
    private View view;
    private ImageView background, box, rabbit, bbam;
    private TextView subtitles;
    private String subs [] = {"토끼 “아차차! 달리기 경주!”","뒤늦게 토끼가 달려갔지만 거북이는 이미 결승선에 도착했어요."};
    private ImageButton next;
    private ArrayList<Integer> myList;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene29, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        rabbit = view.findViewById(R.id.rabbit);
        bbam = view.findViewById(R.id.bbam);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/30_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/31_aa.png")
                .into(bbam);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/31_eat.png")
                .into(rabbit);

        myList = (ArrayList<Integer>) ((Rabbit09)getActivity()).getMylist().clone();
        ((Rabbit09)getActivity()).clearList();

        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                rabbit.setImageResource(0);
                bbam.setImageResource(0);
                subtitles.setText(subs[1]);
                rabbit.setBackgroundResource(R.drawable.rabbit_rightgo28);
                frameRabbit = (AnimationDrawable) rabbit.getBackground();

                Animation rabbitgo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene27);
                frameRabbit.start();
                rabbit.startAnimation(rabbitgo);
            }
        }, 3000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 9000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                if (((Rabbit09)getActivity()).play){
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
