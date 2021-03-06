package com.example.naemandong_main.original.pig;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.naemandong_main.R;
import com.example.naemandong_main.pig.activity.Pig20;
import com.example.naemandong_main.pig.activity.Pig21;
import com.example.naemandong_main.pig.activity.Pig24;
import com.example.naemandong_main.pig.fragment.pScene66;

// 선택지 전
public class ori_pig13 extends Fragment {

    AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, wolf, pigs;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"막내 돼지가 문을 열어주지 않자 늑대는 막내 돼지의 집을 발로 뻥하고 찼어요.", "하지만 딱딱한 막내 돼지의 벽돌집은 무너지지 않았어요.", "\"우리집은 딱딱해서 나쁜 늑대 네가 무너뜨릴 수 없어!\"" };
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene50, container,false);

        background = view.findViewById(R.id.background);
        pigs = view.findViewById(R.id.pigs);
        wolf = view.findViewById(R.id.wolf);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/19_bg-01.png")
                .into(background);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/20_pig-01.png")
                .into(pigs);

        wolf.setBackgroundResource(R.drawable.wolf_s32);
        frameAnimation = (AnimationDrawable) wolf.getBackground();
//        Animation wolfgo = AnimationUtils.loadAnimation(getActivity(), R.anim.pscene05);

        subtitles.setText(subs[0]);
        frameAnimation.start();
        //      wolf.startAnimation(wolfgo);

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
            }
        }, 5100);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ori_pig14 pig14 = new ori_pig14();
                transaction.replace(R.id.frame, pig14);
                transaction.commit();  //저장
            }
        }, 9000);


        return view;
    }
}
