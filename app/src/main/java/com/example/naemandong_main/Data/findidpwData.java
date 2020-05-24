package com.example.naemandong_main.Data;

import com.google.gson.annotations.SerializedName;

public class findidpwData {

    @SerializedName("userdata")
    private String userdata;

    public findidpwData(String userdata) {
        this.userdata = userdata;
    }
}
