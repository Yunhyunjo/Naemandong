package com.example.naemandong_main;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;

import java.io.IOException;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class rScene12 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    private View view;
    private ImageView background,center;
    private ImageButton left, right;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene12, container,false);

        background = view.findViewById(R.id.background);
        left = view.findViewById(R.id.left);
        right = view.findViewById(R.id.right);
        center = view.findViewById(R.id.center);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/12_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/12_center.png")
                .into(center);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/12_left.png")
                .into(left);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/12_right.png")
                .into(right);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene12.mp3");
            mp1.prepare();
            mp1.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Rabbit03)getActivity()).setMylist(0);
                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit04.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Rabbit03)getActivity()).setMylist(1);
                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit07.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}
