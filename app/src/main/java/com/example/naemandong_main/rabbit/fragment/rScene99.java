package com.example.naemandong_main.rabbit.fragment;

import android.graphics.drawable.AnimationDrawable;
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
import com.example.naemandong_main.rabbit.activity.Rabbit28;
import com.example.naemandong_main.rabbit.activity.Rabbit40;

import java.util.ArrayList;

public class rScene99 extends Fragment {

    private AnimationDrawable frameLion;
    private View view;
    private ImageView background, box, lion, lion2, front;
    private TextView subtitles;
    private String subs[] = {"“그래! 왼쪽으로 가야지!”", "사자는 열심히 풀 숲을 헤치며 가자 멀리 결승선이 보이기 시작했어요."};
    private ImageButton next;
    private ArrayList<Integer> myList;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene96, container, false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        lion2 = view.findViewById(R.id.lion2);
        front = view.findViewById(R.id.front);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/13_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/110_backright.png")
                .into(front);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/109_lion_front.png")
                .into(lion2);

        subtitles.setText(subs[0]);

        myList = (ArrayList<Integer>) ((Rabbit40) getActivity()).getMylist().clone();
        ((Rabbit40) getActivity()).clearList();

        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                Glide.with(getActivity())
                        .load(0)
                        .into(lion2);

                lion2.setBackgroundResource(R.drawable.lion_leftgo);
                frameLion = (AnimationDrawable) lion2.getBackground();
                frameLion.start();

                Animation liongo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene74);
                lion2.startAnimation(liongo);

                subtitles.setText(subs[1]);
            }
        }, 3000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 7000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                if (((Rabbit40) getActivity()).play) {
                    bundle.putBoolean("play", true);
                } else {
                    bundle.putIntegerArrayList("myList", myList);
                }
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rFinal10 rfinal10 = new rFinal10();
                rfinal10.setArguments(bundle);
                transaction.replace(R.id.frame, rfinal10);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
