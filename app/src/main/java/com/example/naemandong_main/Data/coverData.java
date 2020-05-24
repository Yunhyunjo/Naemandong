package com.example.naemandong_main.Data;

import com.google.gson.annotations.SerializedName;

public class coverData {

    @SerializedName("storynum")
    private int storynum;

    @SerializedName("book_no")
    private int book_no;

    public coverData(int storynum, int book_no)
    {
        this.storynum = storynum;
        this.book_no = book_no;
    }
}
