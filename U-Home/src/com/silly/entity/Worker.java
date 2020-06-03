package com.silly.entity;

public class Worker {
    private int Wnum;
    private String Name;
    private String Code;
    private int DealTime;
    private double Score;

    public void setWnum(int wnum) {
        Wnum = wnum;
    }

    public int getWnum() {
        return Wnum;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public String getCode() {
        return Code;
    }

    public void setDealTime(int dealTime) {
        DealTime = dealTime;
    }

    public int getDealTime() {
        return DealTime;
    }

    public void setScore(double score) {
        Score = score;
    }

    public double getScore() {
        return Score;
    }

    public Worker(int a,String b,String c,int d,double e){
        this.Code=c;
        this.Name=b;
        this.Wnum=a;
        this.DealTime=d;
        this.Score=e;
    }

    public Worker(){

    }
    public String toString(){
        return "{\"Wnum\":\""+Wnum+ "\"," +
                "\"Name\":\""+ Name +"\"," +
                "\"DealTime\":\""+ DealTime + "\"," +
                "\"Code\":\""+ Code + "\"," +
                "\"Score\":\""+Score + "\""+
                "}";
    }
}
