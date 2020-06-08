package com.silly.controller.tools;

import com.silly.entity.Order;
import com.silly.entity.Room;
import com.silly.service.AdminService;
import com.silly.service.impl.AdminServiceImpl;


import java.util.Date;
import java.util.List;

public class RefreshRoomEmpty {
    public static void fresh(){
        AdminService adminService = new AdminServiceImpl();
        List<Order> orders = adminService.AllOrder();
        List<Room> rooms = adminService.AllRoom();

        System.out.println("更新房源信息");
        Date d = new Date();
        System.out.println(d);

        for(Room room : rooms){
            int EmptyOrNot = 0;
            for(Order order : orders){
                if(order.getRnum() == room.getRnum()
                    && order.getBeginDate().compareTo(d) < 0
                    && order.getEndDate().compareTo(d) > 0){
                        EmptyOrNot++;
                }
            }
            room.setEmptyOrNot(EmptyOrNot);
        }
    }
}
