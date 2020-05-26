package com.silly.entity;

public class Room {
    private int Rnum;
    private int Capacity;
    private boolean Time;
    private boolean EmptyOrNot;
    private String url;

    public int getRnum() {
        return Rnum;
    }

    public int getCapacity() {
        return Capacity;
    }

    public boolean isTime() {
        return Time;
    }

    public boolean isEmptyOrNot() {
        return EmptyOrNot;
    }

    public String getUrl() {
        return url;
    }

    public Room(int rnum, int capacity, boolean time, boolean emptyOrNot, String url) {
        Rnum = rnum;
        Capacity = capacity;
        Time = time;
        EmptyOrNot = emptyOrNot;
        this.url = url;
    }
}
