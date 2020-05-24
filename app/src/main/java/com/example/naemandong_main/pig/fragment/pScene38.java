package com.example.naemandong_main.pig.fragment;

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
import com.example.naemandong_main.pig.activity.Pig10;
import com.example.naemandong_main.pig.activity.Pig11;

public class pScene38 extends Fragment {

    AnimationDrawable frameAnimation;
    private View view;
    private ImageView background;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"그때 늑대의 눈에 굴뚝이 들어왔어요.", "늑대 \"좋아, 저 굴뚝을 통해 집으로 들어가면 돼! 기다려라 막내 돼지야!\"", "막내 돼지 \"어떡하지? 늑대가 굴뚝을 통해 집으로 들어오려고 하잖아!\""};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene33, container,false);

        background = view.findViewById(R.id.background);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/23.png")
                .into(background);

        subtitles.setText(subs[0]);
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
                if (((Pig10)getActivity()).play){
                    if(((Pig10)getActivity()).getData() == 0){
                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig11.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
//                    else {
//                        Intent intent = new Intent(getActivity().getApplicationContext(),Pig05.class);
//                        intent.putExtra("play",true);
//                        startActivity(intent);
//                        getActivity().finish();
//                    }
                }
                else{
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    pScene39 pscene39 = new pScene39();
                    transaction.replace(R.id.frame, pscene39);
                    transaction.commit();  //저장
                }
            }
        });

        return view;
    }
}
