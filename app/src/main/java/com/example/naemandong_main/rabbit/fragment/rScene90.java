package com.example.naemandong_main.rabbit.fragment;

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

public class rScene90 extends Fragment {

    private AnimationDrawable frameAnimation1;
    private View view;
    private ImageView background, box, turtle,lion, lion2, front, skate, turtle2;
    private TextView subtitles;
    private String subs [] = {"\"여기 인라인 스케이트가 있네? 이걸 신고 달려야겠다!\"", "그 때! 거북이 목소리를 들은 사자가 잠에서 깨어났어요.","\"거북이 너, 나 몰래 먼저 가려고 하다니! 거기서!!\""};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene90, container,false);

        background = view.findViewById(R.id.background);
        front = view.findViewById(R.id.front);
        box = view.findViewById(R.id.subtitlebox);
        turtle = view.findViewById(R.id.turtle);
        turtle2 = view.findViewById(R.id.turtle2);
        lion = view.findViewById(R.id.lion);
        lion2 = view.findViewById(R.id.lion2);
        skate = view.findViewById(R.id.skate);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/8_back.jpg")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/8_front_rabbit.png")
                .into(front);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/90_2.png")
                .into(lion2);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/102_find.png")
                .into(turtle);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/102_s.png")
                .into(skate);

        final Animation turtlego = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene90);
        final Animation liongo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene66_lion);

        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                skate.setImageResource(0);
                turtle.setImageResource(0);
                turtle2.setBackgroundResource(R.drawable.tutle_s90);
                frameAnimation1 = (AnimationDrawable) turtle2.getBackground();
                frameAnimation1.start();
                turtle2.startAnimation(turtlego);
                subtitles.setText(subs[1]);
            }
        }, 4000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/rabbit/7/93_b.png")
                        .into(lion2);
                lion.setImageResource(R.drawable.l_run1);
                subtitles.setText(subs[2]);
            }
        }, 6000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                lion.setBackgroundResource(R.drawable.lion_s86);
                frameAnimation1 = (AnimationDrawable) lion.getBackground();
                frameAnimation1.start();
                lion.startAnimation(liongo);
            }
        }, 9000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 13000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    rScene91 rscene91 = new rScene91();
                    transaction.replace(R.id.frame,rscene91);
                    transaction.commit();  //저장
            }
        });

        return view;
    }
}
