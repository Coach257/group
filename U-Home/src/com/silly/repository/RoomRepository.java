package com.silly.repository;

import com.silly.entity.Room;

import java.util.List;

public interface RoomRepository {
    public List<Room> FindRoom_C(int LivePeople);
    public List<Room> FindRoom_C();
    public List<Room> FindRoom_A();
    public Room getbyRnum(int Rnum);
}
