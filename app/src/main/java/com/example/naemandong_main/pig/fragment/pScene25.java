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
import com.example.naemandong_main.pig.activity.Pig07;
import com.example.naemandong_main.pig.activity.Pig36;
import com.example.naemandong_main.pig.activity.Pig14;

import java.io.IOException;

// 선택지
// 첫둘 돼지 도망 -> 엄마집? 막돼집?
public class pScene25 extends Fragment {

    private View view;
    private ImageView background;
    private ImageButton mom, pig;
    MediaPlayer mp1 = new MediaPlayer();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene25, container,false);

        background = view.findViewById(R.id.background);
        mom = view.findViewById(R.id.mom);
        pig = view.findViewById(R.id.pig);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/13_bg-01.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/13_to mom-01.png")
                .into(mom);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/13_to pig-02-01.png")
                .into(pig);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/pig/pScene25.mp3");
            mp1.prepare();
            mp1.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


        mom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Pig36)getActivity()).setMylist(0);
                Intent intent = new Intent(getActivity().getApplicationContext(), Pig07.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        pig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Pig36)getActivity()).setMylist(1);
                Intent intent = new Intent(getActivity().getApplicationContext(), Pig14.class);
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
