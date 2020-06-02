package com.example.naemandong_main.Data;

import com.google.gson.annotations.SerializedName;

public class findResponse {

    @SerializedName("userid")
    private String userid;

    @SerializedName("userpw")
    private String userpw;

    @SerializedName("resultCode")
    private int resultCode;

    public String getId() {
        return userid;
    }

    public String getPw() {
        return userpw;
    }

    public int getCode() {
        return resultCode;
    }


}
