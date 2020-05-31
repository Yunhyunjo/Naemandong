package com.example.naemandong_main.Data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class rbookListResponse {


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
    public List<myrBook> result = new ArrayList();

    public List<myrBook> getMyBook() {
        return result;
    }

    //   public List<myBook> setMyBook(List<myBook> result) { this.result.addAll(result); }

    public int getCount() {
        return count;
    }

    public class myrBook {
        @SerializedName("id")
        public int id;

        @SerializedName("book_no")
        public int book_no;

        @SerializedName("book_title")
        public String book_title;

        @SerializedName("book_cover")
        public String book_cover;

        public int getBookid() {
            return id;
        }

        public int getBookno() {
            return book_no;
        }

        public String getBooktitle() {
            return book_title;
        }

        public String getBookcover() {
            return book_cover;
        }


    }
}
