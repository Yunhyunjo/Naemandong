package com.example.naemandong_main.original.rabbit;


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
import com.example.naemandong_main.rabbit.activity.Rabbit01;

import java.io.IOException;

public class ori_rabbit08 extends Fragment {

    private View view;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    private AnimationDrawable frameAnimation1;
    private ImageView background, box, front, rabbit;
    private TextView subtitles;
    private String subs[] = {"깜짝 놀라 잠에서 깬 토끼는 뒤늦게 정상을 향해 뛰어갔어요.", "하지만 잠꾸러기 토끼는 거북이에게 지고 말았답니다."};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ori_rabbit08, container, false);

        background = view.findViewById(R.id.background);
        front = view.findViewById(R.id.front);
        box = view.findViewById(R.id.subtitlebox);
        rabbit = view.findViewById(R.id.rabbit);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.nmd);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/original/7_fin.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/original/7_front.png")
                .into(front);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/ori_rabbit08_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/ori_rabbit08_2.mp3");
            mp2.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();

        rabbit.setBackgroundResource(R.drawable.rabbit_rightgo);
        frameAnimation1 = (AnimationDrawable) rabbit.getBackground();

        Animation turtlego = AnimationUtils.loadAnimation(getActivity(), R.anim.ori_rabbit08);

        frameAnimation1.start();
        rabbit.startAnimation(turtlego);
        subtitles.setText(subs[0]);
        mp1.start();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                rabbit.setBackgroundResource(0);
                subtitles.setText(subs[1]);
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/rabbit/original/8_zwazeol.png")
                        .into(rabbit);
                mp2.start();
            }
        }, a);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                box.setVisibility(View.INVISIBLE);
                subtitles.setVisibility(View.INVISIBLE);
                next.setVisibility(View.VISIBLE);

            }
        }, b);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit01.class);
                    startActivity(intent);
                    getActivity().finish();
            }
        });

        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mp1 != null) mp1.release();
        if (mp2 != null) mp2.release();
    }
}
