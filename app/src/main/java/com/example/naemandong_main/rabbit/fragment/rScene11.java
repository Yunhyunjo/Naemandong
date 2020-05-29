package com.example.naemandong_main.rabbit.fragment;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
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
import com.example.naemandong_main.rabbit.activity.Rabbit03;
import com.example.naemandong_main.rabbit.activity.Rabbit04;
import com.example.naemandong_main.rabbit.activity.Rabbit07;

import java.io.IOException;

public class rScene11 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    private AnimationDrawable frameAnimation1;
    private View view;
    private ImageView background, box, rabbit;
    private TextView subtitles;
    private String subs [] = {"깡총깡총 얼마나 뛰었을까 갈림길이 나왔어요.", "“어어? 어디로 가야 하지?”"};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene11, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        rabbit = view.findViewById(R.id.rabbit);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/11_back.png")
                .into(background);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene11_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene11_2.mp3");
            mp2.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();

        rabbit.setBackgroundResource(R.drawable.rabbit_s11);
        frameAnimation1 = (AnimationDrawable) rabbit.getBackground();

        Animation rabbitgo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene11);

        frameAnimation1.start();
        rabbit.startAnimation(rabbitgo);
        subtitles.setText(subs[0]);
        mp1.start();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                AnimationDrawable frameAnimation1;
                rabbit.setBackgroundResource(R.drawable.rabbit_s11g);
                frameAnimation1 = (AnimationDrawable) rabbit.getBackground();
                frameAnimation1.start();
                subtitles.setText(subs[1]);
                mp2.start();
            }
        }, a);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, b);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((Rabbit03)getActivity()).play){
                    if(((Rabbit03)getActivity()).getData() == 0){
                        ((Rabbit03)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit04.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else {
                        ((Rabbit03)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit07.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                else{
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    rScene12 rscene12 = new rScene12();
                    transaction.replace(R.id.frame,rscene12);
                    transaction.commit();  //저장
                }
            }
        });

        return view;
    }
}
