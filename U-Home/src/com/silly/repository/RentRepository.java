package com.silly.repository;

import com.silly.entity.Customer;
import com.silly.entity.Order;
import com.silly.utils.JDBCtools;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface RentRepository {
    public List<Order> GetOrder(int mode,int num);
    public void DeleteOrder(Order a);
    public List<Order> GetAllToPay();
}
