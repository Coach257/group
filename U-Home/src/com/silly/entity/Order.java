package com.silly.entity;

public class Order {
    private int Cnum;
    private int Onum;
    private int Rnum;
    private int Mode;

    public int getCnum() {
        return Cnum;
    }

    public int getOnum() {
        return Onum;
    }

    public int getRnum() {
        return Rnum;
    }

    public int getMode() {
        return Mode;
    }

    public Order(int cnum, int onum, int rnum, int mode) {
        Cnum = cnum;
        Onum = onum;
        Rnum = rnum;
        Mode = mode;
    }
}
