package com.silly.controller.tools;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class SendMail implements ServletContextListener {

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

//        /**
//         * ----------------每刻任务 ----------------
//         * 启动服务器后，若此时时间没过8点，等待。到了8点自动执行一次，15分钟后再执行一次，周而复始
//         * 启动服务器后，若此时时间超过8点，会立刻执行一次，等到15分钟后再次执行一次，周而复始 到了第二天，不会再判断是否是8点，这个开始时间，只会判断一次而已
//         */
//        Timer qTimer = new Timer();
//        qTimer.schedule(new TimerTask() {
//
//            @Override
//            public void run() {
//                System.out.println("每刻任务已执行");
//                // TODO 写你的逻辑
//            }
//        }, defaultdate, 1 * 30 * 1000);// 定时每15分钟
//        System.out.println("每刻定时发送Xml信息监听--已启动！");
//
//        /**
//         * ----------------每日任务 ----------------
//         * 启动服务器后，若此时时间没过8点，等待。到了8点自动执行一次，24小时后（第二天8点）再执行一次，周而复始
//         * 启动服务器后，若此时时间已经超过8点，则等到24小时后（第二天8点）才执行一次，周而复始
//         */
//        Timer dTimer = new Timer();
//        dTimer.schedule(new TimerTask() {
//
//            @Override
//            public void run() {
//                System.out.println("每日任务已经执行");
//                // TODO 写你的逻辑
//            }
//        }, sendDate, 24 * 60 * 60 * 1000);// 定时24小时：24 * 60 * 60 * 1000
//        System.out.println("每日定时发送Xml信息监听--已启动！");

        /**
         * ----------------每月任务 ----------------
         * 启动服务器后，若此时时间没过8点，等待。到了8点自动执行判断是否是当前月份的1号，若是则执行一次，
         * 24小时后（第二天8点）再执行一次判断（每月1号以后后的29天或30天后才会是下月1号，再执行一次），周而复始 启动服务器后，若此时时间已经超过8点，会立刻执行一次，等到下个月1号再次执行一次，周而复始
         */
        Timer mTimer = new Timer();
        mTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                System.out.println("月任务 判断中");
                if (day == c.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                    // 每天执行，若为每月1号才执行
                    System.out.println("月任务执行已执行");
                    // TODO 写你的逻辑
                }

            }
        }, sendDate, 24 * 60 * 60 * 1000);// 每天执行一次检查


        System.out.println("每月定时发送Xml信息监听--已启动！");

    }

}