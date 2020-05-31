package com.example.naemandong_main.Data;

import com.google.gson.annotations.SerializedName;

public class rbookListData {

    @SerializedName("storynum")
    private int storynum;

    public rbookListData(int storynum) {
        this.storynum = storynum;
    }
}
