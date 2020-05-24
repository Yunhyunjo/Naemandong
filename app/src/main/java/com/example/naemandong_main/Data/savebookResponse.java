package com.example.naemandong_main.Data;

import com.google.gson.annotations.SerializedName;

public class savebookResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
