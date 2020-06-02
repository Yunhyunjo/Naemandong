package com.example.naemandong_main.pig.fragment;

import android.content.Context;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.naemandong_main.Data.savebookData;
import com.example.naemandong_main.Data.savebookResponse;
import com.example.naemandong_main.Network.RetrofitClient;
import com.example.naemandong_main.Network.ServiceApi;
import com.example.naemandong_main.R;
import com.example.naemandong_main.Record;
import com.example.naemandong_main.Save_Dialog;
import com.example.naemandong_main.Setting_data;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pFinal01 extends Fragment {
    private Save_Dialog saveDialog;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    private View view;
    private ImageView background, box;
    private TextView subtitles;
    private ImageButton save, exit;
    private ArrayList<Integer> myList;
    private ArrayList<String> recordList;
    boolean play = false;
    boolean t = false;
    boolean record;
    private String subs [] = {"막내 돼지는 용감하게 나쁜 늑대를 물리치고 불을 껐어요.", "그 뒤로 막내 돼지는 소방관이 되어 숲 속 마을의 안전을 지켰답니다."};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rfinal01, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        subtitles = view.findViewById(R.id.subTitle);
        save = view.findViewById(R.id.save);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/20_end2.png")
                .into(background);

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
            mp1.setDataSource("http://49.50.174.179:9000/voice/pig/pFinal01_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/pig/pFinal01_2.mp3");
            mp2.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();

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
                t = true;

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
        }, b);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((Setting_data) getContext().getApplicationContext()).isRecord()) {
                    recordList = ((Setting_data) getContext().getApplicationContext()).getRecordList();
                    int book_no = ((Setting_data) getContext().getApplicationContext()).getBook_no();
                    while (recordList.size() < 30)
                        recordList.add("0");
                    saveDialog = new Save_Dialog(getActivity(), book_no, "아기돼지 삼형제", 2, recordList, "http://49.50.174.179:9000/images/cover/pig/3-01.png", true);
                    saveDialog.show();
                    Log.d("record >>>>>>>> ", String.valueOf(recordList));
                    ((Setting_data) getContext().getApplicationContext()).setRecord(false);
                } else {
                    saveDialog = new Save_Dialog(getActivity(), "아기돼지 삼형제", 2, myList, "http://49.50.174.179:9000/images/cover/pig/3-01.png");
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
