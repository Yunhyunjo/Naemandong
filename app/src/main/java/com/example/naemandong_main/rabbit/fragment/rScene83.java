package com.example.naemandong_main.rabbit.fragment;

import android.content.Intent;
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

import com.example.naemandong_main.rabbit.activity.Rabbit32;
import com.example.naemandong_main.rabbit.activity.Rabbit33;
import com.example.naemandong_main.rabbit.activity.Rabbit34;

public class rScene83 extends Fragment {

    private AnimationDrawable frameAnimation1;
    private View view;
    private ImageView background, box, turtle,lion, lion2, front;
    private TextView subtitles;
    private String subs [] = {"거북이는 사자를 깨우기로 했어요.", "\"사자야~ 일어나! 우리 경주하고 있잖아. 사자야~\"","\"응? 여기가 어디지? 흐암 졸려…\""};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene83, container,false);

        background = view.findViewById(R.id.background);
        front = view.findViewById(R.id.front);
        box = view.findViewById(R.id.subtitlebox);
        turtle = view.findViewById(R.id.turtle);
        lion = view.findViewById(R.id.lion);
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
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/91_front_R.png")
                .into(turtle);

        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
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
                lion.setBackgroundResource(R.drawable.lion_s83);
                frameAnimation1 = (AnimationDrawable) lion.getBackground();
                frameAnimation1.start();
                subtitles.setText(subs[2]);
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
                if (((Rabbit32)getActivity()).play){
                    if(((Rabbit32)getActivity()).getData() == 1){
                        ((Rabbit32)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit33.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else {
                        ((Rabbit32)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit34.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                else{
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    rScene84 rscene84 = new rScene84();
                    transaction.replace(R.id.frame,rscene84);
                    transaction.commit();  //저장
                }
            }
        });

        return view;
    }
}
