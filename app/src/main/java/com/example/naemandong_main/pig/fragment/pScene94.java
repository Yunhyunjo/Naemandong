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
import com.example.naemandong_main.pig.activity.Pig02;
import com.example.naemandong_main.pig.activity.Pig03;
import com.example.naemandong_main.pig.activity.Pig06;
import com.example.naemandong_main.pig.activity.Pig30;
import com.example.naemandong_main.pig.activity.Pig36;

import java.io.IOException;

public class pScene94 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    boolean sound, subtitle;
    private ImageView box;
    private AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, wolf, pig, grass;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"\"아이고 못 참겠다\"", "늑대가 첫째 돼지의 집을 후~ 하고 불자 맛있는 과자집이 날아가 버렸어요.","집이 사라진 첫째 돼지는 결국 둘째 돼지네 집으로 도망쳤어요." };
    Handler delayHandler = new Handler();
    boolean t = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene06, container,false);

        background = view.findViewById(R.id.background);
        wolf = view.findViewById(R.id.wolf);
        pig = view.findViewById(R.id.pig);
        grass = view.findViewById(R.id.grass);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);
        box = view.findViewById(R.id.subtitlebox);

        if(!((Setting_data) getContext().getApplicationContext()).isRecord()){
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
        }
        else{

        }


        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/4_example-01.png")
                .into(background);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/pig/pScene06_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/pig/pScene94_2.mp3");
            mp2.prepare();
            mp3.setDataSource("http://49.50.174.179:9000/voice/pig/pScene06_3.mp3");
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
                Glide.with(getContext())
                        .load("http://49.50.174.179:9000/images/pig/1/6-01.png")
                        .into(background);
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
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/pig/1/7_bg-01.png")
                        .into(background);
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/pig/1/7_wolf-01.png")
                        .into(wolf);
                pig.setBackgroundResource(R.drawable.pig_s6);
                frameAnimation = (AnimationDrawable) pig.getBackground();
                frameAnimation.start();
                Animation piggo = AnimationUtils.loadAnimation(getActivity(), R.anim.pscene06);
                pig.startAnimation(piggo);
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
                if (((Setting_data) getContext().getApplicationContext()).isRecord()) {
                    subtitles.setVisibility(View.INVISIBLE);
                    box.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(getActivity(), Record.class);
                    startActivity(intent);
                }
                next.setVisibility(View.VISIBLE);
                t = true;
            }
        }, c);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((Pig30)getActivity()).play){
                    if(((Pig30)getActivity()).getData() == 0){
                        ((Pig30)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig03.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else if (((Pig30)getActivity()).getData() == 1){
                        ((Pig30)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig06.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else {
                        ((Pig30)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig36.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                else{
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    pScene104 pscene104 = new pScene104();
                    transaction.replace(R.id.frame, pscene104);
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
