package com.example.naemandong_main.Data;

import com.google.gson.annotations.SerializedName;

public class percentResponse {

    @SerializedName("count")
    private int count;

    @SerializedName("message")
    private String message;

    public int getCount() {
        return count;
    }

    public String getMessage() {
        return message;
    }
}
