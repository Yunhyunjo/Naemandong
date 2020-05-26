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
import com.example.naemandong_main.rabbit.activity.Rabbit34;
import com.example.naemandong_main.rabbit.activity.Rabbit32;
import com.example.naemandong_main.rabbit.activity.Rabbit33;

public class rScene84 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    private View view;
    private ImageView background;
    private ImageButton one, two;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene84, container,false);

        background = view.findViewById(R.id.background);
        one = view.findViewById(R.id.one);
        two = view.findViewById(R.id.two);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/94_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/94_one.png")
                .into(one);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/94_two.png")
                .into(two);

        /*try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene09.mp3");
            mp1.prepare();
            mp1.start();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Rabbit32)getActivity()).setMylist(0);
                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit34.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Rabbit32)getActivity()).setMylist(1);
                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit33.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}
