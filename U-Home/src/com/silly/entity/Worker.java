package com.silly.entity;

public class Worker {
    private int Wnum;
    private String Name;
    private String Code;

    public void setWnum(int wnum) {
        Wnum = wnum;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setCode(String code) {
        Code = code;
    }

    public int getWnum() {
        return Wnum;
    }

    public String getName() {
        return Name;
    }

    public String getCode() {
        return Code;
    }

    public Worker(int a,String b,String c){
        this.Code=c;
        this.Name=b;
        this.Wnum=a;
    }

    public Worker(){

    }
}
