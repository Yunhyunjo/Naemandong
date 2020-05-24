package com.example.naemandong_main.Data;

import com.google.gson.annotations.SerializedName;

public class bookListData {

    @SerializedName("storynum")
    private int storynum;

    public bookListData(int storynum) {
        this.storynum = storynum;
    }
}
