package com.silly.repository;

import com.silly.entity.*;

import java.sql.SQLException;
import java.util.List;

public interface LodgerRepository {
    public Customer login(String username, String password);
    public String signup(int Cnum,String Name,String Email,String Phone,String Code) ;
    public Customer getbyName(String username);
    public Customer getbyEmail(String email);
    public Customer getbyPhone(String phone);
    public List<Customer> GetLodger();
    public Customer getbyCnum(int Cnum);
    public void CreateOrder(Customer a, Room b,boolean TimeMode,java.sql.Date Bdate,java.sql.Date Edate);
    public void MakeComplaint(int CoNum,String Content,Customer a);
    public List<Order> GetMyOrder(Customer a);
    public List<Fix> GetMyFix(Customer a);
    public Worker GetWorkerInfo(Fix a);
    public void JudgeOnFix(Fix a,int star);
}
