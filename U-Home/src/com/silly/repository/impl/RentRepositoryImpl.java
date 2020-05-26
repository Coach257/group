package com.silly.repository.impl;

import com.silly.entity.Order;
import com.silly.repository.RentRepository;
import com.silly.utils.JDBCtools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RentRepositoryImpl implements RentRepository {
    @Override
    public List<Order> GetOrder(int mode, int num) {
        String sql;
        Connection connection = null;
        List<Order> list=null;
        try {
            connection = JDBCtools.getConnection();
            QueryRunner qR = new QueryRunner();
            if (num == -1) {//此用在admin查找所有订单
                sql = "select * from Orders where Mode = ?";
                if(mode==-1){
                    sql="select * from Orders";
                }
                list = qR.query(connection, sql, new BeanListHandler<Order>(Order.class), mode);
            } else {//特定用户查找自己的订单
                sql = "select * from Orders where Mode = ? and Cnum = ?";
                list = qR.query(connection, sql, new BeanListHandler<Order>(Order.class), mode);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }


}
