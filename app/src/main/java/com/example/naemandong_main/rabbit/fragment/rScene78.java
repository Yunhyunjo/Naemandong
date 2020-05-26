package com.example.naemandong_main.rabbit.fragment;

import android.content.Intent;
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
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.naemandong_main.R;
import com.example.naemandong_main.rabbit.activity.Rabbit30;
import com.example.naemandong_main.rabbit.activity.Rabbit31;

public class rScene78 extends Fragment {

    private View view;
    private ImageView background, box, lion, front;
    private TextView subtitles;
    private String subs [] = {"\"꺼억~ 배부르다. 배부르니까 갑자기 졸린걸?\"", "배부른 사자는 잠이 솔솔 오기 시작했어요."};
    private ImageButton next;
    Handler delayHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rscene77, container,false);

        background = view.findViewById(R.id.background);
        front = view.findViewById(R.id.front);
        box = view.findViewById(R.id.subtitlebox);
        lion = view.findViewById(R.id.lion);
        subtitles = view.findViewById(R.id.subTitle);
        next = view.findViewById(R.id.next);

        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/5/10_back.png")
                .into(background);
        Glide.with(this)
                .load("http://49.50.174.179:9000/images/rabbit/7/88_cc.png")
                .into(lion);


        subtitles.setText(subs[0]);

        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                Glide.with(view)
                        .load("http://49.50.174.179:9000/images/rabbit/7/88_sleep.png")
                        .into(lion);
                subtitles.setText(subs[1]);
            }
        }, 5000);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO
                next.setVisibility(View.VISIBLE);
            }
        }, 10000);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((Rabbit30)getActivity()).play){
                    if(((Rabbit30)getActivity()).getData() == 0){
                        ((Rabbit30)getActivity()).removeData();
                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit31.class);
                        intent.putExtra("play",true);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else {
//                        ((Rabbit30)getActivity()).removeData();
//                        Intent intent = new Intent(getActivity().getApplicationContext(), Rabbit25.class);
//                        intent.putExtra("play",true);
//                        startActivity(intent);
//                        getActivity().finish();
                    }
                }
                else{
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    rScene79 rscene79 = new rScene79();
                    transaction.replace(R.id.frame,rscene79);
                    transaction.commit();  //저장
                }
            }
        });

        return view;
    }
}
