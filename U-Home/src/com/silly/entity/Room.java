package com.silly.entity;

public class Room {
    private int Rnum;
    private int Capacity;
    private int EmptyOrNot;
    private String url;
    private boolean CanUse;
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

    public void setEmptyOrNot(int emptyOrNot) {
        EmptyOrNot = emptyOrNot;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCostPerDay(int costPerDay) {
        CostPerDay = costPerDay;
    }

    public int getEmptyOrNot() {
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

    public Room(int rnum, int capacity, int emptyOrNot, String url, boolean canUse, int Cost,
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

    public Room(){

    }

    public String toString(){
        return "{\"Rnum\":\""+ Rnum + "\"," +
                "\"Capacity\":"+ Capacity +"," + //这个是数字
                "\"CanUse\":"+ (CanUse?"true":"false") + "," +  //bool
                "\"EmptyOrNot\":"+ EmptyOrNot + "," +  //bool
                "\"url\":\""+ url + "\"," +
                "\"CostPerDay\":\""+ CostPerDay + "\"," +
                "\"Place\":\""+ Place + "\"," +
                "\"Rname\":\""+ RName + "\""+
                "}";
    }
}
