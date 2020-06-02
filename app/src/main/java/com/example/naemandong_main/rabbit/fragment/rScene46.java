package com.example.naemandong_main.rabbit.fragment;

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
import com.example.naemandong_main.rabbit.activity.Rabbit17;
import com.example.naemandong_main.rabbit.activity.Rabbit18;
import com.example.naemandong_main.rabbit.activity.Rabbit19;

import java.io.IOException;

public class rScene46 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    boolean sound, subtitle, t;
    private View view;
    private ImageView background, box, turtle, sloth;
    private TextView subtitles;
    private String subs [] = {"거북이는 나무늘보와 경주를 하기로 결정했어요.", "\"그으……래애……\"","\"어휴 답답해! 경주를 하자는 거야 말자는 거야!!!\""};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene46, container,false);

        background = view.findViewById(R.id.background);
        turtle = view.findViewById(R.id.turtle);
        sloth = view.findViewById(R.id.sloth);
        box = view.findViewById(R.id.subtitlebox);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        if(!((Setting_data) getContext().getApplicationContext()).isRecord() && !((Setting_data) getContext().getApplicationContext()).isRecordPlay()){
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
                .load("http://49.50.174.179:9000/images/rabbit/5/62_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/62_hwanagizon.png")
                .into(turtle);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/62_sloth.png")
                .into(sloth);

        if(((Setting_data) getContext().getApplicationContext()).isRecordPlay()){
            String path = ((Setting_data) getContext().getApplicationContext()).getRecordone();
            ((Setting_data) getContext().getApplicationContext()).removeRecordData();
            box.setVisibility(View.INVISIBLE);
            subtitles.setVisibility(View.INVISIBLE);
            try {
                recordmp.setDataSource(path);
                recordmp.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene46_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene46_2.MP3");
            mp2.prepare();
            mp3.setDataSource("http://49.50.174.179:9000/voice/rScene46_3.MP3");
            mp3.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (getArguments() != null){
            sound = getArguments().getBoolean("sound");
            subtitle = getArguments().getBoolean("subtitle");
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();
        int c = mp1.getDuration() + mp2.getDuration() + mp3.getDuration();

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
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/rabbit/5/62_angry.png")
                        .into(turtle);
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
                if (((Rabbit17)getActivity()).play) {
                    if (((Rabbit17) getActivity()).getData() == 1) {
                        ((Rabbit17) getActivity()).removeData();
                        Intent intent = new Intent(getActivity(), Rabbit18.class);
                        intent.putExtra("play", true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else{
                        ((Rabbit17) getActivity()).removeData();
                        Intent intent = new Intent(getActivity(), Rabbit19.class);
                        intent.putExtra("play", true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                else {
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    rScene47 rscene47 = new rScene47();
                    transaction.replace(R.id.frame, rscene47);
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
