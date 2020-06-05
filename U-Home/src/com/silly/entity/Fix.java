package com.silly.entity;

public class Fix {
    private int Fnum;
    private int Cnum;
    private String Reply;
    private int Wnum;
    private String Content;
    private int Star;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getStar() {
        return Star;
    }

    public void setStar(int star) {
        Star = star;
    }

    public void setFnum(int fnum) {
        Fnum = fnum;
    }

    public void setCnum(int cnum) {
        Cnum = cnum;
    }

    public void setWnum(int wnum) {
        Wnum = wnum;
    }

    public int getFnum() {
        return Fnum;
    }

    public int getCnum() {
        return Cnum;
    }

    public int getWnum() {
        return Wnum;
    }

    public String getReply() {
        return Reply;
    }

    public void setReply(String reply) {
        Reply = reply;
    }

    public Fix(int fnum, int cnum, int wnum,String content,int star,String reply) {
        Fnum = fnum;
        Cnum = cnum;
        Wnum = wnum;
        Content=content;
        Star=star;
        Reply=reply;
    }

    public Fix(){

    }
}
