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
import com.example.naemandong_main.pig.activity.Pig07;
import com.example.naemandong_main.pig.activity.Pig08;
import com.example.naemandong_main.pig.activity.Pig25;
import com.example.naemandong_main.pig.activity.Pig28;

import java.io.IOException;

public class pScene27 extends Fragment {

    private View view;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    private ImageView background, pig, wolf, bg2, wolf2, box;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"\"드디어 배를 채울 수 있게 되었구나!! 으하하!!\"", "“으악!!!”", "하지만 여전히 배가 차지 않은 늑대는 셋째 돼지의 집으로 향했어요." };
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene27, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        pig = view.findViewById(R.id.pig);
        wolf = view.findViewById(R.id.wolf);
        wolf2 = view.findViewById(R.id.wolf2);
        bg2 = view.findViewById(R.id.bg2);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/14_bg-01.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/15_wolf-01.png")
                .into(wolf);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/14_pg1-01.png")
                .into(pig);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/15_bg2-01.png")
                .into(bg2);

        if (((Setting_data) getContext().getApplicationContext()).isRecordPlay()) {
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
            mp1.setDataSource("http://49.50.174.179:9000/voice/pig/pScene27_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/pig/pScene27_2.mp3");
            mp2.prepare();
            mp3.setDataSource("http://49.50.174.179:9000/voice/pig/pScene27_3.mp3");
            mp3.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();
        int c = mp1.getDuration() + mp2.getDuration() + mp3.getDuration();


        subtitles.setText(subs[0]);
        if (((Setting_data) getContext().getApplicationContext()).isRecordPlay()) {
            recordmp.start();
        } else {
            mp1.start();
        }
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                wolf.setImageResource(0);
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/pig/1/15_pg2.png")
                        .into(pig);
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/pig/1/15_wolf-01.png")
                        .into(wolf2);
                subtitles.setText(subs[1]);
                if (!((Setting_data) getContext().getApplicationContext()).isRecordPlay()) {
                    mp2.start();
                }
            }
        }, a);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                wolf2.setImageResource(0);
                bg2.setImageResource(0);
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/pig/1/16_wolf-01.png")
                        .into(pig);
                subtitles.setText(subs[2]);
                if (!((Setting_data) getContext().getApplicationContext()).isRecordPlay()) {
                    mp3.start();
                }
            }
        }, b);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                if (((Setting_data) getContext().getApplicationContext()).isRecord()) {
                    subtitles.setVisibility(View.INVISIBLE);
                    box.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(getActivity(), Record.class);
                    startActivity(intent);
                }
                next.setVisibility(View.VISIBLE);
            }
        }, c);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((Pig07)getActivity()).play) {
                    if (((Pig07) getActivity()).getData() == 0) {
                        ((Pig07)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig08.class);
                        intent.putExtra("play", true);
                        startActivity(intent);
                        getActivity().finish();
                    } else if (((Pig07) getActivity()).getData() == 1) {
                        ((Pig07)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig25.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    } else {
                        ((Pig07)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig28.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                else{
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    pScene28 pscene28 = new pScene28();
                    transaction.replace(R.id.frame, pscene28);
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
        if (mp3 != null) mp3.release();
        if (recordmp != null) recordmp.release();
    }

}
