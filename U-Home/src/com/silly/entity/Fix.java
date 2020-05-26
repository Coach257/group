package com.silly.entity;

public class Fix {
    private int Fnum;
    private int Cnum;
    private int Rnum;
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

    public void setRnum(int rnum) {
        Rnum = rnum;
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

    public int getRnum() {
        return Rnum;
    }

    public int getWnum() {
        return Wnum;
    }

    public Fix(int fnum, int cnum, int rnum, int wnum) {
        Fnum = fnum;
        Cnum = cnum;
        Rnum = rnum;
        Wnum = wnum;
    }
}
