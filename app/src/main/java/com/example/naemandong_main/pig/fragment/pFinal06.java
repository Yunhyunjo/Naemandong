package com.example.naemandong_main.pig.fragment;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.naemandong_main.R;
import com.example.naemandong_main.Record;
import com.example.naemandong_main.Save_Dialog;
import com.example.naemandong_main.Setting_data;

import java.io.IOException;
import java.util.ArrayList;

// 돌집 늑대 침대 엔딩
public class pFinal06 extends Fragment {
    private Save_Dialog saveDialog;
    AnimationDrawable frameAnimation;
    MediaPlayer recordmp = new MediaPlayer();
    private ArrayList<String> recordList;
    boolean record;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    private View view;
    private ImageView background, box, wolf;
    private TextView subtitles;
    private ImageButton save, exit;
    private ArrayList<Integer> myList;
    boolean play = false;
    private String subs [] = {"막내돼지는 깊은 잠에 빠진 늑대를 강으로 휙 하고 던졌어요.", "\"늑대야! 앞으로 나를 괴롭힐 생각은 하지도마!\"","그렇게 혼이난 늑대는 멀리멀리 도망쳤답니다."};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pfinal04, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        subtitles = view.findViewById(R.id.subTitle);
        save = view.findViewById(R.id.save);
        exit = view.findViewById(R.id.exit);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/pigpig1.png")
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
            mp1.setDataSource("http://49.50.174.179:9000/voice/pig/pFinal06_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/pig/pFinal06_2.mp3");
            mp2.prepare();
            mp3.setDataSource("http://49.50.174.179:9000/voice/pig/pFinal06_3.mp3");
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
                box.setVisibility(View.INVISIBLE);
                subtitles.setVisibility(View.INVISIBLE);
                if (!play) {
                    save.setVisibility(View.VISIBLE);
                }
                exit.setVisibility(View.VISIBLE);
                if (((Setting_data) getContext().getApplicationContext()).isRecord()) {
                    subtitles.setVisibility(View.INVISIBLE);
                    box.setVisibility(View.INVISIBLE);
                    save.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(getActivity(), Record.class);
                    startActivity(intent);
                }
                if (((Setting_data) getContext().getApplicationContext()).isRecordPlay()) {
                    ((Setting_data) getContext().getApplicationContext()).setRecordPlay(false);
                    ((Setting_data) getContext().getApplicationContext()).clearRecordList();
                }
            }
        }, c);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((Setting_data) getContext().getApplicationContext()).isRecord()) {
                    recordList = ((Setting_data) getContext().getApplicationContext()).getRecordList();
                    int book_no = ((Setting_data) getContext().getApplicationContext()).getBook_no();
                    while (recordList.size() < 30)
                        recordList.add("0");
                    saveDialog = new Save_Dialog(getActivity(), book_no, "아기돼지 삼형제", 2, recordList, "http://49.50.174.179:9000/images/cover/pig/pigcover.png", true);
                    saveDialog.show();
                    Log.d("record >>>>>>>> ", String.valueOf(recordList));
                    ((Setting_data) getContext().getApplicationContext()).setRecord(false);
                    ((Setting_data) getContext().getApplicationContext()).clearRecordList();
                } else {
                    saveDialog = new Save_Dialog(getActivity(), "아기돼지 삼형제", 2, myList, "http://49.50.174.179:9000/images/cover/pig/pigcover.png");
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
