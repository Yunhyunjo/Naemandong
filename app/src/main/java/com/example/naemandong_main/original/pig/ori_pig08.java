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
import com.example.naemandong_main.pig.fragment.pScene24;

public class ori_pig08 extends Fragment {

    AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, wolf, pig, grass;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"겨우겨우 도망친 첫째 돼지는 둘째 돼지와 함께 떨고 있었어요.", "\"얘들아 나 너무 배가 고파~ 나 좀 들여보내주면 안될까?\"" };
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene23, container,false);

        background = view.findViewById(R.id.background);
        pig = view.findViewById(R.id.pig);
        grass = view.findViewById(R.id.grass);
        wolf = view.findViewById(R.id.wolf);
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

        wolf.setBackgroundResource(R.drawable.wolf_s5);
        frameAnimation = (AnimationDrawable) wolf.getBackground();
        Animation wolfgo = AnimationUtils.loadAnimation(getActivity(), R.anim.pscene05);

        frameAnimation.start();
        wolf.startAnimation(wolfgo);
        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                frameAnimation.stop();
                subtitles.setText(subs[1]);
            }
        }, 3100);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ori_pig09 pig09 = new ori_pig09();
                transaction.replace(R.id.frame, pig09);
                transaction.commit();  //저장
            }
        }, 6000);
        return view;
    }
}
