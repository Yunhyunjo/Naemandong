package com.example.naemandong_main.rabbit.fragment;

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

import java.io.IOException;

public class rScene20 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    private View view;
    private ImageView background, box, together, turtle;
    private TextView subtitles;
    private String subs [] = {"토끼의 소리를 들은 거북이는 얼른 엉금엉금 기어가 물속으로 들어갔어요.", "그러고서는 토끼를 등에 태우고 반대편으로 건너갔어요."};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene20, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        turtle = view.findViewById(R.id.turtle);
        together = view.findViewById(R.id.together);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        // 실수 아님 19번 배경 맞음
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/19_back.png")
                .into(background);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/20_tur_back.png")
                .into(turtle);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene20_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene20_2.mp3");
            mp2.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();

        Animation turtlego = AnimationUtils.loadAnimation(getActivity(), R.anim.turtle_swim);
        turtle.startAnimation(turtlego);

        subtitles.setText(subs[0]);
        mp1.start();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
                mp2.start();
                turtle.setImageResource(0);

                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/rabbit/5/20_back.png")
                        .into(background);

                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/rabbit/5/20_together.png")
                        .into(together);

                Animation togehergo = AnimationUtils.loadAnimation(getActivity(), R.anim.turtle_swim);
                together.startAnimation(togehergo);
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
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rScene21 rscene21 = new rScene21();
                transaction.replace(R.id.frame,rscene21);
                transaction.commit();  //저장
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
