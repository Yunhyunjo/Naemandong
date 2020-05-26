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
import com.example.naemandong_main.rabbit.activity.Rabbit34;

import java.util.ArrayList;

public class rScene87 extends Fragment {

    private ArrayList<Integer> myList;
    private AnimationDrawable frameAnimation1;
    private View view;
    private ImageView background, box, spider ,lion, front;
    private TextView subtitles;
    private String subs [] = {"그런데 달려가던 사자 앞에 거미가 나타났어요.","\"으아아악! 거미다! 거미 너무 무서워!\""};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene87, container,false);

        background = view.findViewById(R.id.background);
        front = view.findViewById(R.id.front);
        box = view.findViewById(R.id.subtitlebox);
        spider = view.findViewById(R.id.spider);
        lion = view.findViewById(R.id.lion);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        myList = (ArrayList<Integer>) ((Rabbit34)getActivity()).getMylist().clone();
        ((Rabbit34)getActivity()).clearList();

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/98_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/original/7_front.png")
                .into(front);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/8/97_front.png")
                .into(lion);

        final Animation liongo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene87_right);
        final Animation lion_left = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene87_left);
        final Animation spider_ani = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene87_spider);

        lion.setBackgroundResource(R.drawable.lion_s86);
        frameAnimation1 = (AnimationDrawable) lion.getBackground();
        frameAnimation1.start();

        subtitles.setText(subs[0]);
        lion.startAnimation(liongo);
        spider.startAnimation(spider_ani);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                lion.setBackgroundResource(R.drawable.lion_s87);
                frameAnimation1 = (AnimationDrawable) lion.getBackground();
                frameAnimation1.start();
                lion.startAnimation(lion_left);
                subtitles.setText(subs[1]);
            }
        }, 5000);
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
                rFinal09 rfinal09 = new rFinal09();
                rfinal09.setArguments(bundle);
                transaction.replace(R.id.frame,rfinal09);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
