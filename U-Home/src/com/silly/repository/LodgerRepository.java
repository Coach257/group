package com.silly.repository;

import com.silly.entity.Customer;

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
}
