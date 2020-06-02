package com.example.naemandong_main.rabbit.fragment;

import android.content.Intent;
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

public class rFinal06 extends Fragment {

    private Save_Dialog saveDialog;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    private ArrayList<String> recordList;
    private View view;
    private ImageView background, box, turtle;
    private TextView subtitles;
    private ArrayList<Integer> myList;
    private String subs [] = {"“나무늘보야! 네가 이겼어! 축하해!”","느리지만 꾸준히 경주를 한 나무늘보는 결국 승리를 했답니다."};
    private ImageButton save, exit;
    boolean play = false;
    boolean t, record;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rfinal05, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        turtle = view.findViewById(R.id.rabbit);
        subtitles = view.findViewById(R.id.subTitle);
        save = view.findViewById(R.id.save);
        exit = view.findViewById(R.id.exit);

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

        if (getArguments() != null){
            myList = getArguments().getIntegerArrayList("myList");
            play = getArguments().getBoolean("play");
            record = getArguments().getBoolean("record");
            if(!play){
                while(myList.size() < 7)
                    myList.add(3);
            }
        }

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/68_fin.png")
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
            mp1.setDataSource("http://49.50.174.179:9000/voice/rFinal06_1.MP3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/rFinal06_2.mp3");
            mp2.prepare();

        } catch (IOException e) {
            e.printStackTrace();
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();

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
                t = true;
            }
        }, b);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((Setting_data) getContext().getApplicationContext()).isRecord()){
                    recordList = ((Setting_data) getContext().getApplicationContext()).getRecordList();
                    int book_no = ((Setting_data) getContext().getApplicationContext()).getBook_no();
                    while(recordList.size() < 30)
                        recordList.add("0");
                    Log.d("record >>>>>>>> ", String.valueOf(recordList));
                    saveDialog = new Save_Dialog(getActivity(),book_no, "토끼와 거북이", 1, recordList, "http://49.50.174.179:9000/images/cover/rabbit/2-1.png",true);
                    saveDialog.show();
                    ((Setting_data) getContext().getApplicationContext()).setRecord(false);
                }
                else {
                    saveDialog = new Save_Dialog(getActivity(), "토끼와 거북이",1,myList,"http://49.50.174.179:9000/images/cover/rabbit/2-1.png");
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
        if (recordmp != null) recordmp.release();
    }
}
