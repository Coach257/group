package com.silly.entity;

public class Worker {
    private int Wnum;
    private String Name;
    private String Code;
    private int DealTime;//处理报修次数
    private double Score;//评分

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

    public Worker(int Wnum,String Name,String Code,int DealTime,double Score){
        this.Code=Code;
        this.Name=Name;
        this.Wnum=Wnum;
        this.DealTime=DealTime;
        this.Score=Score;
    }

    public Worker(){

    }
    public String toString(){
        return "{\"Wnum\":\""+Wnum+ "\"," +
                "\"Name\":\""+ Name +"\"," +
                "\"DealTime\":\""+ DealTime + "\"," +
                "\"Code\":\""+ Code + "\"," +
                "\"Score\":\""+ String.format("%.2f",Score) + "\""+
                "}";
    }
}
