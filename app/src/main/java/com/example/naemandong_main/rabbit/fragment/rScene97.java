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
import com.example.naemandong_main.rabbit.activity.Rabbit23;
import com.example.naemandong_main.rabbit.activity.Rabbit24;
import com.example.naemandong_main.rabbit.activity.Rabbit25;
import com.example.naemandong_main.rabbit.activity.Rabbit38;
import com.example.naemandong_main.rabbit.activity.Rabbit39;
import com.example.naemandong_main.rabbit.activity.Rabbit40;

import java.io.IOException;

public class rScene97 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    private View view;
    private ImageView background, center;
    private ImageButton left, right;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene97, container, false);

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

        /*try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene12.mp3");
            mp1.prepare();
            mp1.start();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Rabbit38) getActivity()).setMylist(0);
                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit40.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Rabbit38) getActivity()).setMylist(1);
                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit39.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}
