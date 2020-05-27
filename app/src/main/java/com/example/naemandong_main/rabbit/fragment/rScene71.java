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
import com.example.naemandong_main.rabbit.activity.Rabbit14;
import com.example.naemandong_main.rabbit.activity.Rabbit27;
import com.example.naemandong_main.rabbit.activity.Rabbit28;
import com.example.naemandong_main.rabbit.activity.Rabbit29;

public class rScene71 extends Fragment {

    private AnimationDrawable frameTurtle;
    private View view;
    private ImageView background, box, turtle, front, front2;
    private TextView subtitles;
    private String subs[] = {"“영차! 영차!”", "사자가 똥을 싸는 동안 거북이는 사자를 따라잡게 되었어요.", "“앗 지금이 기회다! 사자보다 빨리 달릴 수 있는 방법이 없을까?”"};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene71, container, false);

        background = view.findViewById(R.id.background);
        front = view.findViewById(R.id.front);
//        front2 = view.findViewById(R.id.front2);
        box = view.findViewById(R.id.subtitlebox);
        turtle = view.findViewById(R.id.turtle);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/83_fin4.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/8_front_rabbit.png")
                .into(front);


        turtle.setBackgroundResource(R.drawable.turtle_rightgo);
        frameTurtle = (AnimationDrawable) turtle.getBackground();

        Animation turtlego = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene32);

        frameTurtle.start();
        turtle.startAnimation(turtlego);


        subtitles.setText(subs[0]);

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
                subtitles.setText(subs[2]);
            }
        }, 6000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 8000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((Rabbit27) getActivity()).play) {
                    if (((Rabbit27) getActivity()).getData() == 0) {
                        ((Rabbit27) getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit28.class);
                        intent.putExtra("play", true);
                        startActivity(intent);
                        getActivity().finish();
                    } else {
                        ((Rabbit27) getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit29.class);
                        intent.putExtra("play", true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                } else {
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    rScene72 rscene72 = new rScene72();
                    transaction.replace(R.id.frame, rscene72);
                    transaction.commit();  //저장
                }
            }
        });

        return view;
    }
}
