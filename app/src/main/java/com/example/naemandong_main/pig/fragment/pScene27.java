package com.example.naemandong_main.pig.fragment;

import android.content.Intent;
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
import com.example.naemandong_main.pig.activity.Pig07;
import com.example.naemandong_main.pig.activity.Pig08;

public class pScene27 extends Fragment {

    private View view;
    private ImageView background, pig, wolf, bg2, wolf2;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"늑대 \"드디어 배를 채울 수 있게 되었구나!! 으하하!!\"", "“으악!!!” 첫째 돼지와 둘째 돼지는 늑대에게 잡아먹히고 말았어요.", "하지만 여전히 배가 차지 않은 늑대는 셋째 돼지의 집으로 향했어요." };
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene27, container,false);

        background = view.findViewById(R.id.background);
        pig = view.findViewById(R.id.pig);
        wolf = view.findViewById(R.id.wolf);
        wolf2 = view.findViewById(R.id.wolf2);
        bg2 = view.findViewById(R.id.bg2);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/14_bg-01.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/15_wolf-01.png")
                .into(wolf);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/14_pg1-01.png")
                .into(pig);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/15_bg2-01.png")
                .into(bg2);

        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                wolf.setImageResource(0);
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/pig/1/15_pg2.png")
                        .into(pig);
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/pig/1/15_wolf-01.png")
                        .into(wolf2);
                subtitles.setText(subs[1]);
            }
        }, 3100);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                wolf2.setImageResource(0);
                bg2.setImageResource(0);
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/pig/1/16_wolf-01.png")
                        .into(pig);
                subtitles.setText(subs[2]);
                next.setVisibility(View.VISIBLE);
            }
        }, 6100);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((Pig07)getActivity()).play) {
                    if (((Pig07) getActivity()).getData() == 0) {
                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig08.class);
                        intent.putExtra("play", true);
                        startActivity(intent);
                        getActivity().finish();
                    } else {
//                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig02.class);
//                        intent.putExtra("play",true);
//                        startActivity(intent);
//                        getActivity().finish();
                    }
                }
                else{
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    pScene28 pscene28 = new pScene28();
                    transaction.replace(R.id.frame, pscene28);
                    transaction.commit();  //저장
                }
            }
        });

        return view;
    }
}
