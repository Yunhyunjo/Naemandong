package com.example.naemandong_main.rabbit.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.naemandong_main.R;
import com.example.naemandong_main.rabbit.activity.Rabbit01;

import java.io.IOException;

public class rScene01 extends Fragment {

    private View view;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    private ImageView background, box;
    private ImageButton next;
    private TextView subtitles;
    private LinearLayout record_box;
    boolean sound, subtitle, record;
    private String subs [] = {"어느 숲 속에 토끼, 사자, 나무늘보, 거북이가 살고 있었어요.", "동물들은 매일같이 자신이 가장 빠르다며 싸우곤 했지요."};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene01, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);
        record_box = view.findViewById(R.id.record);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene01_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene01_2.mp3");
            mp2.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();

        if (getArguments() != null){
            sound = getArguments().getBoolean("sound");
            subtitle = getArguments().getBoolean("subtitle");
            record = getArguments().getBoolean("record");
        }

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/1/1_fin.jpg")
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
        mp1.start();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
                mp2.start();
            }
        }, a);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                if(record == true){
                    subtitles.setVisibility(View.INVISIBLE);
                    box.setVisibility(View.INVISIBLE);
                    record_box.setVisibility(View.VISIBLE);
                }
                next.setVisibility(View.VISIBLE);
            }
        }, b);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putBoolean("sound",sound);
//                bundle.putBoolean("subtitle",subtitle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rScene02 rscene02 = new rScene02();
//                rscene02.setArguments(bundle);
                transaction.replace(R.id.frame,rscene02);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
