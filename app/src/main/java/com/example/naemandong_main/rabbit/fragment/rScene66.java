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

public class rScene66 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    private AnimationDrawable frameTurtle, frameLion;
    private View view;
    private ImageView background, box, lion, turtle;
    private TextView subtitles;
    private String subs [] = {"탕 소리와 함께 사자와 거북이는 달려가기 시작했어요.", "동물의 왕 사자가 나가신다 길을 비켜라~","사자는 시작과 동시에 거북이 보다 훌쩍 앞서기 시작했어요."};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene66, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);
        lion = view.findViewById(R.id.lion);
        turtle = view.findViewById(R.id.turtle);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/78_back.png")
                .into(background);

        /*try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene06_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene06_2.mp3");
            mp2.prepare();
            mp3.setDataSource("http://49.50.174.179:9000/voice/rScene06_3.mp3");
            mp3.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();
        int c = mp1.getDuration() + mp2.getDuration() + mp3.getDuration();
*/

        turtle.setBackgroundResource(R.drawable.turtle_rightgo);
        frameTurtle = (AnimationDrawable) turtle.getBackground();
        lion.setBackgroundResource(R.drawable.lion_rightgo);
        frameLion = (AnimationDrawable) lion.getBackground();

        Animation turtlego = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene07_turtle);
        Animation liongo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene66_lion);

        frameTurtle.start();
        turtle.startAnimation(turtlego);
        frameLion.start();
        lion.startAnimation(liongo);
        subtitles.setText(subs[0]);
 //       mp1.start();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
 //               mp2.start();
            }
        }, 5000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[2]);
 //               mp3.start();
            }
        }, 8000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 10000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rScene67 rscene67 = new rScene67();
                transaction.replace(R.id.frame,rscene67);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
