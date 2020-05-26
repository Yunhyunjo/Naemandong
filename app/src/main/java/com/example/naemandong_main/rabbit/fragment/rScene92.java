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
import com.example.naemandong_main.rabbit.activity.Rabbit36;

import java.util.ArrayList;

public class rScene92 extends Fragment {

    private AnimationDrawable frameLion;
    private View view;
    private ImageView background, box, lion, turtle, bush1, bush2;
    private TextView subtitles;
    private String subs [] = {"\"흥! 나 몰래 혼자 가려고 하더니 메롱~\"", "사자는 넘어진 거북이를 지나쳐 결승선으로 달려갔어요."};
    private ImageButton next;
    private ArrayList<Integer> myList;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene92, container,false);

        background = view.findViewById(R.id.background);
        bush1 = view.findViewById(R.id.bush1);
        bush2 = view.findViewById(R.id.bush2);
        lion = view.findViewById(R.id.lion);
        turtle = view.findViewById(R.id.turtle);
        box = view.findViewById(R.id.subtitlebox);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        myList = (ArrayList<Integer>) ((Rabbit36)getActivity()).getMylist().clone();
        ((Rabbit36)getActivity()).clearList();

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/48_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/48_left.png")
                .into(bush1);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/48_right.png")
                .into(bush2);

        lion.setBackgroundResource(R.drawable.lion_s86);
        frameLion = (AnimationDrawable) lion.getBackground();
        turtle.setImageResource(R.drawable.ggwadang1);

        final Animation liongo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene66_lion);

        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
                lion.startAnimation(liongo);
                frameLion.start();
            }
        }, 4000);
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
                if (((Rabbit36)getActivity()).play){
                    bundle.putBoolean("play",true);
                }
                else {
                    bundle.putIntegerArrayList("myList", myList);
                }
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rFinal10 rfinal10 = new rFinal10();
                rfinal10.setArguments(bundle);
                transaction.replace(R.id.frame,rfinal10);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
