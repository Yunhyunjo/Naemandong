package com.example.naemandong_main.pig.fragment;

import android.content.Intent;
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

import java.io.IOException;

public class pScene88 extends Fragment {


    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    boolean sound, subtitle;
    private ImageView box;
    private View view;
    private ImageView background, pig;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"\"와 벌써 내 집이 생겼네!!\"", "처음으로 자기만의 집을 지은 첫째 돼지는 뿌듯했어요." };
    Handler delayHandler = new Handler();
    boolean t = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene01, container,false);

        background = view.findViewById(R.id.background);
        pig = view.findViewById(R.id.pig);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);
        box = view.findViewById(R.id.subtitlebox);

        (new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    try {
                        Thread.sleep(1000); //1초 간격으로 실행
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (((Setting_data) getContext().getApplicationContext()).getSubtitle() == true) {
                                    subtitles.setVisibility(View.VISIBLE);
                                    box.setVisibility(View.VISIBLE);
                                } else {
                                    subtitles.setVisibility(View.INVISIBLE);
                                    box.setVisibility(View.INVISIBLE);
                                }

                            }
                        });
                    } catch (InterruptedException e) {
                        // error
                    }
                    if (t)
                        break;
                }

            }
        })).start();


        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/4_bg-02.png")
                .into(background);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/pig/pScene04_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/pig/pScene04_2.mp3");
            mp2.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();
        int c = mp1.getDuration() + mp2.getDuration() + mp3.getDuration();

        if (getArguments() != null){
            sound = getArguments().getBoolean("sound");
            subtitle = getArguments().getBoolean("subtitle");
        }

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
                subtitles.setText(subs[1]);

                if(!((Setting_data) getContext().getApplicationContext()).isRecordPlay()){
                    mp2.start();
                }
            }
        }, a);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
                t = true;

                if ((((Setting_data) getContext().getApplicationContext()).getSubtitle() == true)&&(((Setting_data) getContext().getApplicationContext()).isRecord()==false)) {
                    subtitles.setVisibility(View.INVISIBLE);
                    box.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(getActivity(), Record.class);
                    startActivity(intent);
                }
            }
        }, b);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                pScene89 pscene89 = new pScene89();
                transaction.replace(R.id.frame,pscene89);
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
        if (mp3 != null) mp3.release();
        if (recordmp != null) recordmp.release();
    }
}
