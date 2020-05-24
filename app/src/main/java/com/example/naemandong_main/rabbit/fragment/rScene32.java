package com.example.naemandong_main.rabbit.fragment;

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
import com.example.naemandong_main.rabbit.activity.Rabbit10;
import com.example.naemandong_main.rabbit.activity.Rabbit11;

public class rScene32 extends Fragment {

    private AnimationDrawable frameAnimation1;
    private View view;
    private ImageView background, box, rabbit_sleep, turtle, bush1, bush2;
    private TextView subtitles;
    private String subs [] = {"토끼가 잠든 사이 거북이는 어느새 토끼가 있는 곳에 도착했어요.", "그리고 거북이는 토끼의 자는 모습을 바라보았어요."};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene32, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        rabbit_sleep = view.findViewById(R.id.rabbit_sleep);
        bush1 = view.findViewById(R.id.bush1);
        bush2 = view.findViewById(R.id.bush2);
        turtle = view.findViewById(R.id.turtle);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/35_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/35_sleep.png")
                .into(rabbit_sleep);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/35_01.png")
                .into(bush1);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/35_2.png")
                .into(bush2);

        turtle.setBackgroundResource(R.drawable.turtle_rightgo);
        frameAnimation1 = (AnimationDrawable) turtle.getBackground();

        Animation turtlego = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene32);

        frameAnimation1.start();
        turtle.startAnimation(turtlego);
        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
                turtle.setBackgroundResource(0);
                Glide.with(getActivity())
                        .load("http://49.50.174.179:9000/images/rabbit/5/35_t_front.png")
                        .into(turtle);
            }
        }, 2000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 11000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((Rabbit10)getActivity()).play){
                    if(((Rabbit10)getActivity()).getData() == 0){
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit11.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
//                    else {
//                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit14.class);
//                        intent.putExtra("play",true);
//                        startActivity(intent);
//                        getActivity().finish();
//                    }
                }
                else {
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    rScene33 rscene33 = new rScene33();
                    transaction.replace(R.id.frame, rscene33);
                    transaction.commit();  //저장
                }
            }
        });

        return view;
    }
}
