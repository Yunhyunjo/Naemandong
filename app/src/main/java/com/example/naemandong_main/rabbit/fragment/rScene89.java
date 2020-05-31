package com.example.naemandong_main.rabbit.fragment;

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

import com.example.naemandong_main.rabbit.activity.Rabbit35;
import com.example.naemandong_main.rabbit.activity.Rabbit36;
import com.example.naemandong_main.rabbit.activity.Rabbit37;

import java.io.IOException;

public class rScene89 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    private View view;
    private ImageView background;
    private ImageButton skate, bike;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene89, container, false);

        background = view.findViewById(R.id.background);
        skate = view.findViewById(R.id.skate);
        bike = view.findViewById(R.id.bike);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/94_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/101_s.png")
                .into(skate);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/101_b.png")
                .into(bike);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene89.mp3");
            mp1.prepare();
            mp1.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

            mp1.start();

            skate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Rabbit35) getActivity()).setMylist(0);
                    Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit36.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            });
            bike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Rabbit35) getActivity()).setMylist(1);
                    Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit37.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            });

            return view;
        }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mp1 != null) mp1.release();
    }

}
