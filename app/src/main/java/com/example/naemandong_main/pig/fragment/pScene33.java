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
import com.example.naemandong_main.pig.activity.Pig08;
import com.example.naemandong_main.pig.activity.Pig09;

public class pScene33 extends Fragment {

    AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, wolf;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"\"우리집은 단단해서 나쁜 늑대 너는 무너뜨릴 수 없어!\"", "저 튼튼한 막내 돼지 집에 어떻게 하면 들어갈 수 있을까" };
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene33, container,false);

        background = view.findViewById(R.id.background);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/20_example-01.png")
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
                next.setVisibility(View.VISIBLE);
            }
        }, 5000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((Pig08)getActivity()).play){
                    if(((Pig08)getActivity()).getData() == 0){
                        ((Pig08)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig09.class);
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
                    pScene34 pscene34 = new pScene34();
                    transaction.replace(R.id.frame, pscene34);
                    transaction.commit();  //저장
                }
            }
        });

        return view;
    }
}
