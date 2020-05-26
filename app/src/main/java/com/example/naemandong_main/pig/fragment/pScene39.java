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
import com.example.naemandong_main.pig.activity.Pig13;
import com.example.naemandong_main.pig.activity.Pig10;
import com.example.naemandong_main.pig.activity.Pig11;
import com.example.naemandong_main.pig.activity.Pig12;

public class pScene39 extends Fragment {

    private View view;
    private ImageView background;
    private ImageButton fire, tack, bed;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene39, container,false);

        background = view.findViewById(R.id.background);
        fire = view.findViewById(R.id.fire);
        tack = view.findViewById(R.id.tack);
        bed = view.findViewById(R.id.bed);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/24_bg-01.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/24_sel1-01-01.png")
                .into(fire);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/24_sel2-01-01.png")
                .into(tack);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/24_sel3-01-01.png")
                .into(bed);

        fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Pig10)getActivity()).setMylist(0);
                Intent intent = new Intent(getActivity().getApplicationContext(), Pig11.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        tack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Pig10)getActivity()).setMylist(1);
                Intent intent = new Intent(getActivity().getApplicationContext(), Pig12.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        bed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Pig10)getActivity()).setMylist(2);
                Intent intent = new Intent(getActivity().getApplicationContext(), Pig13.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}
