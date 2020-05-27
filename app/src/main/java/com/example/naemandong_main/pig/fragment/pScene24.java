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
import com.example.naemandong_main.pig.activity.Pig07;
import com.example.naemandong_main.pig.activity.Pig14;

// 선택지 전
public class pScene24 extends Fragment {

    AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, wolf, pig, grass;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"\"저리 가! 이 나쁜 늑대야!!\"", "\"흥, 이쯤이야 내 몸통 박치기 한 번이면 무너지지!\"", "쿵!!", "늑대는 튼튼한 몸으로 둘째 돼지의 집을 무너뜨렸어요." };
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene23, container,false);

        background = view.findViewById(R.id.background);
        wolf = view.findViewById(R.id.wolf);
        pig = view.findViewById(R.id.pig);
        grass = view.findViewById(R.id.grass);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/10_bg-01.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/11_pg1-01.png")
                .into(pig);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/10_bg lawn-01.png")
                .into(grass);

        wolf.setBackgroundResource(R.drawable.wolf_s11);
        frameAnimation = (AnimationDrawable) wolf.getBackground();
        final Animation wolfgo = AnimationUtils.loadAnimation(getActivity(), R.anim.pscene11);

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
                frameAnimation.start();
                wolf.startAnimation(wolfgo);
                subtitles.setText(subs[2]);
                next.setVisibility(View.VISIBLE);
            }
        }, 4100);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                grass.setImageResource(0);
                pig.setImageResource(0);
                wolf.setBackgroundResource(0);
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/pig/1/12_example-01.png")
                        .into(background);
                subtitles.setText(subs[3]);
                next.setVisibility(View.VISIBLE);
            }
        }, 7100);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((Pig06)getActivity()).play){
                    if(((Pig06)getActivity()).getData() == 0){
                        ((Pig06)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig07.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else {
                        ((Pig06)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig14.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                else{
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    pScene25 pscene25 = new pScene25();
                    transaction.replace(R.id.frame, pscene25);
                    transaction.commit();  //저장
                }
            }
        });

        return view;
    }
}
