package com.example.naemandong_main.pig.fragment;

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

public class pScene100 extends Fragment {

    AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, pig, wolf, chimney;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"첫째 돼지, 둘째 돼지를 잡아먹고 배가 부른 늑대는 솔솔 잠이 왔어요.", "늑대가 쿨쿨 자는 동안, 막내 돼지는 꽁꽁 묶인 밧줄을 풀기 시작했어요.", "“조금만 더 풀면 여기를 도망칠 수 있어”"};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene100, container,false);

        background = view.findViewById(R.id.background);
        pig = view.findViewById(R.id.pig);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/26_bg-03.png")
                .into(background);

        pig.setBackgroundResource(R.drawable.pig_rope);
        frameAnimation = (AnimationDrawable) pig.getBackground();
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
                subtitles.setText(subs[2]);
            }
        }, 4000);
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
                pScene101 pscene101 = new pScene101();
                transaction.replace(R.id.frame, pscene101);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
