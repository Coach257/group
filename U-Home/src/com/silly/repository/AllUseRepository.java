package com.silly.repository;

import com.silly.entity.Customer;

import java.util.List;

public interface AllUseRepository {
    public boolean InOrNot(String table,String column,String con);
    public List<Customer> InformList();
}
