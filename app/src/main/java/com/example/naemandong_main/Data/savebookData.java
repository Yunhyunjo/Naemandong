package com.example.naemandong_main.Data;

import com.google.gson.annotations.SerializedName;

public class savebookData {

    @SerializedName("storynum")
    private int storynum;

    @SerializedName("book_title")
    private String book_title;

    @SerializedName("book_select0")
    private int book_select0;

    @SerializedName("book_select1")
    private int book_select1;

    @SerializedName("book_select2")
    private int book_select2;

    @SerializedName("book_select3")
    private int book_select3;

    @SerializedName("book_select4")
    private int book_select4;

    @SerializedName("book_select5")
    private int book_select5;

    @SerializedName("book_select6")
    private int book_select6;

    @SerializedName("book_cover")
    private String book_cover;

    public savebookData(int storynum, String book_title, int book_select0, int book_select1, int book_select2, int book_select3, int book_select4, int book_select5, int book_select6, String book_cover) {
        this.storynum = storynum;
        this.book_title = book_title;
        this.book_select0 = book_select0;
        this.book_select1 = book_select1;
        this.book_select2 = book_select2;
        this.book_select3 = book_select3;
        this.book_select4 = book_select4;
        this.book_select5 = book_select5;
        this.book_select6 = book_select6;
        this.book_cover = book_cover;
    }
}
