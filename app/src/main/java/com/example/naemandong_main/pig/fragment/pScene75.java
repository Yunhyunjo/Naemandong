package com.example.naemandong_main.pig.fragment;

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
import com.example.naemandong_main.pig.activity.Pig19;
import com.example.naemandong_main.pig.activity.Pig24;

import java.util.ArrayList;

// 땅파기
public class pScene75 extends Fragment {

    AnimationDrawable frameAnimation1, frameAnimation2;
    private View view;
    private ImageView background, pig, wolf;
    private ImageButton next;
    private TextView subtitles;
    private ArrayList<Integer> myList;
    private String subs [] = {"땅파기를 끝낸 늑대는 막내 돼지의 집으로 갔어요.", "이제 잡아먹기만 하면 되는데… 너무 힘들어서 기운이 없어..", "이제 잡아먹기만 하면 되는데… 너무 힘들어서 기운이 없어.."};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene61, container,false);

        pig = view.findViewById(R.id.pig);
        wolf = view.findViewById(R.id.wolf);
        background = view.findViewById(R.id.background);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/24_bg-012.png")
                .into(background);

        pig.setBackgroundResource(R.drawable.pig_s61);
        frameAnimation1 = (AnimationDrawable) pig.getBackground();

        wolf.setBackgroundResource(R.drawable.wolf_s61);
        frameAnimation2 = (AnimationDrawable) wolf.getBackground();

        frameAnimation1.start();
        frameAnimation2.start();

        subtitles.setText(subs[0]);

        myList = (ArrayList<Integer>) ((Pig24)getActivity()).getMylist().clone();
        ((Pig24)getActivity()).clearList();

        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
            }
        }, 3000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[2]);
            }
        }, 4000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 5000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                if (((Pig24)getActivity()).play){
                    bundle.putBoolean("play",true);
                }
                else {
                    bundle.putIntegerArrayList("myList", myList);
                }
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                pFinal08 pfinal08 = new pFinal08();
                pfinal08.setArguments(bundle);
                transaction.replace(R.id.frame,pfinal08);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
