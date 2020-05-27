package com.example.naemandong_main.pig.fragment;

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

public class pScene113 extends Fragment {

    AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, pig, wolf, chimney;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"늑대가 자고있던 그때, 여행을 갔던 엄마 돼지가 돌아왔어요.", "“얘들아~ 모두 집은 잘 지었니? 첫째야~ 둘째야~ 우리 막내 어디있니?”", "엄마 돼지가 애타게 아기 돼지 삼형제를 불러보았지만 삼형제의 대답은 들리지 않았어요."};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene106, container,false);

        background = view.findViewById(R.id.background);
        pig = view.findViewById(R.id.pig);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/28_example.png")
                .into(background);

        pig.setBackgroundResource(R.drawable.mpig_s106);
        frameAnimation = (AnimationDrawable) pig.getBackground();
        frameAnimation.start();
        Animation piggo = AnimationUtils.loadAnimation(getActivity(), R.anim.pscene13);
        pig.startAnimation(piggo);

        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
                frameAnimation.stop();
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
                pScene106 pscene106 = new pScene106();
                transaction.replace(R.id.frame, pscene106);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
