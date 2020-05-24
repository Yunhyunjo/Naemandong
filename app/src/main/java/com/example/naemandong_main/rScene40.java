package com.example.naemandong_main;

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

public class rScene40 extends Fragment {

    private AnimationDrawable frameAnimation1;
    private View view;
    private ImageView background, box, rabbit, turtle;
    private TextView subtitles;
    private String subs [] = {"거북이는 토끼를 깨우지 않기로 했어요.", "거북이 “토끼가 잘 때 빨리 가야겠다!!”"};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene40, container,false);

        background = view.findViewById(R.id.background);
        rabbit = view.findViewById(R.id.rabbit);
        turtle = view.findViewById(R.id.turtle);
        box = view.findViewById(R.id.subtitlebox);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/38_back.png")
                .into(background);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/35_sleep.png")
                .into(rabbit);

        turtle.setBackgroundResource(R.drawable.turtle_doridori);
        frameAnimation1 = (AnimationDrawable) turtle.getBackground();

        Animation turtlego = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene40);

        frameAnimation1.start();
        turtle.startAnimation(turtlego);

        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
            }
        }, 2000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 8300);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((Rabbit14)getActivity()).play){
                    if(((Rabbit14)getActivity()).getData() == 0){
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit15.class);
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
                    rScene41 rscene41 = new rScene41();
                    transaction.replace(R.id.frame, rscene41);
                    transaction.commit();  //저장
                }
            }
        });

        return view;
    }
}
