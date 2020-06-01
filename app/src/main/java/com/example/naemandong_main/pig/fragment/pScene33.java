package com.example.naemandong_main.pig.fragment;

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
import com.example.naemandong_main.pig.activity.Pig08;
import com.example.naemandong_main.pig.activity.Pig09;
import com.example.naemandong_main.pig.activity.Pig11;
import com.example.naemandong_main.pig.activity.Pig12;
import com.example.naemandong_main.pig.activity.Pig13;

import java.io.IOException;

public class pScene33 extends Fragment {

    AnimationDrawable frameAnimation;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    private View view;
    private ImageView background, wolf, box;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"\"우리집은 단단해서 나쁜 늑대 너는 무너뜨릴 수 없어!\"", "\"저 튼튼한 막내 돼지 집에 어떻게 하면 들어갈 수 있을까\"" };
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene33, container,false);
        box = view.findViewById(R.id.subtitlebox);
        background = view.findViewById(R.id.background);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/20_example-01.png")
                .into(background);

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
            mp1.setDataSource("http://49.50.174.179:9000/voice/pig/pScene33_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/pig/pScene33_2.mp3");
            mp2.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();


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
                if (((Setting_data) getContext().getApplicationContext()).isRecord()) {
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
                if (((Pig08)getActivity()).play){
                    if(((Pig08)getActivity()).getData() == 0){
                        ((Pig08)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig11.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else if(((Pig08)getActivity()).getData() == 1) {
                        ((Pig08)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig12.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else {
                        ((Pig08)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig13.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                else{
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    pScene34 pscene34 = new pScene34();
                    transaction.replace(R.id.frame, pscene34);
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
