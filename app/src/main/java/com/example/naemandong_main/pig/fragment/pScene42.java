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

import java.io.IOException;

public class pScene42 extends Fragment {

    AnimationDrawable frameAnimation;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    private View view;
    private ImageView background, top_back, wolf, chimney, box;
    private ImageButton next;
    private TextView subtitles;
    private String subs[] = {"\"크크크. 기다려라 막내 돼지야! 늑대님이 내려가신다!\"", "막내 돼지는 굴뚝 아래에 뾰족뾰족 송곳을 두었어요."};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene42, container,false);
        box = view.findViewById(R.id.subtitlebox);

        background = view.findViewById(R.id.background);
        top_back = view.findViewById(R.id.top_back);
        wolf = view.findViewById(R.id.wolf);
        chimney = view.findViewById(R.id.chimney);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/pigpig6.png")
                .into(background);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/pigpig2.png")
                .into(top_back);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/pigpig3.png")
                .into(wolf);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/pigpig4.png")
                .into(chimney);

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
            mp1.setDataSource("http://49.50.174.179:9000/voice/pig/pScene42_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/pig/pScene42_2.mp3");
            mp2.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();


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
                if (((Setting_data) getContext().getApplicationContext()).isRecord()) {
                    subtitles.setVisibility(View.INVISIBLE);
                    box.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(getActivity(), Record.class);
                    startActivity(intent);
                }
                next.setVisibility(View.VISIBLE);
            }
        }, b);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                pScene43 pscene43 = new pScene43();
                transaction.replace(R.id.frame, pscene43);
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
        if (recordmp != null) recordmp.release();
    }

}
