package com.example.naemandong_main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.naemandong_main.Data.MybookListData;
import com.example.naemandong_main.Data.bookListData;
import com.example.naemandong_main.Data.bookListResponse;
import com.example.naemandong_main.Data.storybookData;
import com.example.naemandong_main.Data.storybookResponse;
import com.example.naemandong_main.Network.RetrofitClient;
import com.example.naemandong_main.Network.ServiceApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Bookre_select extends Fragment {
    private Context context;

    private View view;
    private ImageButton basic, record;
    private String what;
    private ArrayList<Integer> mySelect = new ArrayList<>();
    private ArrayList<String> strBook = new ArrayList<>();
    private int storynum;
//    public List<bookListResponse.myBook> re = new ArrayList<>();

    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bookre_select, container,false);
        context = container.getContext();

        basic = view.findViewById(R.id.basic);
        record = view.findViewById(R.id.record);

        if(getArguments() != null){
            what = getArguments().getString("what");
            storynum = getArguments().getInt("storynum");
        }
        if (what == "Voice"){
            basic.setVisibility(View.INVISIBLE);
            record.setVisibility(View.INVISIBLE);
        }

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList("myList", mySelect);
        bundle.putString("selected","basic");
        bundle.putString("what",what);
        bundle.putInt("storynum", storynum);
        MybookList mybookList = new MybookList();
        transaction.replace(R.id.fframe, mybookList);
        mybookList.setArguments(bundle);
        transaction.commit();  //저장

        basic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basic.setSelected(true);
                record.setSelected(false);
                /*Bundle bundle = new Bundle();
                bundle.putString("selected","basic");
                bundle.putIntegerArrayList("myList", mySelect);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MybookList mybookList = new MybookList();
                mybookList.setArguments(bundle);
                transaction.replace(R.id.fframe, mybookList);
                transaction.commit();  //저장*/

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("selected","basic");
                bundle.putIntegerArrayList("myList", mySelect);
                bundle.putInt("storynum", storynum);
                MybookList mybookList = new MybookList();
                transaction.replace(R.id.fframe, mybookList);
                mybookList.setArguments(bundle);
                transaction.commit();  //저장
            }
        });

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basic.setSelected(false);
                record.setSelected(true);
                Bundle bundle = new Bundle();
                bundle.putString("selected","voice");
                bundle.putInt("storynum", storynum);
                bundle.putIntegerArrayList("myList", mySelect);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MybookList mybookList = new MybookList();
                mybookList.setArguments(bundle);
                transaction.replace(R.id.fframe, mybookList);
                transaction.commit();  //저장
            }
        });

        return view;
    }
}
