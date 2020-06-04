package com.silly.repository.impl;

import com.silly.entity.Complaint;
import com.silly.entity.Customer;
import com.silly.entity.Order;
import com.silly.repository.AdminRepository;
import com.silly.repository.AllUseRepository;
import com.silly.repository.LodgerRepository;
import com.silly.utils.JDBCtools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class AllUseRepositoryImpl implements AllUseRepository {
    @Override
    public boolean InOrNot(String table, String column, String con) {
        Connection connection=null;
        try {
            connection = JDBCtools.getConnection();
            String sql = "select * from "+table+" where "+column+" = ?";
            QueryRunner qR = new QueryRunner();
            Object list=qR.query(connection,sql,new ScalarHandler<>(),con);
            if(list!=null)
                return true;
            else return false;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
        return false;
    }

    @Override
    public List<Customer> InformList() {
        LodgerRepository lodgerRepository=new LodgerRepositoryImpl();
        AdminRepository adminRepository=new AdminRepositoryImpl();
        String sql;
        Connection connection = null;
        List<Order> list=null;
        List<Customer> aimlist=null;
        Customer customer;
        Calendar calendar =new GregorianCalendar();
        Calendar calendarx =new GregorianCalendar();
        Calendar calendary=new GregorianCalendar();
        calendar=Calendar.getInstance();
        try {
            connection = JDBCtools.getConnection();
            QueryRunner qR = new QueryRunner();
            sql = "select * from Order where Time= ture and Mode= 4";
            list = qR.query(connection, sql, new BeanListHandler<Order>(Order.class));
            for(Order a:list){
                customer=lodgerRepository.getbyCnum(a.getCnum());
                calendarx.setTime(a.getBeginDate());
                calendary.setTime(a.getEndDate());
                if(calendary.get(Calendar.MONTH)-calendar.get(Calendar.MONTH)>0)
                    if (calendar.get(Calendar.MONTH)-calendarx.get(Calendar.MONTH)>=0){
                        aimlist.add(customer);
                        a.setMode(3);
                        adminRepository.alterOrdersInfo(a);
                    }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return aimlist;
    }
}
