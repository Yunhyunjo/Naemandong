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
import com.example.naemandong_main.rabbit.activity.Rabbit07;
import com.example.naemandong_main.rabbit.activity.Rabbit08;
import com.example.naemandong_main.rabbit.activity.Rabbit09;

import java.io.IOException;

public class rScene26 extends Fragment {

    private View view;
    MediaPlayer mp1 = new MediaPlayer();
    private ImageView background;
    private ImageButton yes, no;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene26, container,false);

        background = view.findViewById(R.id.background);
        yes = view.findViewById(R.id.yes);
        no = view.findViewById(R.id.no);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/27_fin.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/27_eat.png")
                .into(yes);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/27_noneat.png")
                .into(no);

        try {
            mp1.setDataSource("http://49.50.174.179:9000/voice/rScene26.mp3");
            mp1.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mp1.start();

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Rabbit07)getActivity()).setMylist(0);
                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit09.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Rabbit07)getActivity()).setMylist(1);
                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit08.class);
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
