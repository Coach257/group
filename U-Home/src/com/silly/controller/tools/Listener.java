package com.silly.controller.tools;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

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
        calendar.set(year, month, day, 8, 00, 00);
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

                RefreshRoomEmpty.fresh();//刷新房间信息

                Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);

                day += 17;

                if (day == c.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                    System.out.println("月任务执行已执行");
                    Mail.sendMial();
                }


            }
        }, defaultdate, 24 * 60 * 60 * 1000);// 每天执行一次检查


    }


}
