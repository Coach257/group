package com.silly.entity;

public class Worker {
    private int Wnum;
    private String Name;
    private String Code;

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
}
