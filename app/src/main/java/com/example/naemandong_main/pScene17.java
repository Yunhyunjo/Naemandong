package com.example.naemandong_main;

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

import java.io.IOException;

public class pScene17 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    AnimationDrawable framAnimation;
    private View view;
    private ImageView background;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"한편, 막내 돼지는 둘째 돼지 집에서 불이 나는걸 봤어요.", "막내 돼지 \"어? 둘째 형 집에 불이 났나봐! 어서 가봐야겠다!\""};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene01, container,false);

        background = view.findViewById(R.id.background);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        background.setBackgroundResource(R.drawable.pig_s17);
        framAnimation = (AnimationDrawable) background.getBackground();

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/pig/pScene17_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/pig/pScene17_2.mp3");
            mp2.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();

        framAnimation.start();
        subtitles.setText(subs[0]);
        mp1.start();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
                mp2.start();
            }
        }, a);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, b);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                pScene18 pscene18 = new pScene18();
                transaction.replace(R.id.frame, pscene18);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
