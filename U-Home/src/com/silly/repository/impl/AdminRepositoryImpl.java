package com.silly.repository.impl;

import com.silly.entity.*;
import com.silly.repository.AdminRepository;
import com.silly.utils.JDBCtools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminRepositoryImpl implements AdminRepository {
    public Admin login(String username, String password) {
        Connection connection = JDBCtools.getConnection();
        String sql = "select * from Admin where Name = ? and Code = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Admin admin = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                admin = new Admin(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCtools.release(connection,statement,resultSet);
        }
        return admin;
    }

    @Override
    public void alterLodgerInfo(Customer a) {
        Connection connection=null;
        try {
            connection = JDBCtools.getConnection();
            String sql = "update Customer set Name = ?, Email =?, Phone = ?, Code= ?," +
                    "url= ? where Cnum= ?";
            QueryRunner qR = new QueryRunner();
            qR.update(connection,sql,a.getName(),a.getEmail(),a.getPhone(),a.getCode(),
                    a.getUrl(),a.getCnum());
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
    public void deleteOneInfo(String table, Object a) {
        Connection connection=null;
        String sql=null;
        try {
            connection = JDBCtools.getConnection();
            QueryRunner qR = new QueryRunner();
            sql="delete from "+table+" where ";
            if(table.equals("Room")){
                sql+="Rnum= ? ";
                qR.update(connection,sql,((Room)a).getRnum());
            }
            else if(table.equals("Orders")){
                sql+="Onum= ? ";
                qR.update(connection,sql,((Order)a).getOnum());

            }
            else if(table.equals("Customer")){
                sql+="Cnum= ? ";
                qR.update(connection,sql,((Customer)a).getCnum());
            }
            else if(table.equals("Worker")){
                sql+="Wnum= ? ";
                qR.update(connection,sql,((Worker)a).getWnum());
            }
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
    public void addOneInfo(String table, Object a) {
        Connection connection=null;
        String sql=null;
        try {
            connection = JDBCtools.getConnection();
            QueryRunner qR = new QueryRunner();
            sql="insert into "+table+" values";
            if(table.equals("Room")){
                sql+="(?,?,?,?,true,?,?,?)";
                Room b=(Room)a;
                qR.update(connection,sql,b.getRnum(),b.getCapacity(),b.getEmptyOrNot(),b.getUrl(),
                        b.getCostPerDay(),b.getPlace(),b.getRName());
            }
            else if(table.equals("Orders")){
                sql+="(?,?,?,?,?,?,?,?)";
                Order b=(Order) a;
                qR.update(connection,sql,b.getOnum(),b.getCnum(),b.getRnum(),
                        b.getMode(),b.getMoneyNeeded(),b.getBeginDate(),
                b.isTime(),b.getEndDate());

            }
            else if(table.equals("Customer")){
                sql+="(?,?,?,?,?,?)";
                Customer b=(Customer) a;
                qR.update(connection,sql,b.getCnum(),b.getName(),b.getEmail(),
                        b.getPhone(),b.getCode(),b.getUrl());
            }
            else if(table.equals("Worker")){
                sql+="(?,?,?,0,0)";
                Worker b=(Worker) a;
                qR.update(connection,sql,b.getWnum(),b.getName(),b.getCode());
            }
            else if(table.equals("Fix")){
                sql+="(?,?,?,?,?,?)";
                Fix b=(Fix)a;
                qR.update(connection,sql,b.getFnum(),b.getCnum(),
                        b.getWnum(),b.getContent(),b.getStar(),b.getReply());
            }
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
    public List<Complaint> checkUndoneComplaint() {
        String sql;
        Connection connection = null;
        List<Complaint> list=null;
        try {
            connection = JDBCtools.getConnection();
            QueryRunner qR = new QueryRunner();
            sql = "select * from Complaint where HaveDone = false ";
            list = qR.query(connection, sql, new BeanListHandler<Complaint>(Complaint.class));
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

    @Override
    public void havenChecked(int CoNum,String reply) {
        Connection connection=null;
        try {
            connection = JDBCtools.getConnection();
            String sql = "update Complaint set HaveDone= true,Reply=? where CoNum= ?";
            QueryRunner qR = new QueryRunner();
            qR.update(connection,sql,reply,CoNum);
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
    public void alterOrdersInfo(Order a) {
        Connection connection=null;
        try {
            connection = JDBCtools.getConnection();
            String sql = "update Orders set Cnum = ?, Rnum =?, Mode = ?, MoneyNeeded= ?," +
                    "BeginDate= ? ,Time= ? ,EndDate= ? where Onum= ?";
            QueryRunner qR = new QueryRunner();
            qR.update(connection,sql,a.getCnum(),a.getRnum(),a.getMode(),a.getMoneyNeeded(),
                    a.getBeginDate(),a.isTime(),a.getEndDate(),a.getOnum());
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
    public void alterRoom(Room a) {
        Connection connection=null;
        try {
            connection = JDBCtools.getConnection();
            String sql = "update Room set Capacity = ?, EmptyOrNot =?, url = ?, CanUse= ?," +
                    "CostPerDay= ? ,Place= ? ,RName= ? where Rnum= ?";
            QueryRunner qR = new QueryRunner();
            qR.update(connection,sql,a.getCapacity(),a.getEmptyOrNot(),a.getUrl(),a.isCanUse()
            ,a.getCostPerDay(),a.getPlace(),a.getRName(),a.getRnum());
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
    public List<Fix> checkUndoneFix() {
        String sql;
        Connection connection = null;
        List<Fix> list=null;
        try {
            connection = JDBCtools.getConnection();
            QueryRunner qR = new QueryRunner();
            sql = "select * from Fix where Wnum = -1";
            list = qR.query(connection, sql, new BeanListHandler<Fix>(Fix.class));
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

    @Override
    public void alterFix(int Wnum,int Fnum) {
        Connection connection=null;
        try {
            connection = JDBCtools.getConnection();
            String sql = "update Fix set Wnum = ? where Fnum= ?";
            QueryRunner qR = new QueryRunner();
            qR.update(connection,sql,Wnum,Fnum);
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
    public Fix getfixbyfnum(int Fnum) {
        Connection connection = JDBCtools.getConnection();
        String sql = "select * from Fix where Fnum = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Fix fix = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(Fnum));
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                fix = new Fix(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3)
                        ,resultSet.getString(4),resultSet.getInt(5),resultSet.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCtools.release(connection,statement,resultSet);
        }
        return fix;
    }
}
