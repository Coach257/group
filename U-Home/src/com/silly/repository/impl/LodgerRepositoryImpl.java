package com.silly.repository.impl;

import com.silly.entity.Customer;
import com.silly.entity.Fix;
import com.silly.repository.LodgerRepository;
import com.silly.utils.JDBCtools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LodgerRepositoryImpl implements LodgerRepository {
    @Override
    public Customer login(String username, String password) {
            Connection connection = JDBCtools.getConnection();
            String sql = "select * from Customer where Name = ? and Code = ?";
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            Customer lodger = null;
            try {
                statement = connection.prepareStatement(sql);
                statement.setString(1,username);
                statement.setString(2,password);
                resultSet = statement.executeQuery();
                if(resultSet.next()){
                    lodger = new Customer(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCtools.release(connection,statement,resultSet);
            }
            return lodger;
    }

    @Override
    public void signin(int Cnum, String Name, String Email, String Phone, String Code)  {
      //  Customer lodger=new Customer(Cnum,Name,Email,Phone,Code);
        Connection connection=null;
        try {
             connection = JDBCtools.getConnection();
            String sql = "insert into Customer values(?,?,?,?,?)";
            QueryRunner qR = new QueryRunner();
            qR.update(connection,sql,Cnum,Name,Email,Phone,Code);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public boolean getbyname(String name){
        Connection connection = JDBCtools.getConnection();
        String sql = "select * from Customer where Name = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Boolean res=false;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                res=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCtools.release(connection,statement,resultSet);
        }
        return res;
    }

    @Override
    public List<Customer> GetLodger() {
        String sql;
        Connection connection = null;
        List<Customer> list=null;
        try {
            connection = JDBCtools.getConnection();
            QueryRunner qR = new QueryRunner();
            sql = "select * from Customer ";
            list = qR.query(connection, sql, new BeanListHandler<Customer>(Customer.class));
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
