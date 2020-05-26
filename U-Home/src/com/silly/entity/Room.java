package com.silly.entity;

public class Room {
    private int Rnum;
    private int Capacity;
    private boolean CanUse;
    private boolean EmptyOrNot;
    private String url;
    private int CostPerDay;

    public int getRnum() {
        return Rnum;
    }

    public int getCapacity() {
        return Capacity;
    }

    public boolean isCanUse() {
        return CanUse;
    }

    public int getCostPerDay() {
        return CostPerDay;
    }

    public void setRnum(int rnum) {
        Rnum = rnum;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public void setCanUse(boolean canUse) {
        CanUse = canUse;
    }

    public void setEmptyOrNot(boolean emptyOrNot) {
        EmptyOrNot = emptyOrNot;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCostPerDay(int costPerDay) {
        CostPerDay = costPerDay;
    }

    public boolean isEmptyOrNot() {
        return EmptyOrNot;
    }

    public String getUrl() {
        return url;
    }

    public Room(int rnum, int capacity,  boolean emptyOrNot, String url,boolean canUse,int Cost) {
        Rnum = rnum;
        Capacity = capacity;
        CanUse=canUse;
        EmptyOrNot = emptyOrNot;
        this.url = url;
        CostPerDay=Cost;
    }
}
