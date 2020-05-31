package com.example.naemandong_main.Data;

import com.google.gson.annotations.SerializedName;

public class recordListData {

    @SerializedName("book_no")
    private int book_no;

    public recordListData(int book_no) {
        this.book_no = book_no;
    }
}
