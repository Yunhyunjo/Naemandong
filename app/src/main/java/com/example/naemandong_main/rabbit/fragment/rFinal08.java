package com.example.naemandong_main.rabbit.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.naemandong_main.R;
import com.example.naemandong_main.Save_Dialog;

import java.util.ArrayList;

public class rFinal08 extends Fragment {

    private Save_Dialog saveDialog;

    private View view;
    private ImageView background, box;
    private TextView subtitles;
    private ArrayList<Integer> myList;
    private String subs [] = {"그렇게 함께 산꼭대기에 올라간 사자와 거북이는 결국 무승부가 되었어요.","사자와 거북이는 다시는 싸우지 않고 행복하게 지냈답니다."};
    private ImageButton save, exit;
    boolean play = false;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rfinal05, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        subtitles = view.findViewById(R.id.subTitle);
        save = view.findViewById(R.id.save);
        exit = view.findViewById(R.id.exit);

        if (getArguments() != null){
            myList = getArguments().getIntegerArrayList("myList");
            play = getArguments().getBoolean("play");
            if(!play){
                while(myList.size() < 7)
                    myList.add(3);
            }
        }

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/96_fin.png")
                .into(background);

        subtitles.setText(subs[0]);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                subtitles.setText(subs[1]);
            }
        }, 3000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                box.setVisibility(View.INVISIBLE);
                subtitles.setVisibility(View.INVISIBLE);
                if (!play) {
                    save.setVisibility(View.VISIBLE);
                }
                exit.setVisibility(View.VISIBLE);
            }
        }, 8000);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDialog = new Save_Dialog(getActivity(), "토끼와 거북이",1,myList,"http://49.50.174.179:9000/images/rabbit/7/96_fin.png");
                saveDialog.show();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return view;
    }
}