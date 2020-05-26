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
import com.example.naemandong_main.pig.activity.Pig05;
import com.example.naemandong_main.pig.fragment.pFinal02;

import java.util.ArrayList;

public class pScene20 extends Fragment {

    AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, wolf;
    private ImageButton next;
    private TextView subtitles;
    private ArrayList<Integer> myList;
    private String subs [] = {"\"늑대야 너 어디니! 저녁 먹기 전에 얼른 집에 들어와라!\"", "\"헉 시간이 벌써 이렇게.. 빨리 갈게요.\"","엄마에게 혼쭐이 난 늑대는 집으로 달려갔어요."};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene20, container,false);

        background = view.findViewById(R.id.background);
        subtitles = view.findViewById(R.id.subTitle);
        wolf = view.findViewById(R.id.wolf);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/16-02.png")
                .into(background);

        myList = (ArrayList<Integer>) ((Pig05)getActivity()).getMylist().clone();
        ((Pig05)getActivity()).clearList();

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
                        .load("http://49.50.174.179:9000/images/pig/17_bg-01.png")
                        .into(background);
                wolf.setBackgroundResource(R.drawable.wolf_s20);
                frameAnimation = (AnimationDrawable) wolf.getBackground();
                Animation wolfgo = AnimationUtils.loadAnimation(getActivity(),R.anim.pscene20);
                frameAnimation.start();
                wolf.startAnimation(wolfgo);
                next.setVisibility(View.VISIBLE);
            }
        }, 6100);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                if (((Pig05)getActivity()).play){
                    bundle.putBoolean("play",true);
                }
                else {
                    bundle.putIntegerArrayList("myList", myList);
                }
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                pFinal02 pfinal02 = new pFinal02();
                pfinal02.setArguments(bundle);
                transaction.replace(R.id.frame,pfinal02);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
