package com.example.naemandong_main.pig.fragment;

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
import com.example.naemandong_main.Record;
import com.example.naemandong_main.Setting_data;
import com.example.naemandong_main.pig.activity.Pig33;
import com.example.naemandong_main.pig.activity.Pig35;

import java.io.IOException;
import java.util.ArrayList;

public class pScene121 extends Fragment {

    AnimationDrawable frameAnimation, frameAnimation2;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    private View view;
    private ImageView background, wolf, pigs, box;
    private ImageButton next;
    private TextView subtitles;
    private ArrayList<Integer> myList;
    private String subs [] = {"돼지 삼형제는 늑대에 맞서 싸우기로 했어요. ", "“나쁜 늑대 너!! 우리들 집을 부숴? 가만 두지 않겠어! 이야아아!”", "”어? 이게 아닌데.. 늑대 살려!!”"};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene121, container,false);

        background = view.findViewById(R.id.background);
        subtitles = view.findViewById(R.id.subTitle);
        pigs = view.findViewById(R.id.pigs);
        wolf = view.findViewById(R.id.wolf);
        next = view.findViewById(R.id.next);
        box = view.findViewById(R.id.subtitlebox);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/26_bg1.png")
                .into(background);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/24_wolf-02.png")
                .into(wolf);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/pig/pScene121_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/pig/pSscene121_2.mp3");
            mp2.prepare();
            mp3.setDataSource("http://49.50.174.179:9000/voice/pig/pScene121_3.mp3");
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

        myList = (ArrayList<Integer>) ((Pig35)getActivity()).getMylist().clone();
        ((Pig35)getActivity()).clearList();

        pigs.setBackgroundResource(R.drawable.pig_121);
        frameAnimation = (AnimationDrawable) pigs.getBackground();
        frameAnimation.start();

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
                subtitles.setText(subs[2]);
                wolf.setImageResource(0);
                wolf.setBackgroundResource(R.drawable.wolf_121);
                frameAnimation2 = (AnimationDrawable) wolf.getBackground();
                frameAnimation2.start();
                Animation wolfgo = AnimationUtils.loadAnimation(getActivity(), R.anim.pscene115);
                wolf.startAnimation(wolfgo);
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
                if (((Pig35)getActivity()).play){
                    bundle.putBoolean("play",true);
                }
                else {
                    bundle.putIntegerArrayList("myList", myList);
                }
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                pFinal13 pfinal13 = new pFinal13();
                pfinal13.setArguments(bundle);
                transaction.replace(R.id.frame,pfinal13);
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
