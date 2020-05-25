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
import com.example.naemandong_main.rabbit.activity.Rabbit02;
import com.example.naemandong_main.rabbit.activity.Rabbit03;
import com.example.naemandong_main.rabbit.activity.Rabbit10;
import com.example.naemandong_main.rabbit.activity.Rabbit26;
import com.example.naemandong_main.rabbit.activity.Rabbit27;

import java.io.IOException;

public class rScene68 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    private View view;
    private ImageView background;
    private ImageButton bone, fish;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene68, container,false);

        background = view.findViewById(R.id.background);
        bone = view.findViewById(R.id.bone);
        fish = view.findViewById(R.id.fish);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/80_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/80_bone.png")
                .into(bone);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/80_fish.png")
                .into(fish);

        /*try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene09.mp3");
            mp1.prepare();
            mp1.start();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Rabbit26)getActivity()).setMylist(0);
                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit27.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        bone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Rabbit26)getActivity()).setMylist(1);
                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit10.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}
