package com.example.naemandong_main.Network;

import com.example.naemandong_main.Data.bookListData;
import com.example.naemandong_main.Data.bookListResponse;
import com.example.naemandong_main.Data.coverData;
import com.example.naemandong_main.Data.coverResponse;
import com.example.naemandong_main.Data.findResponse;
import com.example.naemandong_main.Data.findidpwData;
import com.example.naemandong_main.Data.loginData;
import com.example.naemandong_main.Data.loginResponse;
import com.example.naemandong_main.Data.percentResponse;
import com.example.naemandong_main.Data.recordListData;
import com.example.naemandong_main.Data.recordListResponse;
import com.example.naemandong_main.Data.registerData;
import com.example.naemandong_main.Data.registerResponse;
import com.example.naemandong_main.Data.savebookData;
import com.example.naemandong_main.Data.savebookResponse;
import com.example.naemandong_main.Data.srecordData;
import com.example.naemandong_main.Data.srecordResponse;
import com.example.naemandong_main.Data.storybookData;
import com.example.naemandong_main.Data.storybookResponse;
import com.example.naemandong_main.Data.validateData;
import com.example.naemandong_main.Data.validateResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {

    @POST("/register")
    Call<registerResponse> userJoin(@Body registerData data);

    @POST("/validate")
    Call<validateResponse> idValid(@Body validateData data);

    @POST("/login")
    Call<loginResponse> userLogin(@Body loginData data);

    @POST("/savebook")
    Call<savebookResponse> userSave(@Body savebookData data);

      /*@POST("/bookList")
    Call<bookListResponse> userList(@Body bookListData data);*/

    @POST("/bookList2")
    Call<bookListResponse> userList(@Body bookListData data);

    @POST("/storydata")
    Call<storybookResponse> userBook(@Body storybookData data);

    @POST("/finduser")
    Call<findResponse> userFind(@Body findidpwData data);

    @POST("/upload2")
    Call<coverResponse> uploadCover(@Body coverData data);

    @POST("/percent")
    Call<percentResponse> getPercent(@Body bookListData data);

    @POST("/saverecord")
    Call<srecordResponse> recordSave(@Body srecordData data);

    @POST("/recorddata")
    Call<recordListResponse> recordList(@Body recordListData data);
}
