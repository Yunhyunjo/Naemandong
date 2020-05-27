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

public class pScene122 extends Fragment {

    AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, pig, cotton;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"한편, 그 사실을 모르고 있는 둘째 돼지는 푹신한 이불로 집을 짓고 있었어요.", "\"푹신한 이불로 만든 집은 절대 무너지지 않지!\"" };
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene122, container,false);

        background = view.findViewById(R.id.background);
        pig = view.findViewById(R.id.pig);
        cotton = view.findViewById(R.id.cotton);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/9_bg(bottom)-01.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/9_bg2-01.png")
                .into(cotton);
        pig.setBackgroundResource(R.drawable.pig_s21);
        frameAnimation = (AnimationDrawable) pig.getBackground();

        subtitles.setText(subs[0]);
        frameAnimation.start();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
                next.setVisibility(View.VISIBLE);
            }
        }, 3100);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                pScene123 pscene123 = new pScene123();
                transaction.replace(R.id.frame,pscene123);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
