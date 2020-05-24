package com.example.naemandong_main.Data;

import com.example.naemandong_main.PercentView;
import com.google.gson.annotations.SerializedName;

public class percentResponse {

    @SerializedName("count")
    private int count;

    @SerializedName("message")
    private String message;

    public int getCount() {
        return count;
    }

    public void setCount(int recount) { this.count = recount; }

    public String getMessage() {
        return message;
    }

    public percentResponse(int count) {
        this.count = count;
    }
}
