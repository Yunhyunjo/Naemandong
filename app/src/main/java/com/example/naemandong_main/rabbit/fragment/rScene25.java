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
import com.example.naemandong_main.rabbit.activity.Rabbit07;
import com.example.naemandong_main.rabbit.activity.Rabbit08;
import com.example.naemandong_main.rabbit.activity.Rabbit09;

public class rScene25 extends Fragment {

    private AnimationDrawable frameRabbit;
    private View view;
    private ImageView background, box, rabbit;
    private TextView subtitles;
    private String subs [] = {"신나게 달리던 토끼는 당근 밭을 만났어요. ","토끼 “내가 좋아하는 당근이네!”"};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene25, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        rabbit = view.findViewById(R.id.rabbit);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/26_back.png")
                .into(background);

        rabbit.setBackgroundResource(R.drawable.rabbit_rightgo2425 );
        frameRabbit = (AnimationDrawable) rabbit.getBackground();

        Animation rabbitgo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene25);

        subtitles.setText(subs[0]);
        rabbit.startAnimation(rabbitgo);
        frameRabbit.start();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                rabbit.setBackgroundResource(0);
                Glide.with(getActivity())
                        .load("http://49.50.174.179:9000/images/rabbit/5/26_front.png")
                        .into(rabbit);
                subtitles.setText(subs[1]);
            }
        }, 2500);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 6000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((Rabbit07)getActivity()).play){
                    if(((Rabbit07)getActivity()).getData() == 0){
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit09.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else {
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit08.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                else {
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    rScene26 rscene26 = new rScene26();
                    transaction.replace(R.id.frame, rscene26);
                    transaction.commit();  //저장
                }
            }
        });

        return view;
    }
}
