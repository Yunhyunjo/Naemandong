package com.example.naemandong_main.pig.fragment;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.naemandong_main.R;
import com.example.naemandong_main.Save_Dialog;

import java.util.ArrayList;

// 돼지 셋 엔딩
public class pFinal08 extends Fragment {
    private Save_Dialog saveDialog;
    AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, box, pigs, wolfig;
    private TextView subtitles;
    private ImageButton save, exit;
    private ArrayList<Integer> myList;
    boolean play = false;
    private String subs [] = {"아기 돼지 삼형제와 약속을 한 후, 늑대는 맛있는 저녁을 먹었어요.", "늑대는 그동안 삼형제를 괴롭힌 것을 사과하며 사이 좋은 친구가 되었어요."};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pfinal08, container,false);

        background = view.findViewById(R.id.background);
        subtitles = view.findViewById(R.id.subTitle);
        pigs = view.findViewById(R.id.pigs);
        wolfig = view.findViewById(R.id.wolfig);
        subtitles = view.findViewById(R.id.subTitle);
        save = view.findViewById(R.id.save);
        exit = view.findViewById(R.id.exit);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/25_bg-01.png")
                .into(background);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/25_pig-01.png")
                .into(pigs);

        wolfig.setBackgroundResource(R.drawable.pig_s62);
        frameAnimation = (AnimationDrawable) wolfig.getBackground();
        Animation wolfgo = AnimationUtils.loadAnimation(getActivity(), R.anim.pscene05);

        subtitles.setText(subs[0]);
        frameAnimation.start();
        wolfig.startAnimation(wolfgo);

        if (getArguments() != null){
            myList = getArguments().getIntegerArrayList("myList");
            play = getArguments().getBoolean("play");
            if(!play){
                while(myList.size() < 7)
                    myList.add(3);
            }
        }

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
                    //save.setVisibility(View.VISIBLE);
                    //exit.setVisibility(View.VISIBLE);
                    String a = myList.toString();
                    Toast.makeText(getContext(), a, Toast.LENGTH_SHORT).show();
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
