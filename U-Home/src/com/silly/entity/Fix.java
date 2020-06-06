package com.silly.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class Fix {
    private int Fnum;
    private int Cnum;
    private int Wnum;
    private String Content;
    private int Star;
    private String Reply;

    @JSONField(name = "Content")
    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
    @JSONField(name = "Star")
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
    @JSONField(name = "Fnum")
    public int getFnum() {
        return Fnum;
    }
    @JSONField(name = "Cnum")
    public int getCnum() {
        return Cnum;
    }
    @JSONField(name = "Wnum")
    public int getWnum() {
        return Wnum;
    }
    @JSONField(name = "Reply")
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
