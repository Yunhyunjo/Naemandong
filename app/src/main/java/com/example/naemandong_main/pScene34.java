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

public class pScene34 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    private View view;
    private ImageView background;
    private ImageButton mom, chimney;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene34, container,false);

        background = view.findViewById(R.id.background);
        mom = view.findViewById(R.id.mom);
        chimney = view.findViewById(R.id.chimney);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/22_bg-01.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/22_selc1-01.png")
                .into(mom);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/22_selc2-01.png")
                .into(chimney);

//        try {
//            mp1.setDataSource("http://49.50.174.179:9000/voice/pig/pScene14.mp3");
//            mp1.prepare();
//            mp1.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        mom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Pig08)getActivity()).setMylist(0);
                Intent intent = new Intent(getActivity().getApplicationContext(), Pig09.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        chimney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Pig08)getActivity()).setMylist(1);
                Intent intent = new Intent(getActivity().getApplicationContext(), Pig10.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}
