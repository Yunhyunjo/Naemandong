package com.example.naemandong_main.pig.fragment;

import android.content.Intent;
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
import com.example.naemandong_main.pig.activity.Pig07;
import com.example.naemandong_main.pig.activity.Pig08;
import com.example.naemandong_main.pig.activity.Pig25;
import com.example.naemandong_main.pig.activity.Pig28;

public class pScene105 extends Fragment {

    private View view;
    private ImageView background, pig, wolf, bg2, wolf2;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"“아휴. 아무래도 아직 너무 배고파서 안되겠어. 지금 너도 잡아먹을테다!”", "막내 돼지까지 몽땅 다 잡아먹은 배부른 늑대는 솔솔 잠이 왔어요.", "“배가 부르니 잠이 솔솔 오네. 시원한 나무 그늘 아래서 낮잠이나 자야지.”" };
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene105, container,false);

        background = view.findViewById(R.id.background);
        pig = view.findViewById(R.id.pig);
        wolf = view.findViewById(R.id.wolf);
        bg2 = view.findViewById(R.id.bg2);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/25_bg-02.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/23_wolf-01.png")
                .into(wolf);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/25_pig-02.png")
                .into(pig);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/15_bg2-01.png")
                .into(bg2);

        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                pig.setImageResource(0);
                bg2.setImageResource(0);
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/pig/1/26_wolf1-01.png")
                        .into(wolf);
                subtitles.setText(subs[1]);
            }
        }, 3100);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[2]);
            }
        }, 6100);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 9100);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                pScene113 pscene113 = new pScene113();
                transaction.replace(R.id.frame, pscene113);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
