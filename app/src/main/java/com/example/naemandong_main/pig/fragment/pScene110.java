package com.example.naemandong_main.pig.fragment;

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
import com.example.naemandong_main.pig.activity.Pig21;
import com.example.naemandong_main.pig.activity.Pig22;
import com.example.naemandong_main.pig.activity.Pig32;

public class pScene110 extends Fragment {

    AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, pigs, mpig;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"“그래 솜을 넣자!”", "엄마돼지와 아기 돼지 삼형제는 늑대의 뱃속에 솜을 넣었어요. "};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene107, container,false);

        background = view.findViewById(R.id.background);
        pigs = view.findViewById(R.id.pigs);
        mpig = view.findViewById(R.id.mpig);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/25_bg-02.png")
                .into(background);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/31_pigs3.png")
                .into(pigs);

        mpig.setBackgroundResource(R.drawable.mpig_111);
        frameAnimation = (AnimationDrawable) mpig.getBackground();
        frameAnimation.start();

        subtitles.setText(subs[0]);
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
        }, 5000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                pScene111 pscene111 = new pScene111();
                transaction.replace(R.id.frame, pscene111);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
