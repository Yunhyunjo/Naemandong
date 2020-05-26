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
import com.example.naemandong_main.pig.activity.Pig15;
import com.example.naemandong_main.pig.activity.Pig16;
import com.example.naemandong_main.pig.activity.Pig19;
import com.example.naemandong_main.pig.activity.Pig20;
import com.example.naemandong_main.pig.activity.Pig21;
import com.example.naemandong_main.pig.activity.Pig24;

public class pScene66 extends Fragment {

    MediaPlayer mp1 = new MediaPlayer();
    private View view;
    private ImageView background, background2, house;
    private ImageButton digging, chimney;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene66, container,false);

        background = view.findViewById(R.id.background);
        background2 = view.findViewById(R.id.background2);
        house = view.findViewById(R.id.house);
        digging = view.findViewById(R.id.mom);
        chimney = view.findViewById(R.id.chimney);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/22_bg-02.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/19_bg-01.png")
                .into(background2);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/20_pig-01.png")
                .into(house);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/22_sel1-02.png")
                .into(digging);
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

        digging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Pig20)getActivity()).setMylist(0);
                Intent intent = new Intent(getActivity().getApplicationContext(), Pig24.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        chimney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Pig20)getActivity()).setMylist(1);
                Intent intent = new Intent(getActivity().getApplicationContext(), Pig21.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}
