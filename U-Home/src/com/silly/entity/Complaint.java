package com.silly.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class Complaint {
    private int CoNum;
    private int Cnum;
    private String ComplaintContent;
    private boolean HaveDone;
    private String Reply;

    @JSONField(name = "CoNum")
    public int getCoNum() {
        return CoNum;
    }

    public void setCoNum(int coNum) {
        CoNum = coNum;
    }
    @JSONField(name = "Cnum")
    public int getCnum() {
        return Cnum;
    }

    public void setCnum(int cnum) {
        Cnum = cnum;
    }

    @JSONField(name = "ComplaintContent")
    public String getComplaintContent() {
        return ComplaintContent;
    }

    public void setComplaintContent(String complaintContnet) {
        ComplaintContent = complaintContnet;
    }
    @JSONField(name = "HaveDone")
    public boolean isHaveDone() {
        return HaveDone;
    }

    public void setHaveDone(boolean haveDone) {
        HaveDone = haveDone;
    }
    @JSONField(name = "Reply")
    public String getReply() {
        return Reply;
    }

    public void setReply(String reply) {
        Reply = reply;
    }

    public Complaint(int coNum, int cnum, String complaintContent, boolean haveDone, String reply) {
        CoNum = coNum;
        Cnum = cnum;
        Reply=reply;
        ComplaintContent = complaintContent;
        HaveDone=haveDone;
    }

    public Complaint(){

    }
}
