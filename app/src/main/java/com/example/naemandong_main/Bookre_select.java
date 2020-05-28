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

    private void readStory(storybookData data) {
        service.userBook(data).enqueue(new Callback<storybookResponse>() {
            @Override
            public void onResponse(Call<storybookResponse> call, Response<storybookResponse> response) {
                storybookResponse result = response.body();
                mySelect.add(result.getSelect0());
                mySelect.add(result.getSelect1());
                mySelect.add(result.getSelect2());
                mySelect.add(result.getSelect3());
                mySelect.add(result.getSelect4());
                mySelect.add(result.getSelect5());
                mySelect.add(result.getSelect5());

                Toast.makeText(context, String.valueOf(mySelect), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<storybookResponse> call, Throwable t) {
                //               Toast.makeText(rfinal01.this, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
                //               Log.e("회원가입에 실패했습니다.", t.getMessage());
            }
        });
    }

    /*private void startList(bookListData data) {
        service.userList(data).enqueue(new Callback<bookListResponse>() {
            @Override
            public void onResponse(Call<bookListResponse> call, Response<bookListResponse> response) {
                bookListResponse resource = response.body();
                re.addAll(resource.result);
                //               re = resource.result;

                for (int i=0; i<resource.result.size(); i++) {
                    strBook.add(re.get(i).toString());
                }

                String title = re.get(1).book_title;
                Toast.makeText(context, "데이터 수는 "+resource.getCount()+title, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<bookListResponse> call, Throwable t) {
                //               Toast.makeText(rfinal01.this, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
                //               Log.e("회원가입에 실패했습니다.", t.getMessage());
            }
        });
    }*/


}
