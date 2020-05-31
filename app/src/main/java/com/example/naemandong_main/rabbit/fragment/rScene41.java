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
import com.example.naemandong_main.rabbit.activity.Rabbit14;
import com.example.naemandong_main.rabbit.activity.Rabbit15;
import com.example.naemandong_main.rabbit.activity.Rabbit16;

import java.io.IOException;

public class rScene41 extends Fragment {

    private View view;
    MediaPlayer mp1 = new MediaPlayer();
    private ImageView background;
    private ImageButton bike, car;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene41, container,false);

        background = view.findViewById(R.id.background);
        bike = view.findViewById(R.id.bike);
        car = view.findViewById(R.id.car);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/46_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/46_bike.png")
                .into(bike);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/46_car.png")
                .into(car);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene41.mp3");
            mp1.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mp1.start();


        bike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Rabbit14)getActivity()).setMylist(0);
                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit15.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Rabbit14)getActivity()).setMylist(1);
                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit16.class);
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
