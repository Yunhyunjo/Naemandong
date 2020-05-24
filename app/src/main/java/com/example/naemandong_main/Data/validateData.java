package com.example.naemandong_main.Data;

import com.google.gson.annotations.SerializedName;

public class validateData {

    @SerializedName("mem_userid")
    private String mem_userid;

    public validateData(String mem_userid) {
        this.mem_userid = mem_userid;
    }
}
