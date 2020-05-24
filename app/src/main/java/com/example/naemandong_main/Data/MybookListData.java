package com.example.naemandong_main.Data;

public class MybookListData {
    private int book_no;
    private String title;
    private String cover;

    public MybookListData(){

    }

    public MybookListData(int book_no, String title, String cover){
        this.book_no = book_no;
        this.title = title;
        this.cover = cover;

    }
    public int getBook_no() {
        return book_no;
    }

    public String getTitle() {
        return title;
    }

    public String getCover() {
        return cover;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBook_no(int book_no) {
        this.book_no = book_no;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
