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
import com.example.naemandong_main.pig.activity.Pig25;
import com.example.naemandong_main.pig.activity.Pig26;
import com.example.naemandong_main.pig.activity.Pig27;
import com.example.naemandong_main.pig.activity.Pig28;
import com.example.naemandong_main.pig.activity.Pig31;
import com.example.naemandong_main.pig.activity.Pig32;

public class pScene98 extends Fragment {

    private View view;
    private ImageView background, background2;
    private ImageButton nyam, fruit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pscene98, container,false);

        background = view.findViewById(R.id.background);
        background2 = view.findViewById(R.id.background2);
        nyam = view.findViewById(R.id.nyam);
        fruit = view.findViewById(R.id.fruit);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/23_example-01.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/24_bg-03.png")
                .into(background2);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/24_sel1-02.png")
                .into(nyam);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/pig/1/24_sel2-01.png")
                .into(fruit);

        nyam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Pig28)getActivity()).setMylist(0);
                Intent intent = new Intent(getActivity().getApplicationContext(), Pig32.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Pig28)getActivity()).setMylist(1);
                Intent intent = new Intent(getActivity().getApplicationContext(), Pig31.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}
