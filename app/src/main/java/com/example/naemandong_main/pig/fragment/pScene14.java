package com.example.naemandong_main.pig.fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.naemandong_main.R;
import com.example.naemandong_main.pig.activity.Pig03;
import com.example.naemandong_main.pig.activity.Pig04;
import com.example.naemandong_main.pig.activity.Pig05;

import java.io.IOException;

public class pScene14 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    private View view;
    private ImageView background;
    private ImageButton blue, red;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene14, container,false);

        background = view.findViewById(R.id.background);
        blue = view.findViewById(R.id.blue);
        red = view.findViewById(R.id.red);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/13_BG-01.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/13_REDBOX-01.png")
                .into(red);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/13_BLUEBOX-01.png")
                .into(blue);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/pig/pScene14.mp3");
            mp1.prepare();
            mp1.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Pig03)getActivity()).setMylist(0);
                Intent intent = new Intent(getActivity().getApplicationContext(), Pig04.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Pig03)getActivity()).setMylist(1);
                Intent intent = new Intent(getActivity().getApplicationContext(), Pig05.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}
