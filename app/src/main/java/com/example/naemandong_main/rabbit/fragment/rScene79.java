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
import com.example.naemandong_main.rabbit.activity.Rabbit30;
import com.example.naemandong_main.rabbit.activity.Rabbit31;
import com.example.naemandong_main.rabbit.activity.Rabbit38;

import java.io.IOException;

public class rScene79 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    private View view;
    private ImageView background;
    private ImageButton yes, no;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene79, container,false);

        background = view.findViewById(R.id.background);
        yes = view.findViewById(R.id.yes);
        no = view.findViewById(R.id.no);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/89_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/89_sleep.png")
                .into(yes);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/89_nonsleep.png")
                .into(no);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene79.mp3");
            mp1.prepare();
            mp1.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mp1.start();
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Rabbit30)getActivity()).setMylist(0);
                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit31.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Rabbit30) getActivity()).setMylist(1);
                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit38.class);
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
