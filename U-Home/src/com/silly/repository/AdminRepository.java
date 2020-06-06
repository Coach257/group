package com.silly.repository;

import com.silly.entity.*;

import java.util.List;

public interface AdminRepository {
    public Admin login(String username, String password);
    public void alterLodgerInfo(Customer a);
    public void deleteOneInfo(String table,Object a);
    public void addOneInfo(String table,Object a);
    public List<Complaint> checkUndoneComplaint();
    public void havenChecked(int CoNum,String reply);
    public void alterOrdersInfo(Order a);
    public void alterRoom(Room a);
    public List<Fix> checkUndoneFix();
    public void alterFix(int Wnum,int Fnum);
}
