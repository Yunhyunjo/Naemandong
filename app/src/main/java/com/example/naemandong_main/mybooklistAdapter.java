package com.example.naemandong_main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.example.naemandong_main.Data.MybookListData;
import com.example.naemandong_main.Data.storybookData;
import com.example.naemandong_main.Data.storybookResponse;
import com.example.naemandong_main.Network.RetrofitClient;
import com.example.naemandong_main.Network.ServiceApi;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mybooklistAdapter extends RecyclerView.Adapter<mybooklistAdapter.ViewHolder> {
    private ArrayList<MybookListData> mData = new ArrayList<>();
    public Context context;
    public ArrayList<Integer> mySelect = new ArrayList<>();
    public String what;
    public int storynum;
    public Activity activity = new Activity();

    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);


    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView rCover;
        TextView rTitle;
        int book_no;

        ViewHolder(final View itemView) {
            super(itemView) ;
            // 뷰 객체에 대한 참조. (hold strong reference)
            rCover = itemView.findViewById(R.id.rCover);
            rTitle = itemView.findViewById(R.id.rTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (what == "sketchbook"){
                        FragmentTransaction transaction = ((SketchbookActivity)context).getSupportFragmentManager().beginTransaction();
                        Bundle bundle = new Bundle();
                        bundle.putInt("book_no", book_no);
                        bundle.putInt("storynum", storynum);
                        SketchbookFragment sketchbookFragment = new SketchbookFragment();
                        transaction.replace(R.id.framelayout, sketchbookFragment);
                        sketchbookFragment.setArguments(bundle);
                        transaction.commit();  //저장
                    }
                    else {
                        readStory(new storybookData(book_no));
                    }
                }
            });
        }

        void onBind(MybookListData data) {
            rTitle.setText(data.getTitle());
            book_no = data.getBook_no();
            Glide.with(context)
                    .load(data.getCover())
                    .signature(new ObjectKey(String.valueOf(System.currentTimeMillis())))
                    .override(376,282)
                    .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(20,0)))
                    .into(rCover);
        }
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.mybooklist_item, parent, false) ;
        ViewHolder vh = new ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(mybooklistAdapter.ViewHolder holder, int position) {
        holder.onBind(mData.get(position));
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size();
    }

    void addItem(MybookListData data) {
        // 외부에서 item을 추가시킬 함수입니다.
        mData.add(data);
    }

    public void setStorynum(int storynum) {
        this.storynum = storynum;
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
                mySelect.add(result.getSelect6());

                Intent intent = new Intent(context, Rabbit01.class);
                intent.putExtra("select",mySelect);
                intent.putExtra("play",true);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<storybookResponse> call, Throwable t) {

            }
        });
    }
}
