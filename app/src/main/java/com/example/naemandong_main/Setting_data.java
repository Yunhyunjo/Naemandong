package com.example.naemandong_main;

import android.app.Application;

import java.util.ArrayList;

public class Setting_data extends Application {

    private boolean sound = true;
    private boolean subtitle = true;
    public ArrayList<Integer> myList = new ArrayList<>();

    public boolean getSound(){
        return sound;
    }

    public boolean getSubtitle(){
        return subtitle;
    }

    public void setSound(boolean sound){
        this.sound = sound;
    }

    public void setSubtitle(boolean subtitle){
        this.subtitle = subtitle;
    }

    public ArrayList<Integer> getMyList() {
        return myList;
    }
    public void addMyList(int a) {
        myList.add(a);
    }
    public void clearList() {
        myList.clear();
    }
}
