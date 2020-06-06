package com.silly.repository.impl;


import com.silly.entity.Fix;
import com.silly.entity.Order;
import com.silly.entity.Worker;
import com.silly.repository.WorkerRepository;
import com.silly.utils.JDBCtools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WorkerRepositoryImpl implements WorkerRepository {
    @Override
    public Worker login(String username, String password) {
        Connection connection = JDBCtools.getConnection();
        String sql = "select * from Worker where Name = ? and Code = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Worker worker = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                worker = new Worker(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3)
                ,resultSet.getInt(4),resultSet.getDouble(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCtools.release(connection,statement,resultSet);
        }
        return worker;
    }

    @Override
    public List<Worker> getWorker() {
        String sql;
        Connection connection = null;
        List<Worker> list=null;
        try {
            connection = JDBCtools.getConnection();
            QueryRunner qR = new QueryRunner();
            sql = "select * from Worker";
            list = qR.query(connection, sql, new BeanListHandler<Worker>(Worker.class));
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
    public void AlterWorkerInfo(Worker a) {
        Connection connection=null;
        try {
            connection = JDBCtools.getConnection();
            String sql = "update Worker set DealTime= ? , Score= ? ,Code= ? where Wnum= ?";
            QueryRunner qR = new QueryRunner();
            qR.update(connection,sql,a.getDealTime(),a.getScore(),a.getCode(),a.getWnum());
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
    public List<Fix> MyFix(Worker worker) {
        String sql;
        Connection connection = null;
        List<Fix> list=null;
        try {
            connection = JDBCtools.getConnection();
            QueryRunner qR = new QueryRunner();
            sql = "select * from Fix where Wnum=? and Reply is null ";
            list = qR.query(connection, sql, new BeanListHandler<Fix>(Fix.class),worker.getWnum());
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
    public void DealFix(int Fnum, String reply) {
        Connection connection=null;
        try {
            connection = JDBCtools.getConnection();
            String sql = "update Fix set Reply= ? where Fnum= ?";
            QueryRunner qR = new QueryRunner();
            qR.update(connection,sql,reply,Fnum);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return;
    }
}
