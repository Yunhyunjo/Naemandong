package com.example.naemandong_main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.naemandong_main.Data.MybookListData;
import com.example.naemandong_main.Data.bookListData;
import com.example.naemandong_main.Data.bookListResponse;
import com.example.naemandong_main.Network.RetrofitClient;
import com.example.naemandong_main.Network.ServiceApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MybookList extends Fragment {
    public static Context context;
    private mybooklistAdapter adapter;
    private ArrayList<Integer> mySelect;
    private RecyclerView recyclerView;
    public List<bookListResponse.myBook> re = new ArrayList<>();
    private int storynum;
    private String what;

    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.mybooklist, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView) ;
        recyclerView.setHasFixedSize(true);

        if(getArguments() != null){
            mySelect = getArguments().getIntegerArrayList("myList");
            storynum = getArguments().getInt("storynum");
            what = getArguments().getString("what");
        }

        adapter = new mybooklistAdapter() ;
        adapter.what = what;
        adapter.setStorynum(storynum);
        if ((what == "Sketchbook")) {
            adapter.activity = this.getActivity();
        }
        if ((what == "Voice")) {
            adapter.activity = this.getActivity();
        }
        startList(new bookListData(storynum));
        recyclerView.addItemDecoration(new ItemDecoration());
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void startList(bookListData data) {
        service.userList(data).enqueue(new Callback<bookListResponse>() {
            @Override
            public void onResponse(Call<bookListResponse> call, Response<bookListResponse> response) {
                bookListResponse resource = response.body();
                re.addAll(resource.result);

                for (int i = 0; i < re.size(); i++){
                    int a = re.get(i).book_no;
                    String b = re.get(i).book_title;
                    String c = re.get(i).book_cover;
                    adapter.addItem(new MybookListData(a,b,c));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<bookListResponse> call, Throwable t) {
                //               Toast.makeText(rfinal01.this, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
                //               Log.e("회원가입에 실패했습니다.", t.getMessage());
            }
        });
    }
}
