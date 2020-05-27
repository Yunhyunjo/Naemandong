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
import com.example.naemandong_main.rabbit.activity.Rabbit08;

import java.util.ArrayList;

public class rScene27 extends Fragment {

    private AnimationDrawable frameRabbit;
    private View view;
    private ImageView background, box, rabbit;
    private TextView subtitles;
    private String subs [] = {"“아냐! 시합이 끝나고 먹으러 오자!” ","토끼는 먹는걸 포기하고 열심히 달려 산꼭대기에 도착했어요."};
    private ImageButton next;
    private ArrayList<Integer> myList;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene27, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        rabbit = view.findViewById(R.id.rabbit);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/28_back.png")
                .into(background);
        rabbit.setBackgroundResource(0);
        Glide.with(getActivity())
                .load("http://49.50.174.179:9000/images/rabbit/5/28_noneat.png")
                .into(rabbit);

        myList = (ArrayList<Integer>) ((Rabbit08)getActivity()).getMylist().clone();
        ((Rabbit08)getActivity()).clearList();

        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
                rabbit.setImageResource(0);
                rabbit.setBackgroundResource(R.drawable.rabbit_rightgo2425 );
                frameRabbit = (AnimationDrawable) rabbit.getBackground();
                Animation rabbitgo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene27);
                rabbit.startAnimation(rabbitgo);
                frameRabbit.start();
            }
        }, 2500);
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
                Bundle bundle = new Bundle();
                if (((Rabbit08)getActivity()).play){
                    bundle.putBoolean("play",true);
                }
                else {
                    bundle.putIntegerArrayList("myList", myList);
                }
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rFinal02 rfinal02 = new rFinal02();
                rfinal02.setArguments(bundle);
                transaction.replace(R.id.frame,rfinal02);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
