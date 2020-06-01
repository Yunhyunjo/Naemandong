package com.example.naemandong_main.rabbit.fragment;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
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
import com.example.naemandong_main.Record;
import com.example.naemandong_main.Setting_data;
import com.example.naemandong_main.rabbit.activity.Rabbit38;
import com.example.naemandong_main.rabbit.activity.Rabbit39;
import com.example.naemandong_main.rabbit.activity.Rabbit40;

import java.io.IOException;

public class rScene96 extends Fragment {

    private AnimationDrawable frameLion;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    boolean sound, subtitle;
    private View view;
    private ImageView background, box, lion, lion2;
    private TextView subtitles;
    private String subs[] = {"우다다다 얼마나 뛰었을까 갈림길이 나왔어요.", "“으음? 어디로 가야 하지?”"};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene96, container, false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        lion = view.findViewById(R.id.lion);
        lion2 = view.findViewById(R.id.lion2);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/13_back.png")
                .into(background);

        if(((Setting_data) getContext().getApplicationContext()).isRecordPlay()){
            String path = ((Setting_data) getContext().getApplicationContext()).getRecordone();
            ((Setting_data) getContext().getApplicationContext()).removeRecordData();
            try {
                recordmp.setDataSource(path);
                recordmp.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene96_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene96_2.MP3");
            mp2.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (getArguments() != null){
            sound = getArguments().getBoolean("sound");
            subtitle = getArguments().getBoolean("subtitle");
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();

        lion.setBackgroundResource(R.drawable.lion_backgo);
        frameLion = (AnimationDrawable) lion.getBackground();

        frameLion.start();


        subtitles.setText(subs[0]);

        if(((Setting_data) getContext().getApplicationContext()).isRecordPlay()){
            recordmp.start();
        }
        else {
            mp1.start();
        }
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                lion.setBackgroundResource(0);

                lion2.setBackgroundResource(R.drawable.lion_ggauddung);
                frameLion = (AnimationDrawable) lion2.getBackground();
                if(!((Setting_data) getContext().getApplicationContext()).isRecordPlay()){
                    mp2.start();
                }
                frameLion.start();
                subtitles.setText(subs[1]);
            }
        }, a);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO

                if(((Setting_data)getContext().getApplicationContext()).isRecord()){
                    subtitles.setVisibility(View.INVISIBLE);
                    box.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(getActivity(), Record.class);
                    startActivity(intent);
                }
                next.setVisibility(View.VISIBLE);
            }
        }, b);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((Rabbit38) getActivity()).play) {
                    if (((Rabbit38) getActivity()).getData() == 0) {
                        ((Rabbit38) getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit40.class);
                        intent.putExtra("play", true);
                        startActivity(intent);
                        getActivity().finish();
                    } else {
                        ((Rabbit38) getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit39.class);
                        intent.putExtra("play", true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                } else {
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    rScene97 rscene97 = new rScene97();
                    transaction.replace(R.id.frame, rscene97);
                    transaction.commit();  //저장
                }
            }
        });

        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mp1 != null) mp1.release();
        if (mp2 != null) mp2.release();
        if (recordmp != null) recordmp.release();
    }
}
