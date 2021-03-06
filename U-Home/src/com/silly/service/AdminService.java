package com.silly.service;

import com.silly.entity.*;

import java.util.List;

public interface AdminService {
    public boolean ExistOrNot(String a,String b,String c);
    public List<Order> UnsettledOrders();
    public void changeUSOrders(Order a);
    public List<Complaint> UnsettledComplaint();
    public void HaveSeenComplaint(int CoNum,String reply);
    public void AddFix(Fix a);
    public List<Fix> UnsettledFix();
    public void settleFixOnForce(int Wnum,int Fnum);
    public void DealFix(int Fnum,String reply);
    public List<Customer> AllCustomer();
    public void AddCustomer(Customer a);
    public void DeleteCustomer(Customer a);
    public void ChangeCustomer(Customer a);
    public List<Worker> AllWorker();
    public void AddWorker(Worker a);
    public void DeleteWorker(Worker a);
    public List<Order> AllOrder();
    public void DeleteOrder(Order a);
    public void AddOrder(Order a);
    public void ChangeOrder(Order a);
    public List<Room> AllRoom();
    public void DeleteRoom(Room a);
    public void AddRoom(Room a);
    public void ChangeRoom(Room a);
    public Customer FindByCnum(int cnum);
    public Room FindByRnum(int rnum);
    public List<Customer> InformList();
    public List<Fix> WorkerFix(Worker worker);
    public Fix GetFixByFnum(int Fnum);
    public void SetOrderEnd(Order a);
    public List<Order> GetAllToPay();
}
