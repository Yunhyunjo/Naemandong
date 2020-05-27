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
import com.example.naemandong_main.rabbit.activity.Rabbit35;
import com.example.naemandong_main.rabbit.activity.Rabbit36;
import com.example.naemandong_main.rabbit.activity.Rabbit37;

public class rScene88 extends Fragment {

    private AnimationDrawable frameAnimation1;
    private View view;
    private ImageView background, box, turtle,lion, lion2, front;
    private TextView subtitles;
    private String subs [] = {"\"사자가 잘 때 얼른 지나가야지!\"", "거북이는 사자를 깨우지 않기로 했어요.","\"사자보다 빠르게 갈 수 있는 방법이 없을까?\""};
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

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/8_back.jpg")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/8_front_rabbit.png")
                .into(front);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/90_2.png")
                .into(lion2);

        turtle.setBackgroundResource(R.drawable.turtle_doridori);
        frameAnimation1 = (AnimationDrawable) turtle.getBackground();

        subtitles.setText(subs[0]);
        frameAnimation1.start();
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
                subtitles.setText(subs[2]);
            }
        }, 9000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 13000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((Rabbit35)getActivity()).play){
                    if(((Rabbit35)getActivity()).getData() == 0){
                        ((Rabbit35)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit36.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else {
                        ((Rabbit35)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit37.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                else{
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    rScene89 rscene89 = new rScene89();
                    transaction.replace(R.id.frame,rscene89);
                    transaction.commit();  //저장
                }
            }
        });

        return view;
    }
}
