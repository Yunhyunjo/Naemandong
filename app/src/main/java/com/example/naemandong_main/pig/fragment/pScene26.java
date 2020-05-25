package com.example.naemandong_main.pig.fragment;

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

// 선택지 후
public class pScene26 extends Fragment {

    private View view;
    private ImageView background, pig, memo, wolf;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"집이 무너진 첫째 돼지와 둘째 돼지는 엄마 돼지의 집으로 도망쳤어요.", "메모 – 엄마 여행 갔다올게 ~ 밥 잘 먹고 사이좋게 있으렴~","그러나 엄마 돼지의 집에는 짧은 편지만 붙어 있었어요." };
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene26, container,false);

        background = view.findViewById(R.id.background);
        pig = view.findViewById(R.id.pig);
        memo = view.findViewById(R.id.memo);
        wolf = view.findViewById(R.id.wolf);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/14_bg-01.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/14_pg1-01.png")
                .into(pig);

        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/pig/1/14_memo-01.png")
                        .into(memo);
                subtitles.setText(subs[1]);
            }
        }, 3100);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[2]);
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/pig/1/14_wolf1.png")
                        .into(wolf);
                next.setVisibility(View.VISIBLE);
            }
        }, 6100);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                pScene27 pscene27 = new pScene27();
                transaction.replace(R.id.frame,pscene27);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
