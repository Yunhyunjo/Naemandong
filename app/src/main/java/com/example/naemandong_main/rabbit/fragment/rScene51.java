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
import com.example.naemandong_main.rabbit.activity.Rabbit19;
import com.example.naemandong_main.rabbit.activity.Rabbit20;

public class rScene51 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    private View view;
    private ImageView background;
    private ImageButton potion, map;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene51, container,false);

        background = view.findViewById(R.id.background);
        potion = view.findViewById(R.id.potion);
        map = view.findViewById(R.id.map);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/56_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/56_potion.png")
                .into(potion);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/56_map.png")
                .into(map);

//        try {
//            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene09.mp3");
//            mp1.prepare();
//            mp1.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        potion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Rabbit19)getActivity()).setMylist(0);
                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit20.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
//        lion.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((Rabbit17)getActivity()).setMylist(0);
//                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit10.class);
//                startActivity(intent);
//                getActivity().finish();
//            }
//        });

        return view;
    }
}
