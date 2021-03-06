package com.example.naemandong_main.original.pig;

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
import com.example.naemandong_main.pig.activity.Pig22;
import com.example.naemandong_main.pig.fragment.pFinal09;

import java.util.ArrayList;

public class ori_pig16 extends Fragment {

    AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, wolf, pigs;
    private ImageButton next;
    private TextView subtitles;
    private ArrayList<Integer> myList;
    private String subs [] = {"\"으악! 뭐야! 엉덩이에 불이 붙었잖아! 늑대 살려!\"", "엉덩이에 불이 붙은 늑대는 헐레벌떡 굴뚝을 빠져나와 도망쳤어요."};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene56, container,false);

        background = view.findViewById(R.id.background);
        subtitles = view.findViewById(R.id.subTitle);
        pigs = view.findViewById(R.id.pigs);
        wolf = view.findViewById(R.id.wolf);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/19_bg-01.png")
                .into(background);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/27_house-01.png")
                .into(pigs);

        wolf.setBackgroundResource(R.drawable.wolf_s41);
        frameAnimation = (AnimationDrawable) wolf.getBackground();
        Animation wolfgo = AnimationUtils.loadAnimation(getActivity(), R.anim.pscene41);

        frameAnimation.start();
        wolf.startAnimation(wolfgo);

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
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ori_pig17 pig17 = new ori_pig17();
                transaction.replace(R.id.frame, pig17);
                transaction.commit();  //저장
            }
        }, 5100);

        return view;
    }
}
