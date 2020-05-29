package com.silly.entity;

public class Room {
    private int Rnum;
    private int Capacity;
    private boolean CanUse;
    private boolean EmptyOrNot;
    private String url;
    private int CostPerDay;
    private String Place;
    private String RName;

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

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public String getRName() {
        return RName;
    }

    public void setRName(String RName) {
        this.RName = RName;
    }

    public String toString(){
        return "{\"Rnum\":\""+ Rnum + "\"," +
                "\"Capacity\":\""+ Capacity +"\"," +
                "\"CanUse\":\""+ CanUse + "\"," +
                "\"EmptyOrNot\":\""+ EmptyOrNot + "\"," +
                "\"url\":\""+ url + "\"," +
                "\"CostPerDay\":\""+ CostPerDay + "\"," +
                "\"Place\":\""+ Place + "\"," +
                "\"RName\":\""+ RName + "\""+
                "}";
    }

    public Room(int rnum, int capacity, boolean emptyOrNot, String url, boolean canUse, int Cost,
                String place, String rName) {
        Rnum = rnum;
        Capacity = capacity;
        CanUse=canUse;
        EmptyOrNot = emptyOrNot;
        this.url = url;
        CostPerDay=Cost;
        Place=place;
        RName=rName;
    }
}
