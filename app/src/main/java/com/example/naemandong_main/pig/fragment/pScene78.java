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

public class pScene78 extends Fragment {

    AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, wolf, pig, house, house_inside;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"막내 돼지는 무서웠지만 침착하게 말했어요.", "싫어! 날 잡아먹으려는 거잖아!" };
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene77, container,false);

        background = view.findViewById(R.id.background);
        pig = view.findViewById(R.id.pig);
        house = view.findViewById(R.id.house);
        house_inside = view.findViewById(R.id.house_inside);
        wolf = view.findViewById(R.id.wolf);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/19_bg-01.png")
                .into(background);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/20_pg-01-01.png")
                .into(pig);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/19_house-03.png")
                .into(house);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/19_houseinside-03.png")
                .into(house_inside);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/20_wolf1 (1).png")
                .into(wolf);

        /*wolf.setBackgroundResource(R.drawable.wolf_s5);
        frameAnimation = (AnimationDrawable) wolf.getBackground();
        Animation wolfgo = AnimationUtils.loadAnimation(getActivity(), R.anim.pscene05);*/

        subtitles.setText(subs[0]);
       /* frameAnimation.start();
        wolf.startAnimation(wolfgo);*/
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
  //              frameAnimation.stop();
                subtitles.setText(subs[1]);
                next.setVisibility(View.VISIBLE);
            }
        }, 3100);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                pScene79 pscene79 = new pScene79();
                transaction.replace(R.id.frame,pscene79);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
