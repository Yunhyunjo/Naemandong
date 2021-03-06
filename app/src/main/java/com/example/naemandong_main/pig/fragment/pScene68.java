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
import com.example.naemandong_main.pig.activity.Pig16;
import com.example.naemandong_main.pig.activity.Pig17;
import com.example.naemandong_main.pig.activity.Pig18;
import com.example.naemandong_main.pig.activity.Pig21;
import com.example.naemandong_main.pig.activity.Pig22;
import com.example.naemandong_main.pig.activity.Pig23;

import java.io.IOException;

public class pScene68 extends Fragment {

    private View view;
    private ImageView background, background2;
    private ImageButton fire, tack, bed;
    MediaPlayer mp1 = new MediaPlayer();
    boolean sound, subtitle;
    private ImageView box;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene54, container,false);

        background = view.findViewById(R.id.background);
        background2 = view.findViewById(R.id.background2);
        fire = view.findViewById(R.id.fire);
        bed = view.findViewById(R.id.bed);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/pigscary.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/24_bg-02.png")
                .into(background2);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/24_sel1-01-01.png")
                .into(fire);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/24_sel3-01-01.png")
                .into(bed);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/pig/pScene39.mp3");
            mp1.prepare();
            mp1.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Pig21)getActivity()).setMylist(0);
                Intent intent = new Intent(getActivity().getApplicationContext(), Pig22.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        bed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Pig21)getActivity()).setMylist(1);
                Intent intent = new Intent(getActivity().getApplicationContext(), Pig23.class);
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
