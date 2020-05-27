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
import com.example.naemandong_main.pig.activity.Pig02;
import com.example.naemandong_main.R;
import com.example.naemandong_main.pig.activity.Pig01;
import com.example.naemandong_main.pig.activity.Pig29;
import com.example.naemandong_main.pig.activity.Pig30;

import java.io.IOException;

public class pScene02 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    private View view;
    private ImageView background;
    private ImageButton straw, cottoncandy, cookie;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene02, container,false);

        background = view.findViewById(R.id.background);
        straw = view.findViewById(R.id.straw);
        cottoncandy = view.findViewById(R.id.cottoncandy);
        cookie = view.findViewById(R.id.cookie);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/2_bg-01.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/2_straw-01.png")
                .into(straw);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/2_cottoncandy-01.png")
                .into(cottoncandy);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/2_cookie-01.png")
                .into(cookie);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/pig/pScene02.mp3");
            mp1.prepare();
            mp1.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        straw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Pig01)getActivity()).setMylist(0);
                Intent intent = new Intent(getActivity().getApplicationContext(), Pig02.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        cottoncandy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Pig01)getActivity()).setMylist(1);
                Intent intent = new Intent(getActivity().getApplicationContext(), Pig29.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        cookie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Pig01)getActivity()).setMylist(2);
                Intent intent = new Intent(getActivity().getApplicationContext(), Pig30.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}
