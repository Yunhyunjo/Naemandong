package com.example.naemandong_main.rabbit.fragment;

import android.media.MediaPlayer;
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

import java.io.IOException;

public class rScene02 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    private View view;
    private ImageView background, rabbit, lion, sloth, box, next;
    private TextView subtitles;
    boolean sound, subtitle;
    private String subs [] = {"토끼“내가 이 숲 속에서 제일 빨라. 아무도 나를 이길 수 없을껄?”", "사자“아니야. 내가 이 숲 속의 왕이니까 달리기도 내가 제일 빨라.”","나무늘보“나......도.....달...리...기....”"};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene02, container,false);

        background = view.findViewById(R.id.background);
        rabbit = view.findViewById(R.id.rabbit);
        lion = view.findViewById(R.id.lion);
        sloth = view.findViewById(R.id.sloth);
        box = view.findViewById(R.id.subtitlebox);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene02_1.mp3");
            mp1.prepare();
            mp2.setDataSource("http://49.50.174.179:9000/voice/rScene02_2.mp3");
            mp2.prepare();
            mp3.setDataSource("http://49.50.174.179:9000/voice/rScene02_3.mp3");
            mp3.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int a = mp1.getDuration();
        int b = mp1.getDuration() + mp2.getDuration();
        int c = mp1.getDuration() + mp2.getDuration() + mp3.getDuration();

//        if (getArguments() != null){
//            sound = getArguments().getBoolean("sound");
//            subtitle = getArguments().getBoolean("subtitle");
//        }

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/2/2_back.jpg")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/2/2_rabbit_select.png")
                .into(rabbit);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/2/2_lion_select.png")
                .into(lion);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/2/2_sloth_select.png")
                .into(sloth);

//        if (subtitle) {
//            box.setVisibility(View.VISIBLE);
//            subtitles.setVisibility(View.VISIBLE);
//        }
//        else {
//            box.setVisibility(View.INVISIBLE);
//            subtitles.setVisibility(View.INVISIBLE);
//        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putBoolean("sound",sound);
//                bundle.putBoolean("subtitle",subtitle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rScene03 rscene03 = new rScene03();
//                rscene03.setArguments(bundle);
                transaction.replace(R.id.frame,rscene03);
                transaction.commit();  //저장



            }
        });

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
                subtitles.setText(subs[2]);
                mp3.start();
                //na.startAnimation(n);
            }
        }, b);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, c);



        return view;
    }
}
