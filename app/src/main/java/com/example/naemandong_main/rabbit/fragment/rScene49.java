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
import com.example.naemandong_main.rabbit.activity.Rabbit18;
import com.example.naemandong_main.rabbit.activity.Rabbit26;

public class rScene49 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    private View view;
    private ImageView background;
    private ImageButton rabbit, lion;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene49, container,false);

        background = view.findViewById(R.id.background);
        rabbit = view.findViewById(R.id.rabbit);
        lion = view.findViewById(R.id.lion);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/65_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/65_rabbit.png")
                .into(rabbit);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/65_lion.png")
                .into(lion);

//        try {
//            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene09.mp3");
//            mp1.prepare();
//            mp1.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        rabbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Rabbit18)getActivity()).setMylist(0);
                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit02.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        lion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Rabbit18)getActivity()).setMylist(1);
                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit26.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}
