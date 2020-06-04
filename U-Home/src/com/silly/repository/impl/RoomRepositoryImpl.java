package com.silly.repository.impl;

import com.silly.entity.Order;
import com.silly.entity.Room;
import com.silly.repository.RoomRepository;
import com.silly.utils.JDBCtools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RoomRepositoryImpl implements RoomRepository {
    @Override
    public List<Room> FindRoom_C(int LivePeople) {
        String sql;
        Connection connection = null;
        List<Room> list=null;
        try {
            connection = JDBCtools.getConnection();
            QueryRunner qR = new QueryRunner();
            sql = "select * from Room where Capacity = ? and EmptyOrNot < ?  and CanUse =true";
            list = qR.query(connection, sql, new BeanListHandler<Room>(Room.class), LivePeople,LivePeople);
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
    public List<Room> FindRoom_C() {
        String sql;
        Connection connection = null;
        List<Room> list=null;
        try {
            connection = JDBCtools.getConnection();
            QueryRunner qR = new QueryRunner();
            sql = "select * from Room as Tmp where CanUse =true and EmptyOrNot < " +
                    "( select Capacity from Room as H where H.Rnum = Tmp.Rnum )";
            list = qR.query(connection, sql, new BeanListHandler<Room>(Room.class));
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
    public List<Room> FindRoom_A() {
        String sql;
        Connection connection = null;
        List<Room> list=null;
        try {
            connection = JDBCtools.getConnection();
            QueryRunner qR = new QueryRunner();
            sql = "select * from Room";
            list = qR.query(connection, sql, new BeanListHandler<Room>(Room.class));
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
