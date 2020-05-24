package com.example.naemandong_main.rabbit.fragment;

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

public class rScene31 extends Fragment {

    private View view;
    private ImageView background, box, bed, front;
    private TextView subtitles;
    private String subs [] = {"토끼 ”여기 베개랑 침대가 있네? 신난다!”", "베개를 찾은 토끼는 쿨쿨 깊은 잠이 들었어요."};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene31, container,false);

        front = view.findViewById(R.id.front);
        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        bed = view.findViewById(R.id.bed);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/34_back.png")
                .into(background);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/34_bed.png")
                .into(bed);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/33_rabbit.png")
                .into(front);

        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                front.setImageResource(0);
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/rabbit/5/34_r_bed.png")
                        .into(bed);
                subtitles.setText(subs[1]);
            }
        }, 5000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 8300);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rScene32 rscene32 = new rScene32();
                transaction.replace(R.id.frame,rscene32);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
