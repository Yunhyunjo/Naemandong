package com.example.naemandong_main;

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
import java.util.ArrayList;

public class rFinal05 extends Fragment {

    private Save_Dialog saveDialog;
    private View view;
    private ImageView background, box, turtle;
    private TextView subtitles;
    private ArrayList<Integer> myList;
    private String subs [] = {"자동차가 너무 재미있던 거북이는 카레이서가 되고 싶다고 생각했어요.","결국 거북이는 카레이서의 꿈을 이루었답니다."};
    private ImageButton save;
    boolean play = false;
    Handler delayHandler = new Handler();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rfinal05, container,false);

        background = view.findViewById(R.id.background);
        box = view.findViewById(R.id.subtitlebox);
        turtle = view.findViewById(R.id.rabbit);
        subtitles = view.findViewById(R.id.subTitle);
        save = view.findViewById(R.id.save);

        if (getArguments() != null){
            myList = getArguments().getIntegerArrayList("myList");
            play = getArguments().getBoolean("play");
            if(!play){
                while(myList.size() < 7)
                    myList.add(3);
            }
        }

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/53_fin.png")
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
            }
        }, 8000);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDialog = new Save_Dialog(getActivity(), "토끼와 거북이",1,myList, "http://49.50.174.179:9000/images/cover/rabbit_ending04.png");
                saveDialog.show();
            }
        });

        return view;
    }
}
