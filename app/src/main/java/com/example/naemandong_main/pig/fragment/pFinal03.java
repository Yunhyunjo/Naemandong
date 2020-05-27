package com.example.naemandong_main.pig.fragment;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.naemandong_main.R;
import com.example.naemandong_main.Save_Dialog;

import java.util.ArrayList;

// 돌집 막내돼지 혼자 엔딩
public class pFinal03 extends Fragment {
    private Save_Dialog saveDialog;
    AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, box, pig, house, house_inside;
    private TextView subtitles;
    private ImageButton save, exit;
    private ArrayList<Integer> myList;
    boolean play = false;
    private String subs [] = {"막내 돼지가 문을 열어주지 않자 늑대는 아쉬워하며 마을을 떠났어요.", "늑대가 떠나고 난 평화로운 마을에서 막내돼지는 오래오래 행복하게 살았답니다."};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pfinal03, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        pig = view.findViewById(R.id.pig);
        house = view.findViewById(R.id.house);
        house_inside = view.findViewById(R.id.house_inside);
        subtitles = view.findViewById(R.id.subTitle);
        save = view.findViewById(R.id.save);
        exit = view.findViewById(R.id.exit);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/19_bg-01.png")
                .into(background);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/26_house.png")
                .into(house);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/26_houseinside.png")
                .into(house_inside);

        pig.setBackgroundResource(R.drawable.pig_final03);
        frameAnimation = (AnimationDrawable) pig.getBackground();

        if (getArguments() != null){
            myList = getArguments().getIntegerArrayList("myList");
            play = getArguments().getBoolean("play");
            if(!play){
                while(myList.size() < 7)
                    myList.add(3);
            }
        }

        frameAnimation.start();
        subtitles.setText(subs[0]);
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
                box.setVisibility(View.INVISIBLE);
                subtitles.setVisibility(View.INVISIBLE);
                if (!play) {
                    save.setVisibility(View.VISIBLE);
                    exit.setVisibility(View.VISIBLE);
                }
            }
        }, 10000);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDialog = new Save_Dialog(getActivity(), "아기돼지 삼형제",2,myList,"http://49.50.174.179:9000/images/cover/pigcover.png");
                saveDialog.show();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return view;
    }
}
