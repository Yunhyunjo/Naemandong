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

public class pScene69 extends Fragment {

    AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, top_back, wolf, chimney;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"크크크 기다려라 돼지들아! 늑대님이 내려가신다!", "돼지 삼형제는 굴뚝 아래에 불을 피우기 시작했어요.", "어? 뭐야 어디서 타는 냄새가 나는데?"};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene42, container,false);

        background = view.findViewById(R.id.background);
        top_back = view.findViewById(R.id.top_back);
        wolf = view.findViewById(R.id.wolf);
        chimney = view.findViewById(R.id.chimney);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/25_bg(top)-01-01-01.png")
                .into(background);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/pigpig2.png")
                .into(top_back);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/pigpig3.png")
                .into(wolf);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/25_bg(bottom_3)-01.png")
                .into(chimney);

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
                pScene70 pscene70 = new pScene70();
                transaction.replace(R.id.frame, pscene70);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
