package com.example.naemandong_main;

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

import java.io.IOException;

public class rScene16 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    private View view;
    private ImageView background;
    private ImageButton paper, tube;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene16, container,false);

        background = view.findViewById(R.id.background);
        paper = view.findViewById(R.id.paper);
        tube = view.findViewById(R.id.tube);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/16_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/16_paper.png")
                .into(paper);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/16_tube.png")
                .into(tube);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene16.mp3");
            mp1.prepare();
            mp1.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Rabbit04)getActivity()).setMylist(0);
                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit05.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        tube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Rabbit04)getActivity()).setMylist(1);
                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit06.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}
