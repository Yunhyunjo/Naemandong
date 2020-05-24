package com.example.naemandong_main.pig.fragment;

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
import com.example.naemandong_main.pig.activity.Pig09;
import com.example.naemandong_main.pig.fragment.pFinal03;

import java.util.ArrayList;

public class pScene37 extends Fragment {

    AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, wolf;
    private ImageButton next;
    private TextView subtitles;
    private ArrayList<Integer> myList;
    private String subs [] = {"막내 돼지는 늑대의 거짓말을 알아챘어요.", "막내 돼지 \"이 나쁜 늑대! 엄마 목소리를 흉내내서 날 잡아먹으려고 하다니! 난 속지 않아!\"","늑대 \"에잇 아까워! 막내 돼지도 잡아먹을 수 있었는데!\""};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene37, container,false);

        background = view.findViewById(R.id.background);
        subtitles = view.findViewById(R.id.subTitle);
        wolf = view.findViewById(R.id.wolf);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/19_example.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/25_wolf1-01.png")
                .into(wolf);

        myList = (ArrayList<Integer>) ((Pig09)getActivity()).getMylist().clone();
        ((Pig09)getActivity()).clearList();

        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
            }
        }, 3100);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[2]);
                wolf.setImageResource(0);

                wolf.setBackgroundResource(R.drawable.wolf_s37);
                frameAnimation = (AnimationDrawable) wolf.getBackground();
                Animation wolfgo = AnimationUtils.loadAnimation(getActivity(),R.anim.pscene37);
                frameAnimation.start();
                wolf.startAnimation(wolfgo);
                next.setVisibility(View.VISIBLE);
            }
        }, 6100);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                if (((Pig09)getActivity()).play){
                    bundle.putBoolean("play",true);
                }
                else {
                    bundle.putIntegerArrayList("myList", myList);
                }
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                pFinal03 pfinal03 = new pFinal03();
                pfinal03.setArguments(bundle);
                transaction.replace(R.id.frame,pfinal03);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
