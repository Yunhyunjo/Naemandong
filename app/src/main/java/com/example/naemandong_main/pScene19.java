package com.example.naemandong_main;

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

public class pScene19 extends Fragment {

    private View view;
    private ImageView background, box, wolf;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"늑대는 파란 상자를 열어봤어요.", "그 상자 안에는 늑대의 핸드폰이 들어있었어요.","따르릉~ 이때 늑대의 엄마에게 전화가 왔어요."};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene19, container,false);

        background = view.findViewById(R.id.background);
        subtitles = view.findViewById(R.id.subTitle);
        box = view.findViewById(R.id.box);
        wolf = view.findViewById(R.id.wolf);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/14,15-bg-01.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/14_phone-01.png")
                .into(box);

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
                subtitles.setText(subs[2]);
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/pig/15_bluebox-01.png")
                        .into(box);
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/pig/15_wolf-01.png")
                        .into(wolf);
                next.setVisibility(View.VISIBLE);
            }
        }, 6100);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                pScene20 pscene20 = new pScene20();
                transaction.replace(R.id.frame, pscene20);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
