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
import com.example.naemandong_main.pig.activity.Pig02;
import com.example.naemandong_main.pig.activity.Pig03;
import com.example.naemandong_main.pig.activity.Pig06;
import com.example.naemandong_main.pig.activity.Pig36;

import java.io.IOException;

public class pScene07 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    private View view;
    private ImageView background;
    private ImageButton tree, steel, blanket;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene07, container,false);

        background = view.findViewById(R.id.background);
        tree = view.findViewById(R.id.tree);
        steel = view.findViewById(R.id.steel);
        blanket = view.findViewById(R.id.blanket);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/8_bg-01.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/8_tree-01.png")
                .into(tree);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/8_steel-01.png")
                .into(steel);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/8_blanket-01.png")
                .into(blanket);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/pig/pScene07.mp3");
            mp1.prepare();
            mp1.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        steel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Pig02)getActivity()).setMylist(0);
                Intent intent = new Intent(getActivity().getApplicationContext(), Pig03.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        tree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Pig02)getActivity()).setMylist(1);
                Intent intent = new Intent(getActivity().getApplicationContext(), Pig06.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        blanket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Pig02)getActivity()).setMylist(2);
                Intent intent = new Intent(getActivity().getApplicationContext(), Pig36.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}
