package com.example.naemandong_main.rabbit.fragment;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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

import com.bumptech.glide.Glide;
import com.example.naemandong_main.R;
import com.example.naemandong_main.Record;
import com.example.naemandong_main.Save_Dialog;
import com.example.naemandong_main.Setting_data;
import com.example.naemandong_main.rabbit.activity.Rabbit21;

import java.io.IOException;
import java.util.ArrayList;

public class rFinal09 extends Fragment {

    private Save_Dialog saveDialog;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    private ArrayList<String> recordList;
    private AnimationDrawable frameAnimation1;
    private View view;
    private ImageView background, box, front, turtle, rabbit;
    private TextView subtitles;
    private ArrayList<Integer> myList;
    private String subs [] = {"\"거북아, 겁쟁이 사자가 거미를 보고 놀라서 집에 가버렸어!\"","\"오잉?.. 지금까지 뭐한거지…\"", "그렇게 경주는 허무하게 끝이 나 버렸답니다."};
    private ImageButton save, exit;
    boolean play = false;
    boolean sound, subtitle, record;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rfinal09, container,false);

        background = view.findViewById(R.id.background);
        front = view.findViewById(R.id.front);
        box = view.findViewById(R.id.subtitlebox);
        turtle = view.findViewById(R.id.turtle);
        rabbit = view.findViewById(R.id.rabbit);
        subtitles = view.findViewById(R.id.subTitle);
        save = view.findViewById(R.id.save);
        exit = view.findViewById(R.id.exit);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/8_back.jpg")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/8_front_rabbit.png")
                .into(front);

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
            mp1.setDataSource("http://49.50.174.179:9000/voice/rFinal09_1.MP3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/rFinal09_2.MP3");
            mp2.prepare();
            mp3.setDataSource("http://49.50.174.179:9000/voice/rFinal09_3.mp3");
            mp3.prepare();

        } catch (IOException e) {
            e.printStackTrace();
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();
        int c = mp1.getDuration() + mp2.getDuration() + mp3.getDuration();

        if (getArguments() != null){
            myList = getArguments().getIntegerArrayList("myList");
            play = getArguments().getBoolean("play");
            record = getArguments().getBoolean("record");
            if(!play){
                while(myList.size() < 7)
                    myList.add(3);
            }
        }

        turtle.setImageResource(R.drawable.t_run1);

        rabbit.setBackgroundResource(R.drawable.rabbit_leftgo);
        frameAnimation1 = (AnimationDrawable) rabbit.getBackground();

        Animation rabbitgo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene56);

        frameAnimation1.start();
        rabbit.startAnimation(rabbitgo);
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
                turtle.setImageResource(0);
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/rabbit/7/99_ohing.png")
                        .override(580,557)
                        .into(turtle);
                subtitles.setText(subs[1]);
                frameAnimation1.stop();

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
                box.setVisibility(View.INVISIBLE);
                subtitles.setVisibility(View.INVISIBLE);
                if (!play) {
                    save.setVisibility(View.VISIBLE);
                }
                if (((Setting_data) getContext().getApplicationContext()).isRecord()) {
                    subtitles.setVisibility(View.INVISIBLE);
                    box.setVisibility(View.INVISIBLE);
                    save.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(getActivity(), Record.class);
                    startActivity(intent);
                }
                exit.setVisibility(View.VISIBLE);
                if(((Setting_data) getContext().getApplicationContext()).isRecordPlay()){
                    ((Setting_data) getContext().getApplicationContext()).setRecordPlay(false);
                    ((Setting_data) getContext().getApplicationContext()).clearRecordList();
                }
            }
        }, c);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((Setting_data) getContext().getApplicationContext()).isRecord()){
                    recordList = ((Setting_data) getContext().getApplicationContext()).getRecordList();
                    int book_no = ((Setting_data) getContext().getApplicationContext()).getBook_no();
                    while(recordList.size() < 30)
                        recordList.add("0");
                    saveDialog = new Save_Dialog(getActivity(),book_no, "토끼와 거북이", 1, recordList, "http://49.50.174.179:9000/images/cover/rabbit/3-03.png",true);
                    //saveDialog.show();
                    Log.d("record >>>>>>>> ", String.valueOf(recordList));
                    ((Setting_data) getContext().getApplicationContext()).setRecord(false);
                    ((Setting_data) getContext().getApplicationContext()).clearRecordList();
                }
                else {
                    saveDialog = new Save_Dialog(getActivity(), "토끼와 거북이",1,myList,"http://49.50.174.179:9000/images/cover/rabbit/3-03.png");
                    saveDialog.show();
                }
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
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
