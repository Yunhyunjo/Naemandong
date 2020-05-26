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
import com.example.naemandong_main.rabbit.activity.Rabbit31;
import com.example.naemandong_main.rabbit.activity.Rabbit32;
import com.example.naemandong_main.rabbit.activity.Rabbit35;

public class rScene81 extends Fragment {

    private AnimationDrawable frameAnimation1;
    private View view;
    private ImageView background, box, turtle, lion2, front;
    private TextView subtitles;
    private String subs [] = {"그 사이 거북이는 열심히 달려 사자가 잠들어 있는 곳까지 오게 되었어요.", "\"앗 사자가 잠들었네? 경기중인데 사자를 깨워야하나?\""};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene81, container,false);

        background = view.findViewById(R.id.background);
        front = view.findViewById(R.id.front);
        box = view.findViewById(R.id.subtitlebox);
        turtle = view.findViewById(R.id.turtle);
        lion2 = view.findViewById(R.id.lion2);
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

        turtle.setBackgroundResource(R.drawable.turtle_rightgo);
        frameAnimation1 = (AnimationDrawable) turtle.getBackground();

        Animation turtlego = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene32);


        subtitles.setText(subs[0]);
        turtle.startAnimation(turtlego);
        frameAnimation1.start();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                turtle.setBackgroundResource(0);
                Glide.with(getActivity())
                        .load("http://49.50.174.179:9000/images/rabbit/5/35_t_front.png")
                        .into(turtle);
                subtitles.setText(subs[1]);
            }
        }, 6000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 10000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((Rabbit31)getActivity()).play){
                    if(((Rabbit31)getActivity()).getData() == 0){
                        ((Rabbit31)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit32.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else {
                        ((Rabbit31)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit35.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                else{
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    rScene82 rscene82 = new rScene82();
                    transaction.replace(R.id.frame,rscene82);
                    transaction.commit();  //저장
                }
            }
        });

        return view;
    }
}
