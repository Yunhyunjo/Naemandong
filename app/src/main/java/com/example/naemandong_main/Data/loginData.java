package com.example.naemandong_main.Data;

import com.google.gson.annotations.SerializedName;

public class loginData {

    @SerializedName("mem_userid")
    private String mem_userid;

    @SerializedName("mem_password")
    private String mem_password;

    public loginData(String mem_userid, String mem_password) {
        this.mem_userid = mem_userid;
        this.mem_password = mem_password;
    }

}
