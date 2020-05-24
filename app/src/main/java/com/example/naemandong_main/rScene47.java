package com.example.naemandong_main;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;

import java.io.IOException;

public class rScene47 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    private View view;
    private ImageView background;
    private ImageButton yes, no;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene47, container,false);

        background = view.findViewById(R.id.background);
        yes = view.findViewById(R.id.yes);
        no = view.findViewById(R.id.no);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/63_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/63_go.png")
                .into(yes);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/63_no.png")
                .into(no);

//        try {
//            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene09.mp3");
//            mp1.prepare();
//            mp1.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Rabbit17)getActivity()).setMylist(1);
                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit18.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Rabbit17)getActivity()).setMylist(0);
                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit19.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}
