package com.example.naemandong_main;

import android.app.Application;

import java.util.ArrayList;

public class Setting_data extends Application {

    private boolean sound = true;
    private boolean subtitle = true;
    private boolean record = false;
    private boolean recordPlay = false;
    private ArrayList<String> recordList = new ArrayList<>();
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

    public boolean isRecord() {
        return record;
    }

    public void setRecord(boolean record) {
        this.record = record;
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

    public ArrayList<String> getRecordList() {
        return recordList;
    }

    public void addRecordList(String path){
        recordList.add(path);
    }

    public void removeRecordData(){
        recordList.remove(0);
    }

    public void clearRecordList(){
        recordList.clear();
    }

    public boolean isRecordPlay() {
        return recordPlay;
    }

    public void setRecordPlay(boolean recordPlay){
        this.recordPlay = recordPlay;
    }
}
