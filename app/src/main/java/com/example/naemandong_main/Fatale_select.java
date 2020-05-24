package com.example.naemandong_main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.naemandong_main.Data.bookListResponse;

import java.util.ArrayList;
import java.util.List;

public class Fatale_select extends Fragment {

    private Context context;

    private View view;
    private ImageButton pig,rabbit;
    public List<bookListResponse.myBook> re = new ArrayList<>();
    private String what;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fatale_select, container,false);
        context = container.getContext();

        pig = view.findViewById(R.id.pig);
        rabbit = view.findViewById(R.id.rabbit);

        if (getArguments() != null){
            what = getArguments().getString("what");
        }

        pig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("storynum", 2);
                bundle.putString("what", what);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Bookre_select bookre_select = new Bookre_select();
                bookre_select.setArguments(bundle);
                transaction.replace(R.id.framelayout, bookre_select);
                transaction.commit();  //저장

            }
        });
        rabbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("storynum", 1);
                bundle.putString("what", what);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Bookre_select bookre_select = new Bookre_select();
                bookre_select.setArguments(bundle);
                transaction.replace(R.id.framelayout, bookre_select);
                transaction.commit();  //저장


            }
        });

        return view;
    }
}
