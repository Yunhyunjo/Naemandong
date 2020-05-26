package com.example.naemandong_main.original.rabbit;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.naemandong_main.R;

public class ori_rabbit03 extends Fragment {

    private View view;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    private ImageView background, box, rabbit, turtle;
    private TextView subtitles;
    boolean sound, subtitle;
    private String subs [] = {"토끼 “좋아. 하지만 날 이길 수는 없을 껄?”", "거북이 “경주는 끝까지 해봐야 아는 거야”","토끼와 거북이는 산 꼭대기까지 경주하기로 했어요. "};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ori_rabbit02, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        rabbit = view.findViewById(R.id.rabbit);
        turtle = view.findViewById(R.id.turtle);
        subtitles = view.findViewById(R.id.subTitle);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/original/1_back.jpg")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/original/3_ok.png")
                .into(rabbit);

        turtle.setBackgroundResource(R.drawable.angry_tur);

//        try {
//            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene06_1.mp3");
//            mp1.prepare();
//            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene06_2.mp3");
//            mp2.prepare();
//            mp3.setDataSource("http://49.50.174.179:9000/voice/rScene06_3.mp3");
//            mp3.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();
//        int c = mp1.getDuration() + mp2.getDuration() + mp3.getDuration();

        subtitles.setText(subs[0]);
        mp1.start();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
                mp2.start();
            }
        }, 4000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[2]);
                //mp3.start();
            }
        }, 7000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ori_rabbit04 ori_rabbit04 = new ori_rabbit04();
                transaction.replace(R.id.frame, ori_rabbit04);
                transaction.commit();  //저장
            }
        }, 12000);
        return view;
    }

}
