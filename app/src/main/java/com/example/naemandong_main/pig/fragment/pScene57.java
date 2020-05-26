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

public class pScene57 extends Fragment {

    AnimationDrawable frameAnimation;
    private View view;
    private ImageView background;
    private ImageButton next;
    private TextView subtitles;
    private String subs [] = {"크크크 기다려라 돼지들아! 늑대님이 내려가신다!", "돼지 삼형제는 굴뚝 아래에 아주 푹신한 침대를 두었어요.", "이제 거의 다 내려온 것 같은데.."};
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene33, container,false);

        background = view.findViewById(R.id.background);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/25_example-01.png")
                .into(background);

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
                next.setVisibility(View.VISIBLE);
            }
        }, 5000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                pScene58 pscene58 = new pScene58();
                transaction.replace(R.id.frame, pscene58);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
