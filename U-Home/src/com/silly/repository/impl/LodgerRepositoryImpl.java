package com.silly.repository.impl;

import com.silly.entity.*;
import com.silly.repository.LodgerRepository;
import com.silly.utils.JDBCtools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
    public String signup(int Cnum, String Name, String Email, String Phone, String Code)  {
      //  Customer lodger=new Customer(Cnum,Name,Email,Phone,Code);
        if(getbyName(Name)!=null)return "用户名已被注册";
        if (getbyEmail(Email)!=null)return "邮箱已被注册";
        if (getbyPhone(Phone)!=null)return "手机号已被注册";
        Connection connection=null;
        try {
             connection = JDBCtools.getConnection();
            String sql = "insert into Customer(Cnum,Name,Email,Phone,Code) values(?,?,?,?,?)";
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
        return null;
    }

    @Override
    public Customer getbyName(String username) {
        Connection connection = JDBCtools.getConnection();
        String sql = "select * from Customer where Name = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Customer lodger = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);
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
    public Customer getbyEmail(String email) {
        Connection connection = JDBCtools.getConnection();
        String sql = "select * from Customer where Email = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Customer lodger = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,email);
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
    public Customer getbyPhone(String phone) {
        Connection connection = JDBCtools.getConnection();
        String sql = "select * from Customer where Phone = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Customer lodger = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,phone);
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

    @Override
    public Customer getbyCnum(int Cnum) {
        Connection connection = JDBCtools.getConnection();
        String sql = "select * from Customer where Cnum = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Customer lodger = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,Cnum);
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
    public void CreateOrder(Customer a, Room b, boolean TimeMode, java.sql.Date Bdate,java.sql.Date Edate) {
        int onum=(int) (System.currentTimeMillis()/1000);
        Calendar calendarB =new GregorianCalendar();
        Calendar calendarE =new GregorianCalendar();
        int Time;
        java.sql.Date begindate= Bdate;
        java.sql.Date enddate=Edate;
        calendarB.setTime(begindate);
        calendarE.setTime(enddate);
        int moneyNeeded;
        if(TimeMode==true){
           // calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)+Time-1);
           // calendar.set(Calendar.DATE,calendar.get(Calendar.DAY_OF_MONTH));
            Time=(calendarE.get(Calendar.YEAR)-calendarB.get(Calendar.YEAR))*12+
                    calendarE.get(Calendar.MONTH)-calendarB.get(Calendar.MONTH)+1;
            moneyNeeded=30*Time*b.getCostPerDay();
        }
        else{
            //calendar.add(Calendar.DATE,Time);
            long timea=calendarE.getTimeInMillis();
            long timeb=calendarB.getTimeInMillis();
            long between_days=(timea-timeb)/(1000*3600*24);
            Time=Integer.parseInt(String.valueOf(between_days));
            moneyNeeded=Time*b.getCostPerDay();
        }
        Order order=new Order(onum,a.getCnum(),b.getRnum(),2,moneyNeeded,begindate
        ,TimeMode,enddate);
        Connection connection=null;
        String sql=null;
        try {
            connection = JDBCtools.getConnection();
            QueryRunner qR = new QueryRunner();
            sql="insert into Orders values (?,?,?,?,?,?,?,?)";
            qR.update(connection,sql,order.getOnum(),order.getCnum(),order.getRnum(),
                    order.getMode(),order.getMoneyNeeded(),order.getBeginDate(),
                    order.isTime(),order.getEndDate());
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
    public void MakeComplaint(int CoNum,String Content,Customer a) {
        Complaint b=new Complaint(CoNum,a.getCnum(),Content,false,null);
        Connection connection=null;
        String sql=null;
        try{
            connection = JDBCtools.getConnection();
            QueryRunner qR = new QueryRunner();
            sql="insert into Complaint values(?,?,?,?,?)";
            qR.update(connection,sql,b.getCoNum(),b.getCnum(),
                    b.getComplaintContent(),b.isHaveDone(),b.getReply());
        }catch (SQLException throwables) {
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
    public List<Order> GetMyOrder(Customer a) {
        String sql;
        Connection connection = null;
        List<Order> list=null;
        try {
            connection = JDBCtools.getConnection();
            QueryRunner qR = new QueryRunner();
            sql = "select * from Orders  where Cnum= ?";
            list = qR.query(connection, sql, new BeanListHandler<Order>(Order.class),a.getCnum());
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
    public List<Fix> GetMyFix(Customer a) {
        String sql;
        Connection connection = null;
        List<Fix> list=null;
        try {
            connection = JDBCtools.getConnection();
            QueryRunner qR = new QueryRunner();
            sql = "select * from Fix  where Cnum= ?";
            list = qR.query(connection, sql, new BeanListHandler<Fix>(Fix.class),a.getCnum());
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
    public void JudgeOnFix(Fix a, int star) {
        Connection connection=null;
        try {
            connection = JDBCtools.getConnection();
            String sql = "update Fix set Star= ? where Fnum= ?";
            QueryRunner qR = new QueryRunner();
            qR.update(connection,sql,star,a.getFnum());
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
    public Worker GetWorkerInfo(Fix a) {
        Connection connection = JDBCtools.getConnection();
        String sql = "select * from Worker where Wnum = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Worker worker = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,a.getWnum());
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                worker = new Worker(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getInt(4),
                        resultSet.getDouble(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCtools.release(connection,statement,resultSet);
        }
        return worker;
    }

}

