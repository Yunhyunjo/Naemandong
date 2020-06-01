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
import com.example.naemandong_main.pig.activity.Pig19;

import java.io.IOException;
import java.util.ArrayList;

// 땅파기
public class pScene61 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    boolean sound, subtitle;
    private ImageView box;
    AnimationDrawable frameAnimation1, frameAnimation2;
    private View view;
    private ImageView background, pig, wolf;
    private ImageButton next;
    private TextView subtitles;
    private ArrayList<Integer> myList;
    private String subs [] = {"땅파기를 끝낸 늑대는 막내 돼지의 집으로 갔어요.", "\"늑대야 우릴 잡아먹지 않겠다고 약속하면 이 맛있는 저녁을 같이 먹게 해줄게.\"", "“알겠어 이제 잡아먹으려고 하지 않을게.”"};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene61, container,false);

        pig = view.findViewById(R.id.pig);
        wolf = view.findViewById(R.id.wolf);
        background = view.findViewById(R.id.background);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);
        box = view.findViewById(R.id.subtitlebox);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/24_bg-012.png")
                .into(background);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/pig/pScene75_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/pig/pScene61_2.mp3");
            mp2.prepare();
            mp3.setDataSource("http://49.50.174.179:9000/voice/pig/pScene61_3.mp3");
            mp3.prepare();
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

        pig.setBackgroundResource(R.drawable.pig_s61);
        frameAnimation1 = (AnimationDrawable) pig.getBackground();

        wolf.setBackgroundResource(R.drawable.wolf_s61);
        frameAnimation2 = (AnimationDrawable) wolf.getBackground();

        frameAnimation1.start();
        frameAnimation2.start();

        subtitles.setText(subs[0]);

        myList = (ArrayList<Integer>) ((Pig19)getActivity()).getMylist().clone();
        ((Pig19)getActivity()).clearList();


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
                subtitles.setText(subs[2]);

                if(!((Setting_data) getContext().getApplicationContext()).isRecordPlay()){
                    mp3.start();
                }
            }
        }, b);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
                if (((Setting_data) getContext().getApplicationContext()).isRecord()) {
                    subtitles.setVisibility(View.INVISIBLE);
                    box.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(getActivity(), Record.class);
                    startActivity(intent);
                }
            }
        }, c);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                if (((Pig19)getActivity()).play){
                    bundle.putBoolean("play",true);
                }
                else {
                    bundle.putIntegerArrayList("myList", myList);
                }
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                pFinal08 pfinal08 = new pFinal08();
                pfinal08.setArguments(bundle);
                transaction.replace(R.id.frame,pfinal08);
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
