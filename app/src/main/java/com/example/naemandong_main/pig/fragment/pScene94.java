package com.example.naemandong_main.pig.fragment;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
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
import com.example.naemandong_main.pig.activity.Pig02;
import com.example.naemandong_main.pig.activity.Pig03;
import com.example.naemandong_main.pig.activity.Pig06;
import com.example.naemandong_main.pig.activity.Pig30;
import com.example.naemandong_main.pig.activity.Pig36;

public class pScene94 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();
    MediaPlayer mp3 = new MediaPlayer();
    private AnimationDrawable frameAnimation;
    private View view;
    private ImageView background, wolf, pig, grass;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"\"아이고 못 참겠다\"", "늑대가 첫째 돼지의 집을 후~ 하고 불자 맛있는 과자집이 날아가 버렸어요.","집이 사라진 첫째 돼지는 결국 둘째 돼지네 집으로 도망쳤어요." };
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene06, container,false);

        background = view.findViewById(R.id.background);
        wolf = view.findViewById(R.id.wolf);
        pig = view.findViewById(R.id.pig);
        grass = view.findViewById(R.id.grass);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/4_example-01.png")
                .into(background);

        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                Glide.with(getContext())
                        .load("http://49.50.174.179:9000/images/pig/1/6-01.png")
                        .into(background);
                subtitles.setText(subs[1]);
            }
        }, 3000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/pig/1/7_bg-01.png")
                        .into(background);
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/pig/1/7_wolf-01.png")
                        .into(wolf);
                pig.setBackgroundResource(R.drawable.pig_s6);
                frameAnimation = (AnimationDrawable) pig.getBackground();
                frameAnimation.start();
                Animation piggo = AnimationUtils.loadAnimation(getActivity(), R.anim.pscene06);
                pig.startAnimation(piggo);
                subtitles.setText(subs[2]);
                mp3.start();
            }
        }, 4000);
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
                if (((Pig30)getActivity()).play){
                    if(((Pig30)getActivity()).getData() == 0){
                        ((Pig30)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig03.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else if (((Pig30)getActivity()).getData() == 1){
                        ((Pig30)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig06.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else {
                        ((Pig30)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Pig36.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                else{
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    pScene104 pscene104 = new pScene104();
                    transaction.replace(R.id.frame, pscene104);
                    transaction.commit();  //저장
                }
            }
        });

        return view;
    }
}
