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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.naemandong_main.R;

public class pScene22 extends Fragment {

    AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, pig1, pig2, grass;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"그 때 완성된 집에서 쉬고 있던 둘째 돼지의 집을 첫째 돼지가 두드렸어요.", "\"둘째 돼지야! 형이야 나 좀 들여보내줘!! 늑대가 쫓아오고 있어!\"" };
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene22, container,false);

        background = view.findViewById(R.id.background);
        pig1 = view.findViewById(R.id.pig1);
        pig2 = view.findViewById(R.id.pig2);
        grass = view.findViewById(R.id.grass);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/10_bg-01.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/10_pg1-01.png")
                .into(pig2);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/10_bg lawn-01.png")
                .into(grass);

        pig1.setBackgroundResource(R.drawable.pig_s6);
        frameAnimation = (AnimationDrawable) pig1.getBackground();
        Animation piggo = AnimationUtils.loadAnimation(getActivity(), R.anim.pscene09);

        frameAnimation.start();
        pig1.startAnimation(piggo);
        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                pig1.setBackgroundResource(0);
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/pig/1/10_pg2-01.png")
                        .into(pig2);
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/pig/1/10_pg2_1.png")
                        .into(pig1);
                frameAnimation.stop();
                subtitles.setText(subs[1]);
            }
        }, 3100);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 5100);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                pScene23 pscene23 = new pScene23();
                transaction.replace(R.id.frame,pscene23);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
