package com.silly.service;

import com.silly.entity.*;

import java.util.List;

public interface CustomerService {
    public void AddAOrder(Customer a, Room b, boolean TimeMode, java.sql.Date Bdate,java.sql.Date Edate);
    public List<Room> ShowSuitableRoom();
    public void MakeComplaint(int CoNum,Customer a,String Content);
    public List<Order> ShowMyOrder(Customer a);
    public void ChangeMyInfo(Customer a);
    public List<Fix> ShowMyFix(Customer a);
    public Worker GetWorkerByFix(Fix a);
    public void JudgeOnFix(Fix a,int star);
}
