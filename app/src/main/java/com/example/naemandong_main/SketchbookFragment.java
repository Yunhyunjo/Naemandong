package com.example.naemandong_main;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.naemandong_main.Data.coverData;
import com.example.naemandong_main.Data.coverResponse;
import com.example.naemandong_main.Network.RetrofitClient;
import com.example.naemandong_main.Network.ServiceApi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SketchbookFragment extends Fragment implements View.OnClickListener {

    private static final int REQEST_PERMISSION = 1001;
    private Context context;
    private View view;
    private int storynum, book_no;
    MyView vw;
    ImageButton red, orange, yellow, ygreen,  blue, skyblue, green, pink, brown,  black;
    Button eraser,save;
    File saveCover;

    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sketchbook, container,false);
        context = container.getContext();
        vw = new MyView(context);

        if (getArguments() != null){
            storynum = getArguments().getInt("storynum");
            book_no = getArguments().getInt("book_no");
        }

        red = view.findViewById(R.id.Red);
        orange = view.findViewById(R.id.Orange);
        yellow = view.findViewById(R.id.Yellow);
        ygreen = view.findViewById(R.id.Yellowgreen);
        green = view.findViewById(R.id.Green);
        skyblue = view.findViewById(R.id.SkyBlue);
        blue = view.findViewById(R.id.Blue);
        pink = view.findViewById(R.id.Pink);
        brown = view.findViewById(R.id.Brown) ;
        black = view.findViewById(R.id.Black);
        eraser = view.findViewById(R.id.Eraser);
        save = view.findViewById(R.id.Save);

        red.setOnClickListener(this);
        orange.setOnClickListener(this);
        yellow.setOnClickListener(this);
        ygreen.setOnClickListener(this);
        green.setOnClickListener(this);
        skyblue.setOnClickListener(this);
        blue.setOnClickListener(this);
        pink.setOnClickListener(this);
        brown.setOnClickListener(this);
        black.setOnClickListener(this);
        eraser.setOnClickListener(this);
        save.setOnClickListener(this);

        LinearLayout linear = view.findViewById(R.id.grim);
        linear.addView(vw); //해당 뷰에 자식을 붙이는 함수 addView
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Red:
                red.setSelected(true);
                orange.setSelected(false);
                yellow.setSelected(false);
                ygreen.setSelected(false);
                green.setSelected(false);
                skyblue.setSelected(false);
                blue.setSelected(false);
                pink.setSelected(false);
                brown.setSelected(false);
                black.setSelected(false);
                vw.color = Color.RED;
                vw.setTopen();
                break;
            case R.id.Orange:
                red.setSelected(false);
                orange.setSelected(true);
                yellow.setSelected(false);
                ygreen.setSelected(false);
                green.setSelected(false);
                skyblue.setSelected(false);
                blue.setSelected(false);
                pink.setSelected(false);
                brown.setSelected(false);
                black.setSelected(false);
                vw.color = Color.rgb(235, 105, 52);
                vw.setTopen();
                break;
            case R.id.Yellow:
                red.setSelected(false);
                orange.setSelected(false);
                yellow.setSelected(true);
                ygreen.setSelected(false);
                green.setSelected(false);
                skyblue.setSelected(false);
                blue.setSelected(false);
                pink.setSelected(false);
                brown.setSelected(false);
                black.setSelected(false);
                vw.color = Color.rgb(255, 224, 73);
                vw.setTopen();
                break;
            case R.id.Yellowgreen:
                red.setSelected(false);
                orange.setSelected(false);
                yellow.setSelected(false);
                ygreen.setSelected(true);
                green.setSelected(false);
                skyblue.setSelected(false);
                blue.setSelected(false);
                pink.setSelected(false);
                brown.setSelected(false);
                black.setSelected(false);
                vw.color = Color.rgb(180, 209, 51);
                vw.setTopen();
                break;
            case R.id.Green:
                red.setSelected(false);
                orange.setSelected(false);
                yellow.setSelected(false);
                ygreen.setSelected(false);
                green.setSelected(true);
                skyblue.setSelected(false);
                blue.setSelected(false);
                pink.setSelected(false);
                brown.setSelected(false);
                black.setSelected(false);
                vw.color = Color.rgb(49,169,93);
                vw.setTopen();
                break;
            case R.id.SkyBlue:
                red.setSelected(false);
                orange.setSelected(false);
                yellow.setSelected(false);
                ygreen.setSelected(false);
                green.setSelected(false);
                skyblue.setSelected(true);
                blue.setSelected(false);
                pink.setSelected(false);
                brown.setSelected(false);
                black.setSelected(false);
                vw.color = Color.rgb(126, 203, 241);
                vw.setTopen();
                break;
            case R.id.Blue:
                red.setSelected(false);
                orange.setSelected(false);
                yellow.setSelected(false);
                ygreen.setSelected(false);
                green.setSelected(false);
                skyblue.setSelected(false);
                blue.setSelected(true);
                pink.setSelected(false);
                brown.setSelected(false);
                black.setSelected(false);
                vw.color = Color.rgb(0, 125, 194);
                vw.setTopen();
                break;
            case R.id.Pink:
                red.setSelected(false);
                orange.setSelected(false);
                yellow.setSelected(false);
                ygreen.setSelected(false);
                green.setSelected(false);
                skyblue.setSelected(false);
                blue.setSelected(false);
                pink.setSelected(true);
                brown.setSelected(false);
                black.setSelected(false);
                vw.color = Color.rgb(239,149,175);
                vw.setTopen();
                break;
            case R.id.Brown:
                red.setSelected(false);
                orange.setSelected(false);
                yellow.setSelected(false);
                ygreen.setSelected(false);
                green.setSelected(false);
                skyblue.setSelected(false);
                blue.setSelected(false);
                pink.setSelected(false);
                brown.setSelected(true);
                black.setSelected(false);
                vw.color = Color.rgb(153,92,41);
                vw.setTopen();
                break;
            case  R.id.Black:
                red.setSelected(false);
                orange.setSelected(false);
                yellow.setSelected(false);
                ygreen.setSelected(false);
                green.setSelected(false);
                skyblue.setSelected(false);
                blue.setSelected(false);
                pink.setSelected(false);
                brown.setSelected(false);
                black.setSelected(true);
                vw.color = Color.BLACK;
                vw.setTopen();
                break;
            case R.id.Eraser:
                red.setSelected(false);
                orange.setSelected(false);
                yellow.setSelected(false);
                ygreen.setSelected(false);
                green.setSelected(false);
                skyblue.setSelected(false);
                blue.setSelected(false);
                pink.setSelected(false);
                brown.setSelected(false);
                black.setSelected(false);
                vw.setToeraser();
                break;
            case R.id.Save:
                save(vw);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("what", "Sketchbook");
                bundle.putInt("storynum", storynum);
                Bookre_select bookre_select = new Bookre_select();
                transaction.replace(R.id.framelayout, bookre_select);
                bookre_select.setArguments(bundle);
                transaction.commit();  //저장
                break;
        }
    }

    public void save(View view){
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQEST_PERMISSION);
        }else{
            saveBitmap();
        }
    }

    public void saveBitmap() {
        Bitmap bitmap = vw.getBitmap();
        String name;
        if (storynum == 1){
            name = "rabbitCover"+ book_no + ".png" ;
        }
        else {
            name = "pigCover"+ book_no + ".png";
        }
        Log.d("nameeeeeeeeeeeeeeeee : ", name + "   " + storynum);
        try {
            saveCover = new File(Environment.getExternalStorageDirectory()+"/Pictures/Test/","Cover");
            if(!saveCover.exists()){
                saveCover.mkdirs();
            }
            System.out.println("save FILE : " + saveCover + File.separator + name);
            FileOutputStream out = new FileOutputStream(saveCover + File.separator + name);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            Toast.makeText(context,"저장하였습니다.", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        send2Server(saveCover, name);
    }


    public void send2Server(File file, String name) {
        Log.d("짱나게 하지마라", "555555555");
        File image = new File(file, name);
        if (file.exists()) {
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("img", name, RequestBody.create(MediaType.parse(("image/png")), image))
                    .build();
            Request request = new Request.Builder()
                    .url("http://49.50.174.179:9000/uploadtest") // Server URL 은 본인 IP를 입력
                    .post(requestBody)
                    .build();
            OkHttpClient client = new OkHttpClient();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    Log.d("TEST : >>>>>>> ", "실패ㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐ" + e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.d("TEST : >>>>>>> ", response.body().string());
                    uploadcover(new coverData(storynum, book_no));
                }
            });
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if(requestCode == REQEST_PERMISSION && grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            saveBitmap();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void uploadcover(coverData data) {
        service.uploadCover(data).enqueue(new retrofit2.Callback<coverResponse>() {
            @Override
            public void onResponse(retrofit2.Call<coverResponse> call, retrofit2.Response<coverResponse> response) {
                coverResponse result = response.body();

                if (result.getCode() == 200) {
                    Log.d("성공", "표지변경에 성공했습니다.");
                }
            }

            @Override
            public void onFailure(retrofit2.Call<coverResponse> call, Throwable t) {
                Log.e("표지변경에 실패했습니다.", t.getMessage());
            }
        });
    }
}

