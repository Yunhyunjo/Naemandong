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

// 땅파기
public class pScene74 extends Fragment {

    AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, house, wolf;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"어떻게 막내 돼지의 집으로 들어갈지 고민하던 늑대는 땅을 파기로 했어요.", "이 땅파기 대장 늑대님의 실력을 보여줄 때가 왔군.", "흐흐흐, 얼른 땅을 파고 막내 돼지의 집으로 들어가서 다 잡아 먹어버리겠어."};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene74, container,false);

        background = view.findViewById(R.id.background);
        house = view.findViewById(R.id.house);
        wolf = view.findViewById(R.id.wolf);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/23_bg (1).png")
                .into(background);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/23_pigs-01.png")
                .into(house);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/23_wolf.png")
                .into(wolf);

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
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                pScene75 pscene75 = new pScene75();
                transaction.replace(R.id.frame, pscene75);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
