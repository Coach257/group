package com.silly.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Order {
    private int Onum;
    private int Cnum;
    private int Rnum;
    private int Mode;//mode中1是未提交，2是未审核，3是未付款，4是已完成订单,5是审核不通过，6是长租当月已付款，7是订单结束
    private int MoneyNeeded;
    private Date BeginDate;
    private boolean Time;//true是长租 false是短租
    private Date EndDate;

    public void setCnum(int cnum) {
        Cnum = cnum;
    }

    public void setOnum(int onum) {
        Onum = onum;
    }

    public void setRnum(int rnum) {
        Rnum = rnum;
    }

    public void setMode(int mode) {
        Mode = mode;
    }

    public void setMoneyNeeded(int moneyNeeded) {
        MoneyNeeded = moneyNeeded;
    }

    public void setBeginDate(Date beginDate) {
        BeginDate = beginDate;
    }

    public void setTime(boolean time) {
        Time = time;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    @JSONField(name = "Cnum")
    public int getCnum() {
        return Cnum;
    }

    @JSONField(name = "Onum")
    public int getOnum() {
        return Onum;
    }

    @JSONField(name = "Rnum")
    public int getRnum() {
        return Rnum;
    }

    @JSONField(name = "Mode")
    public int getMode() {
        return Mode;
    }

    @JSONField(name = "MoneyNeeded")
    public int getMoneyNeeded() {
        return MoneyNeeded;
    }

    @JSONField(format="yyyy-MM-dd",name = "BeginDate")
    public Date getBeginDate() {
        return BeginDate;
    }

    @JSONField(name = "Time")
    public boolean isTime() {
        return Time;
    }

    @JSONField(format="yyyy-MM-dd",name = "EndDate")
    public Date getEndDate() {
        return EndDate;
    }

    public Order(int onum, int cnum, int rnum, int mode, int moneyNeeded, java.sql.Date beginDate,
                 boolean time, java.sql.Date endDate) {
        Cnum = cnum;
        Onum = onum;
        Rnum = rnum;
        Mode = mode;
        MoneyNeeded=moneyNeeded;
        BeginDate=beginDate;
        Time=time;
        EndDate=endDate;
    }

    public Order(){

    }
}
