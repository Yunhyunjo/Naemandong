package com.example.naemandong_main.rabbit.fragment;

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
import com.example.naemandong_main.rabbit.activity.Rabbit21;
import com.example.naemandong_main.rabbit.fragment.rFinal03;

import java.util.ArrayList;

public class rScene56 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    private AnimationDrawable frameAnimation1;
    private View view;
    private ImageView background, box, front, sloth, rabbit;
    private TextView subtitles;
    private String subs [] = {"토끼가 달려와 나무늘보에게 말했어요.", "토끼 \"나무늘보야! 방금 거북이가 결승선을 통과했어!\"", "나무늘보가 신비의 물약을 찾았을 때에는 이미 거북이가 결승선을 통과한 뒤였어요."};
    private ImageButton next;
    Handler delayHandler = new Handler();
    private ArrayList<Integer> myList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene56, container,false);

        background = view.findViewById(R.id.background);
        front = view.findViewById(R.id.front);
        box = view.findViewById(R.id.subtitlebox);
        sloth = view.findViewById(R.id.sloth);
        rabbit = view.findViewById(R.id.rabbit);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/8_back.jpg")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/8_front_rabbit.png")
                .into(front);

        myList = (ArrayList<Integer>) ((Rabbit21)getActivity()).getMylist().clone();
        ((Rabbit21)getActivity()).clearList();

        sloth.setImageResource(R.drawable.right_r1);

//        try {
//            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene10_1.mp3");
//            mp1.prepare();
//            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene10_2.mp3");
//            mp2.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();

        rabbit.setBackgroundResource(R.drawable.rabbit_leftgo);
        frameAnimation1 = (AnimationDrawable) rabbit.getBackground();

        Animation rabbitgo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene56);

        frameAnimation1.start();
        rabbit.startAnimation(rabbitgo);
        subtitles.setText(subs[0]);
        //mp1.start();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
                frameAnimation1.stop();
                //mp2.start();
            }
        }, 3500);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[2]);
            }
        }, 8500);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 15000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                if (((Rabbit21)getActivity()).play){
                    bundle.putBoolean("play",true);
                }
                else {
                    bundle.putIntegerArrayList("myList", myList);
                }
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rFinal03 rfinal03 = new rFinal03();
                rfinal03.setArguments(bundle);
                transaction.replace(R.id.frame,rfinal03);
                transaction.commit();  //저장
            }
        });


        return view;
    }
}
