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

public class pScene107 extends Fragment {

    AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, pigs, mpig, chimney;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"그러자 늑대의 배 속에서 첫째 돼지, 둘째 돼지, 마지막으로 막내돼지가 나왔어요.", "“ 얘들아, 너희 모두 괜찮니? 엄마가 얼마나 걱정했는지 모른단다.”", "“저희는 괜찮아요, 고마워요 엄마!”"};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene107, container,false);

        background = view.findViewById(R.id.background);
        mpig = view.findViewById(R.id.mpig);
        pigs = view.findViewById(R.id.pigs);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/25_bg-02.png")
                .into(background);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/31_pigwolf.png")
                .into(mpig);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/31_pigs1.png")
                .into(pigs);

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
                pigs.setImageResource(0);
                pigs.setBackgroundResource(R.drawable.pigs_crying);
                frameAnimation = (AnimationDrawable) pigs.getBackground();
                frameAnimation.start();
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
                pScene108 pscene108 = new pScene108();
                transaction.replace(R.id.frame, pscene108);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
