package com.silly.repository.impl;

import com.silly.repository.AllUseRepository;
import com.silly.utils.JDBCtools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
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



}
