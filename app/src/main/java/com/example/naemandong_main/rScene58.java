package com.example.naemandong_main;

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

import java.util.ArrayList;

public class rScene58 extends Fragment {

    private AnimationDrawable frameAnimation1;
    private View view;
    private ImageView background, box, sloth;
    private TextView subtitles;
    private String subs [] = {"나무늘보는 쉬지않고 열심히 달려갔어요.","그리고 마침내 나무늘보의 눈 앞에 결승점이 보이기 시작했어요."};
    private ImageButton next;
    private ArrayList<Integer> myList;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene58, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        sloth = view.findViewById(R.id.sloth);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/67_back.png")
                .into(background);

        sloth.setBackgroundResource(R.drawable.sloth_rightgo);
        frameAnimation1 = (AnimationDrawable) sloth.getBackground();
        final Animation slothgo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene50_sloth);

        myList = (ArrayList<Integer>) ((Rabbit22)getActivity()).getMylist().clone();
        ((Rabbit22)getActivity()).clearList();

        subtitles.setText(subs[0]);
        frameAnimation1.start();
        sloth.startAnimation(slothgo);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
            }
        }, 3000);
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
                if (((Rabbit22)getActivity()).play){
                    bundle.putBoolean("play",true);
                }
                else {
                    bundle.putIntegerArrayList("myList", myList);
                }
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rFinal06 rfinal06 = new rFinal06();
                rfinal06.setArguments(bundle);
                transaction.replace(R.id.frame,rfinal06);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
