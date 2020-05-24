package com.example.naemandong_main.Data;

import android.app.Application;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class bookListResponse {


    /*@SerializedName("count")
    private int count;

    @SerializedName("book_no")
    private int book_no;

    @SerializedName("book_title")
    private String book_title;

    public int getCount() { return count; }

    public int getBookno() { return book_no; }

    public String getBooktitle() {
        return book_title;
    }*/

    @SerializedName("count")
    private int count;

    @SerializedName("result")
    public List<myBook> result = new ArrayList();

    public List<myBook> getMyBook() { return result; }

 //   public List<myBook> setMyBook(List<myBook> result) { this.result.addAll(result); }

    public int getCount() { return count; }

    public class myBook {
        @SerializedName("book_no")
        public int book_no;

        @SerializedName("book_title")
        public String book_title;

        @SerializedName("book_cover")
        public String book_cover;

        public int getBookno() { return book_no; }

        public String getBooktitle() { return book_title; }

        public String getBookcover() { return book_cover; }


    }
}
