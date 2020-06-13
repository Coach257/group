package com.silly.controller.tools;

import com.silly.entity.Order;
import com.silly.entity.Room;
import com.silly.service.AdminService;
import com.silly.service.impl.AdminServiceImpl;

import java.util.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class Listener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("定时发送Xml信息监听--已关闭！");
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year, month, day, 1, 00, 00);
        // 当天8点（默认执行时间）
        Date defaultdate = calendar.getTime();
        Date sendDate = null;
        if (defaultdate.before(new Date())) {
            // 若当前时间超过了defaultdate时间，当天不再执行，则将执行时间sendDate改为明天8点
            calendar.add(Calendar.DATE, 1);
            sendDate = calendar.getTime();
        }else {
            // 若当前时间没有超过defaultdate时间，则将执行时间sendDate改为defaultdate
            sendDate = defaultdate;
        }
        Timer qTimer = new Timer();
        qTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                System.out.println("refresh");

                RefreshRoomEmpty.fresh();//刷新房间信息

                Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);

                System.out.println(day);
                if (day == 1) {//月初
                    refreshOrder();
                }
                day += 17;

                if (day == c.getActualMaximum(Calendar.DAY_OF_MONTH)) {//月末
                    System.out.println("月任务执行已执行");
                    Mail.sendMial();
                }
            }
        }, defaultdate, 15 * 1000);// 十秒钟检查一次


    }

    private static void refreshOrder(){
        AdminService adminService = new AdminServiceImpl();
        List<Order> orders = adminService.AllOrder();
        List<Room> rooms = adminService.AllRoom();

        System.out.println("更新长期订单");
        Date d = new Date();
        System.out.println(d);
        for(Order order : orders){
            if(order.isTime() && order.getMode() == 6){
                order.setMode(4);
                adminService.ChangeOrder(order);
            }
        }
    }
}
