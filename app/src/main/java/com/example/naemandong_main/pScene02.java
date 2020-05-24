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
//        lion.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit02.class);
//                startActivity(intent);
//            }
//        });
//        sloth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit02.class);
//                startActivity(intent);
//            }
//        });

        return view;
    }
}
