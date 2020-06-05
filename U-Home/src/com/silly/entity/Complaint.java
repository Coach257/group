package com.silly.entity;

public class Complaint {
    private int CoNum;
    private int Cnum;
    private String Reply;
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

    public String getReply() {
        return Reply;
    }

    public void setReply(String reply) {
        Reply = reply;
    }

    public Complaint(int coNum, int cnum, String complaintContnet, boolean haveDone, String reply) {
        CoNum = coNum;
        Cnum = cnum;
        Reply=reply;
        ComplaintContnet = complaintContnet;
        HaveDone=haveDone;
    }

    public Complaint(){

    }
}
