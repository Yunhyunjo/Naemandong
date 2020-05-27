package com.example.naemandong_main.rabbit.fragment;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
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
import com.example.naemandong_main.rabbit.activity.Rabbit34;

import java.util.ArrayList;

public class rScene85 extends Fragment {

    private ArrayList<Integer> myList;
    private View view;
    private ImageView background, box, turtle,lion, lion2, front;
    private TextView subtitles;
    private String subs [] = {"\"앗 거북아 혹시 네가 날 깨운거니? 정말 고마워. 우리 그냥 같이 들어가자!\"","\"그럴까? 친구와 경쟁하는 것 보다 사이 좋게 같이 들어가는게 더 좋아!\""};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene83, container,false);

        background = view.findViewById(R.id.background);
        front = view.findViewById(R.id.front);
        box = view.findViewById(R.id.subtitlebox);
        turtle = view.findViewById(R.id.turtle);
        lion = view.findViewById(R.id.lion);
        lion2 = view.findViewById(R.id.lion2);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        myList = (ArrayList<Integer>) ((Rabbit34)getActivity()).getMylist().clone();
        ((Rabbit34)getActivity()).clearList();

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/8_back.jpg")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/8_front_rabbit.png")
                .into(front);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/93_b.png")
                .into(lion2);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/95_back.png")
                .into(lion);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/95_front_R.png")
                .into(turtle);

        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
            }
        }, 4000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 9000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                if (((Rabbit34)getActivity()).play){
                    bundle.putBoolean("play",true);
                }
                else {
                    bundle.putIntegerArrayList("myList", myList);
                }
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rFinal08 rfinal08 = new rFinal08();
                rfinal08.setArguments(bundle);
                transaction.replace(R.id.frame,rfinal08);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}