package com.example.naemandong_main.Data;

import com.google.gson.annotations.SerializedName;

public class recordListData {

    @SerializedName("id")
    private int id;

    public recordListData(int id) {
        this.id = id;
    }
}
