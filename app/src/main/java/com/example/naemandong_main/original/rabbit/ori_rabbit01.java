package com.example.naemandong_main.original.rabbit;

import android.media.MediaPlayer;
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
import com.example.naemandong_main.rabbit.fragment.rScene02;

import java.io.IOException;

public class ori_rabbit01 extends Fragment {

    private View view;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    private ImageView background, box;
    private TextView subtitles;
    boolean sound, subtitle;
    private String subs [] = {"어느 숲속에 토끼와 거북이가 살고있었어요.", "토끼는 매일같이 거북이가 느리다며 놀려댔어요."};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene01, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        subtitles = view.findViewById(R.id.subTitle);

//        try {
//            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene01_1.mp3");
//            mp1.prepare();
//            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene01_2.mp3");
//            mp2.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();

        if (getArguments() != null){
            sound = getArguments().getBoolean("sound");
            subtitle = getArguments().getBoolean("subtitle");
        }

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/original/1_fin.jpg")
                .into(background);

//        if (subtitle) {
//            box.setVisibility(View.VISIBLE);
//            subtitles.setVisibility(View.VISIBLE);
//        }
//        else {
//            box.setVisibility(View.INVISIBLE);
//            subtitles.setVisibility(View.INVISIBLE);
//        }

        subtitles.setText(subs[0]);
        //mp1.start();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
                //mp2.start();
            }
        }, 3000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ori_rabbit02 ori_rabbit02 = new ori_rabbit02();
                transaction.replace(R.id.frame, ori_rabbit02);
                transaction.commit();  //저장
            }
        }, 6500);

        return view;
    }

}
