package com.example.naemandong_main.original.pig;

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
import com.example.naemandong_main.pig.fragment.pScene64;

// 첫둘 돼지 막돼집으로 가기로함
public class ori_pig11 extends Fragment {

    AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, pigs, pig, house, house_inside;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"그 때 완성된 집에서 쉬고 있던 막내 돼지의 집을 첫째, 둘째 돼지가 두드렸어요.", "\"막내야! 형들이야 우리 좀 들여보내줘!! 늑대가 우리 집을 무너뜨리고 잡아먹으려고 해!\"", "\"형들 괜찮아? 어서 들어와!!\""};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene63, container,false);

        background = view.findViewById(R.id.background);
        pigs = view.findViewById(R.id.pigs);
        pig = view.findViewById(R.id.pig);
        house = view.findViewById(R.id.house);
        house_inside = view.findViewById(R.id.house_inside);

        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/19_bg-01.png")
                .into(background);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/19_house-03.png")
                .into(house);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/19_houseinside-03.png")
                .into(house_inside);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/19_pg-01.png")
                .into(pig);

        pigs.setBackgroundResource(R.drawable.pig_s46);
        frameAnimation = (AnimationDrawable) pigs.getBackground();
        Animation pigsgo = AnimationUtils.loadAnimation(getActivity(), R.anim.pscene46);

        frameAnimation.start();
        pigs.startAnimation(pigsgo);

        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
            }
        }, 3100);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                frameAnimation.stop();
                subtitles.setText(subs[2]);
            }
        }, 5100);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ori_pig12 pig12 = new ori_pig12();
                transaction.replace(R.id.frame, pig12);
                transaction.commit();  //저장
            }
        }, 8000);


        return view;
    }
}
