package com.silly.repository.impl;


import com.silly.entity.Worker;
import com.silly.repository.WorkerRepository;
import com.silly.utils.JDBCtools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                worker = new Worker(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCtools.release(connection,statement,resultSet);
        }
        return worker;
    }
}
