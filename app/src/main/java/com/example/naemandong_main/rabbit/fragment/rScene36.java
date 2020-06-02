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
import com.example.naemandong_main.Setting;
import com.example.naemandong_main.rabbit.activity.Rabbit11;
import com.example.naemandong_main.rabbit.activity.Rabbit12;
import com.example.naemandong_main.rabbit.activity.Rabbit13;

import java.io.IOException;

public class rScene36 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    private View view;
    private ImageView background;
    private ImageButton alone, together;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene36, container,false);

        background = view.findViewById(R.id.background);
        alone = view.findViewById(R.id.alone);
        together = view.findViewById(R.id.together);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/39_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/39_1.png")
                .into(alone);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/39_2.png")
                .into(together);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene36.mp3");
            mp1.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mp1.start();


        alone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Rabbit11)getActivity()).setMylist(0);
                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit12.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        together.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Rabbit11)getActivity()).setMylist(1);
                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit13.class);
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
