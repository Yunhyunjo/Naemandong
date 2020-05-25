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
import com.example.naemandong_main.rabbit.activity.Rabbit20;
import com.example.naemandong_main.rabbit.activity.Rabbit21;
import com.example.naemandong_main.rabbit.activity.Rabbit22;

public class rScene53 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    private AnimationDrawable frameAnimation1;
    private View view;
    private ImageView background, box,front, sloth;
    private TextView subtitles;
    private String subs [] = {"나무늘보 \"ㅇ….어….어어..………\"", "신비한 물약은 빨리 달리게 도와주는 약이었어요", "하지만 그 것도 잠시, 약의 효과는 금방 떨어져서 나무늘보는 다시 느려졌어요."};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene53, container,false);

        background = view.findViewById(R.id.background);
        front = view.findViewById(R.id.front);
        box = view.findViewById(R.id.subtitlebox);
        sloth = view.findViewById(R.id.sloth);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/8_back.jpg")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/8_front_rabbit.png")
                .into(front);

//        try {
//            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene08_1.mp3");
//            mp1.prepare();
//            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene08_2.mp3");
//            mp2.prepare();
//            mp3.setDataSource("http://49.50.174.179:9000/voice/rScene08_3.mp3");
//            mp3.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();
        int c = mp1.getDuration() + mp2.getDuration() + mp3.getDuration();

        sloth.setBackgroundResource(R.drawable.sloth_53_1);
        frameAnimation1 = (AnimationDrawable) sloth.getBackground();

        final Animation slothgo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene53);
        final Animation slothgo2 = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene53_2);

        subtitles.setText(subs[0]);
        //mp1.start();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
                frameAnimation1.start();
                sloth.startAnimation(slothgo);
                //mp2.start();
            }
        }, 2000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[2]);
                sloth.setBackgroundResource(R.drawable.sloth_53_2);
                frameAnimation1 = (AnimationDrawable) sloth.getBackground();
                frameAnimation1.start();
                sloth.startAnimation(slothgo2);
                //mp3.start();
            }
        }, 6000);
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
                if (((Rabbit20)getActivity()).play){
                    if(((Rabbit20)getActivity()).getData() == 0){
                        ((Rabbit20)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit21.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else {
                        ((Rabbit20)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit22.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                else {
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    rScene54 rscene54 = new rScene54();
                    transaction.replace(R.id.frame, rscene54);
                    transaction.commit();  //저장
                }
            }
        });


        return view;
    }
}
