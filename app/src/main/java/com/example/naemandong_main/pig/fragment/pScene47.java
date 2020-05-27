package com.example.naemandong_main.pig.fragment;

import android.content.Intent;
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
import com.example.naemandong_main.pig.activity.Pig08;
import com.example.naemandong_main.pig.activity.Pig14;
import com.example.naemandong_main.pig.activity.Pig15;
import com.example.naemandong_main.pig.activity.Pig20;

// 막돼 재료 선택
public class pScene47 extends Fragment {

    private View view;
    private ImageView background;
    private ImageButton stone, brick, sand;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene28, container,false);

        background = view.findViewById(R.id.background);
        stone = view.findViewById(R.id.stone);
        brick = view.findViewById(R.id.brick);
        sand = view.findViewById(R.id.sand);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/17_bg-01.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/17_stone-01.png")
                .into(stone);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/17_brick-01.png")
                .into(brick);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/17_sand-01.png")
                .into(sand);

        stone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Pig14)getActivity()).setMylist(0);
                Intent intent = new Intent(getActivity().getApplicationContext(), Pig15.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        brick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Pig14)getActivity()).setMylist(1);
                Intent intent = new Intent(getActivity().getApplicationContext(), Pig20.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
//        sloth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit02.class);
//                startActivity(intent);
//            }
//        });

        return view;
    }
}
