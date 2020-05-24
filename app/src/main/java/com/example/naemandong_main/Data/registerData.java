package com.example.naemandong_main.Data;

import com.google.gson.annotations.SerializedName;

public class registerData {

    @SerializedName("mem_userid")
    private String mem_userid;

    @SerializedName("mem_password")
    private String mem_password;

    @SerializedName("mem_username")
    private String mem_username;

    @SerializedName("mem_nickname")
    private String mem_nickname;

    @SerializedName("mem_phone")
    private String mem_phone;

    public registerData(String mem_userid, String mem_password, String mem_username, String mem_nickname, String mem_phone) {
        this.mem_userid = mem_userid;
        this.mem_password = mem_password;
        this.mem_username = mem_username;
        this.mem_nickname = mem_nickname;
        this.mem_phone = mem_phone;
    }

}
