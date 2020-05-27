package com.example.naemandong_main.original.rabbit;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.naemandong_main.R;

public class ori_rabbit07 extends Fragment {

    private View view;
    private ImageView background, box;
    private TextView subtitles;
    private String subs [] = {"“와아 내가 이겼다! 토끼는 느림보다!!”", "열심히 달린 거북이는 결국 토끼보다 먼저 정상에 도착했어요."};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene01, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        subtitles = view.findViewById(R.id.subTitle);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/original/7_fin.png")
                .into(background);

//        try {
//            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene08_1.mp3");
//            mp1.prepare();
//            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene08_2.mp3");
//            mp2.prepare();
//            mp3.setDataSource("http://49.50.174.179:9000/voice/rScene08_3.mp3");
//            mp3.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        int a = mp1.getDuration();
//        int b = mp1.getDuration() + mp2.getDuration();
//        int c = mp1.getDuration() + mp2.getDuration() + mp3.getDuration();
        subtitles.setText(subs[0]);
        //mp1.start();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
                //mp2.start();
            }
        }, 4000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                //mp3.start();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ori_rabbit08 ori_rabbit08 = new ori_rabbit08();
                transaction.replace(R.id.frame, ori_rabbit08);
                transaction.commit();  //저장
            }
        }, 9000);

        return view;
    }
}
