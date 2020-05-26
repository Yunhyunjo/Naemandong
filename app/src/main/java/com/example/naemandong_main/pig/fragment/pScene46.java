package com.example.naemandong_main.pig.fragment;

import android.content.Intent;
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
import com.example.naemandong_main.pig.activity.Pig06;
import com.example.naemandong_main.pig.activity.Pig07;
import com.example.naemandong_main.pig.activity.Pig14;
import com.example.naemandong_main.pig.activity.Pig15;

// 첫둘 돼지 막돼집으로 가기로함
public class pScene46 extends Fragment {

    AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, pigs, wolf, tree;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"첫째 돼지와 둘째 돼지는 막내 돼지의 집으로 도망쳤어요."};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene46, container,false);

        background = view.findViewById(R.id.background);
        tree = view.findViewById(R.id.tree);
        wolf = view.findViewById(R.id.wolf);
        pigs = view.findViewById(R.id.pigs);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/19_bg-01.png")
                .into(background);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/treecollapse.png")
                .into(tree);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/20_wolf1 (1).png")
                .into(wolf);

        pigs.setBackgroundResource(R.drawable.pig_s46);
        frameAnimation = (AnimationDrawable) pigs.getBackground();
        Animation pigsgo = AnimationUtils.loadAnimation(getActivity(), R.anim.pscene46);

        frameAnimation.start();
        pigs.startAnimation(pigsgo);

        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                frameAnimation.stop();
            }
        }, 3100);
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
                if(((Pig14)getActivity()).play){
                    if(((Pig14)getActivity()).getData() == 1){
                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig15.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else {
//                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig02.class);
//                        intent.putExtra("play",true);
//                        startActivity(intent);
//                        getActivity().finish();
                    }
                }
                else{
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    pScene47 pscene47 = new pScene47();
                    transaction.replace(R.id.frame, pscene47);
                    transaction.commit();  //저장
                }
            }
        });

        return view;
    }
}
