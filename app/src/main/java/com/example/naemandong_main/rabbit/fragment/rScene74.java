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
import com.example.naemandong_main.rabbit.activity.Rabbit15;
import com.example.naemandong_main.rabbit.activity.Rabbit28;

import java.util.ArrayList;

public class rScene74 extends Fragment {

    private AnimationDrawable frameLion;
    private View view;
    private ImageView background, box, lion, turtle, front, front2;
    private TextView subtitles;
    private String subs[] = {"사자는 뒤늦게 거북이를 발견하고 서둘러 쫓아갔지만 따라잡지 못했어요.", "“거..거북이가 너무 빨라서 따라잡기 힘들어..헥헥”"};
    private ImageButton next;
    private ArrayList<Integer> myList;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene74, container, false);

        background = view.findViewById(R.id.background);
        front = view.findViewById(R.id.front);
        front2 = view.findViewById(R.id.front2);
        box = view.findViewById(R.id.subtitlebox);
        lion = view.findViewById(R.id.lion);
        turtle = view.findViewById(R.id.turtle);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/86_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/86_right.png")
                .into(front2);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/86_left.png")
                .into(front);
        turtle.setBackgroundResource(R.drawable.bike_tur_small);

        lion.setBackgroundResource(R.drawable.lion_backgo);
        frameLion = (AnimationDrawable) lion.getBackground();

        Animation turtlego = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene74);
        turtle.startAnimation(turtlego);


        frameLion.start();

        subtitles.setText(subs[0]);

        myList = (ArrayList<Integer>) ((Rabbit28) getActivity()).getMylist().clone();
        ((Rabbit28) getActivity()).clearList();

        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
            }
        }, 5000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 8000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                if (((Rabbit28) getActivity()).play) {
                    bundle.putBoolean("play", true);
                } else {
                    bundle.putIntegerArrayList("myList", myList);
                }
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rFinal03 rfinal03 = new rFinal03();
                rfinal03.setArguments(bundle);
                transaction.replace(R.id.frame, rfinal03);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
