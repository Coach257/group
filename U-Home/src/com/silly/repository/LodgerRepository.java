package com.silly.repository;

import com.silly.entity.Customer;

import java.sql.SQLException;

public interface LodgerRepository {
    public Customer login(String username, String password);
    public void signin(int Cnum,String Name,String Email,String Phone,String Code) ;
    public boolean getbyname(String name);
}
