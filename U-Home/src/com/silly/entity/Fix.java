package com.silly.entity;

public class Fix {
    private int Fnum;
    private int Cnum;
    private int Rnum;
    private int Wnum;

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
