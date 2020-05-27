package com.example.naemandong_main.pig.fragment;

import android.content.Intent;
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
import com.example.naemandong_main.pig.activity.Pig06;
import com.example.naemandong_main.pig.activity.Pig15;

// 첫둘 돼지 막돼집으로 가기로함
public class pScene49 extends Fragment {

    AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, pigs;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"그 때 완성된 집에서 쉬고 있던 막내 돼지의 집을 첫째, 둘째 돼지가 두드렸어요.", "막내야! 형들이야 우리 좀 들여보내줘!! 늑대가 우리 집을 무너뜨리고 잡아먹으려고 해!", "형들 괜찮아? 어서 들어와!!"};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene49, container,false);

        background = view.findViewById(R.id.background);
        pigs = view.findViewById(R.id.pigs);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/19_example.png")
                .into(background);

        pigs.setBackgroundResource(R.drawable.pig_s46);
        frameAnimation = (AnimationDrawable) pigs.getBackground();
        Animation pigsgo = AnimationUtils.loadAnimation(getActivity(), R.anim.pscene46);

        frameAnimation.start();
        pigs.startAnimation(pigsgo);

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
                frameAnimation.stop();
                subtitles.setText(subs[2]);
                next.setVisibility(View.VISIBLE);
            }
        }, 5100);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                pScene50 pscene50 = new pScene50();
                transaction.replace(R.id.frame,pscene50);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
