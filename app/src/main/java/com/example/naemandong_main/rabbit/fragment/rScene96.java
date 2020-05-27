package com.example.naemandong_main.rabbit.fragment;

import android.content.Intent;
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
import com.example.naemandong_main.rabbit.activity.Rabbit38;
import com.example.naemandong_main.rabbit.activity.Rabbit39;
import com.example.naemandong_main.rabbit.activity.Rabbit40;

public class rScene96 extends Fragment {

    private AnimationDrawable frameLion;
    private View view;
    private ImageView background, box, lion, lion2;
    private TextView subtitles;
    private String subs[] = {"우다다다 얼마나 뛰었을까 갈림길이 나왔어요.", "“으음? 어디로 가야 하지?”"};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene96, container, false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        lion = view.findViewById(R.id.lion);
        lion2 = view.findViewById(R.id.lion2);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/13_back.png")
                .into(background);

        lion.setBackgroundResource(R.drawable.lion_backgo);
        frameLion = (AnimationDrawable) lion.getBackground();

        frameLion.start();


        subtitles.setText(subs[0]);


        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                lion.setBackgroundResource(0);

                lion2.setBackgroundResource(R.drawable.lion_ggauddung);
                frameLion = (AnimationDrawable) lion2.getBackground();

                frameLion.start();
                subtitles.setText(subs[1]);
            }
        }, 3000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 6000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((Rabbit38) getActivity()).play) {
                    if (((Rabbit38) getActivity()).getData() == 0) {
                        ((Rabbit38) getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit40.class);
                        intent.putExtra("play", true);
                        startActivity(intent);
                        getActivity().finish();
                    } else {
                        ((Rabbit38) getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit39.class);
                        intent.putExtra("play", true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                } else {
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    rScene97 rscene97 = new rScene97();
                    transaction.replace(R.id.frame, rscene97);
                    transaction.commit();  //저장
                }
            }
        });

        return view;
    }
}
