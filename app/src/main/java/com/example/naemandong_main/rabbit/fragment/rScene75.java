package com.example.naemandong_main.rabbit.fragment;

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

public class rScene75 extends Fragment {

    private AnimationDrawable frameLion;
    private View view;
    private ImageView background, box, turtle, front, car;
    private TextView subtitles;
    private String subs[] = {"그 때, 거북이는 자동차를 발견했어요.", "“이제 사자를 이길 수 있겠어!”", "“앗! 거북이 너 비겁하게 자동차를 타다니!!!”"};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene75, container, false);

        background = view.findViewById(R.id.background);
        front = view.findViewById(R.id.front);
        car = view.findViewById(R.id.car);
        box = view.findViewById(R.id.subtitlebox);
        turtle = view.findViewById(R.id.turtle);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/83_fin4.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/79_fffront.png")
                .into(front);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/114_car.png")
                .into(car);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/114_find.png")
                .into(turtle);


        subtitles.setText(subs[0]);

        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                Glide.with(getActivity())
                        .load(0)
                        .into(turtle);
                Glide.with(getActivity())
                        .load(0)
                        .into(car);
                Glide.with(getActivity())
                        .load("http://49.50.174.179:9000/images/rabbit/7/114_fin.png")
                        .into(background);
                subtitles.setText(subs[1]);
            }
        }, 3000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
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
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                rScene76 rscene76 = new rScene76();
                transaction.replace(R.id.frame, rscene76);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
