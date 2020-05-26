package com.silly.repository.impl;

import com.silly.entity.Admin;
import com.silly.repository.AdminRepository;
import com.silly.utils.JDBCtools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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


}
