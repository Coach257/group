package com.silly.repository;

import com.silly.entity.Order;

import java.util.List;

public interface RentRepository {
    public List<Order> GetOrder(int mode,int num);
}
