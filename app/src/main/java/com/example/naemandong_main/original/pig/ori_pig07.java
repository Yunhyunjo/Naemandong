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
import com.example.naemandong_main.pig.fragment.pScene23;

public class ori_pig07 extends Fragment {

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
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ori_pig08 pig08 = new ori_pig08();
                transaction.replace(R.id.frame, pig08);
                transaction.commit();  //저장
            }
        }, 6000);


        return view;
    }
}
