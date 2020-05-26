package com.silly.entity;

public class Complaint {
    private int CoNum;
    private int Cnum;
    private int Rnum;
    private String ComplaintContnet;
    private boolean HaveDone;


    public int getCoNum() {
        return CoNum;
    }

    public void setCoNum(int coNum) {
        CoNum = coNum;
    }

    public int getCnum() {
        return Cnum;
    }

    public void setCnum(int cnum) {
        Cnum = cnum;
    }

    public int getRnum() {
        return Rnum;
    }

    public void setRnum(int rnum) {
        Rnum = rnum;
    }

    public String getComplaintContnet() {
        return ComplaintContnet;
    }

    public void setComplaintContnet(String complaintContnet) {
        ComplaintContnet = complaintContnet;
    }

    public boolean isHaveDone() {
        return HaveDone;
    }

    public void setHaveDone(boolean haveDone) {
        HaveDone = haveDone;
    }

    public Complaint(int coNum, int cnum, int rnum, String complaintContnet, boolean haveDone) {
        CoNum = coNum;
        Cnum = cnum;
        Rnum = rnum;
        ComplaintContnet = complaintContnet;
        HaveDone=haveDone;
    }
}
