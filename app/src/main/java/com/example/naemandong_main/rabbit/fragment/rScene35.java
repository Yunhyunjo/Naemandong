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
import com.example.naemandong_main.rabbit.activity.Rabbit11;
import com.example.naemandong_main.rabbit.activity.Rabbit12;
import com.example.naemandong_main.rabbit.activity.Rabbit13;

public class rScene35 extends Fragment {

    private AnimationDrawable frameAnimation1;
    private View view;
    private ImageView background, box, rabbit, turtle, bed;
    private TextView subtitles;
    private String subs [] = {"토끼는 거북이 덕분에 잠에서 깨어났어요.", "토끼 “으음 이게 무슨일이지?”", "잠에서 깬 토끼는 주변을 둘러보았어요."};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene35, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        rabbit = view.findViewById(R.id.rabbit);
        bed = view.findViewById(R.id.bed);
        turtle = view.findViewById(R.id.turtle);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/38_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/38_bed.png")
                .into(bed);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/38_t_front.png")
                .into(turtle);

        rabbit.setBackgroundResource(R.drawable.rabbit_sleep);
        frameAnimation1 = (AnimationDrawable) rabbit.getBackground();

        Animation rabbitgo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene35);

        frameAnimation1.start();
        rabbit.startAnimation(rabbitgo);

        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
            }
        }, 5000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[2]);
            }
        }, 5000);
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
                if (((Rabbit11)getActivity()).play) {
                    if (((Rabbit11) getActivity()).getData() == 0) {
                        ((Rabbit11) getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit12.class);
                        intent.putExtra("play", true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else {
                        ((Rabbit11) getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit13.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                else {
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    rScene36 rscene36 = new rScene36();
                    transaction.replace(R.id.frame, rscene36);
                    transaction.commit();  //저장
                }
            }
        });

        return view;
    }
}
