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
import com.example.naemandong_main.pig.activity.Pig06;
import com.example.naemandong_main.pig.activity.Pig07;
import com.example.naemandong_main.pig.activity.Pig14;
import com.example.naemandong_main.pig.activity.Pig15;
import com.example.naemandong_main.pig.activity.Pig20;
import com.example.naemandong_main.pig.activity.Pig35;

import java.io.IOException;

// 첫둘 돼지 막돼집으로 가기로함
public class pScene46 extends Fragment {

    AnimationDrawable frameAnimation;
    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer recordmp = new MediaPlayer();
    private View view;
    private ImageView background, pigs, wolf, tree, box;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"첫째 돼지와 둘째 돼지는 막내 돼지의 집으로 도망쳤어요."};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene46, container,false);
        box = view.findViewById(R.id.subtitlebox);

        background = view.findViewById(R.id.background);
        tree = view.findViewById(R.id.tree);
        wolf = view.findViewById(R.id.wolf);
        pigs = view.findViewById(R.id.pigs);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/19_bg-01.png")
                .into(background);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/treecollapse.png")
                .into(tree);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/20_wolf1 (1).png")
                .into(wolf);

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
            mp1.setDataSource("http://49.50.174.179:9000/voice/pig/pScene46_1.mp3");
            mp1.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int a = mp1.getDuration();


        pigs.setBackgroundResource(R.drawable.pig_s46);
        frameAnimation = (AnimationDrawable) pigs.getBackground();
        Animation pigsgo = AnimationUtils.loadAnimation(getActivity(), R.anim.pscene46);

        frameAnimation.start();
        pigs.startAnimation(pigsgo);

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
                frameAnimation.stop();
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
        }, 5000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((Pig14)getActivity()).play){
                    if(((Pig14)getActivity()).getData() == 0){
                        ((Pig14)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig15.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    } else if (((Pig14) getActivity()).getData() == 1) {
                        ((Pig14)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig20.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    } else {
                        ((Pig14) getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig35.class);
                        intent.putExtra("play", true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                else{
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    pScene47 pscene47 = new pScene47();
                    transaction.replace(R.id.frame, pscene47);
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
        if (recordmp != null) recordmp.release();
    }

}
