package com.example.naemandong_main.rabbit.fragment;

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

public class rScene98 extends Fragment {

    private AnimationDrawable frameLion;
    private View view;
    private ImageView background, box, lion, lion2, front;
    private TextView subtitles;
    private String subs[] = {"사자는 오른쪽 길로 가기로 결정했어요.", "“아직 거북이는 오려면 멀었겠지? 역시 너무 느리다니까”", "사자는 다시 산꼭대기까지 달리기 시작했어요."};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene96, container, false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        lion2 = view.findViewById(R.id.lion2);
        front = view.findViewById(R.id.front);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/13_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/110_backright.png")
                .into(front);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/109_lion_front.png")
                .into(lion2);

        subtitles.setText(subs[0]);

        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                Glide.with(getActivity())
                        .load(0)
                        .into(lion2);

                lion2.setBackgroundResource(R.drawable.lion_rightgo);
                subtitles.setText(subs[1]);
            }
        }, 3000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                frameLion = (AnimationDrawable) lion2.getBackground();
                frameLion.start();

                Animation liongo = AnimationUtils.loadAnimation(getActivity(), R.anim.rscene74);
                lion2.startAnimation(liongo);

                subtitles.setText(subs[2]);
            }
        }, 6000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 8000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("fromwhere", 98);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rScene87 rscene87 = new rScene87();
                rscene87.setArguments(bundle);
                transaction.replace(R.id.frame, rscene87);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
