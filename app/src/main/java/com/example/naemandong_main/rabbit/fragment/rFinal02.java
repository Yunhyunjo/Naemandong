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

public class rFinal02 extends Fragment {

    private Save_Dialog saveDialog;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    private ArrayList<String> recordList;
    private View view;
    private ImageView background, box, rabbit;
    private TextView subtitles;
    private ArrayList<Integer> myList;
    boolean play = false;
    boolean t, record;
    private String subs [] = {"“와 내가 이겼다!! 역시 난 빨라~”","토끼는 결국 경주에서 이겼어요.", "그 후로 거북이는 계속 느림보로 불렸답니다."};
    private ImageButton save, exit;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rfinal02, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        rabbit = view.findViewById(R.id.rabbit);
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
                .load("http://49.50.174.179:9000/images/rabbit/5/24_back.jpg")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/24_r.png")
                .into(rabbit);

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
            mp1.setDataSource("http://49.50.174.179:9000/voice/rFinal02_1.MP3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/rFinal02_2.mp3");
            mp2.prepare();
            mp3.setDataSource("http://49.50.174.179:9000/voice/rFinal02_3.mp3");
            mp3.prepare();

        } catch (IOException e) {
            e.printStackTrace();
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
                if(!((Setting_data) getContext().getApplicationContext()).isRecordPlay()){
                    mp2.start();
                }
                subtitles.setText(subs[1]);
            }
        }, a);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                if(!((Setting_data) getContext().getApplicationContext()).isRecordPlay()){
                    mp3.start();
                }
                subtitles.setText(subs[2]);
                t = true;

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
                    saveDialog = new Save_Dialog(getActivity(),book_no, "토끼와 거북이", 1, recordList, "http://49.50.174.179:9000/images/cover/rabbit_ending02.png",true);
                    saveDialog.show();
                    Log.d("record >>>>>>>> ", String.valueOf(recordList));
                    ((Setting_data) getContext().getApplicationContext()).setRecord(false);
                }
                else {
                    saveDialog = new Save_Dialog(getActivity(), "토끼와 거북이",1,myList,"http://49.50.174.179:9000/images/cover/rabbit_ending02.png");
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
