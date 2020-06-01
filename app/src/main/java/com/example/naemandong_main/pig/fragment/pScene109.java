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
import com.example.naemandong_main.pig.activity.Pig28;
import com.example.naemandong_main.pig.activity.Pig31;
import com.example.naemandong_main.pig.activity.Pig32;
import com.example.naemandong_main.pig.activity.Pig33;
import com.example.naemandong_main.pig.activity.Pig34;

import java.io.IOException;

public class pScene109 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    private View view;
    private ImageView background, background2;
    private ImageButton cotton, rock;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene98, container,false);

        background = view.findViewById(R.id.background);
        background2 = view.findViewById(R.id.background2);
        cotton = view.findViewById(R.id.nyam);
        rock = view.findViewById(R.id.fruit);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/29_example.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/32_bg-01.png")
                .into(background2);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/32_sel1-01.png")
                .into(cotton);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/32_sel2-01.png")
                .into(rock);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/pig/pScene109.mp3");
            mp1.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp1.start();

        cotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Pig32)getActivity()).setMylist(0);
                Intent intent = new Intent(getActivity().getApplicationContext(), Pig33.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Pig32)getActivity()).setMylist(1);
                Intent intent = new Intent(getActivity().getApplicationContext(), Pig34.class);
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
