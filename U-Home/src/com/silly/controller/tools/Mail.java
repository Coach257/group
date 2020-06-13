package com.silly.controller.tools;

import com.silly.entity.Customer;
import com.silly.service.impl.AdminServiceImpl;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class Mail extends Thread {


    public static void sendMial() {
        List<Customer> list = new AdminServiceImpl().AllCustomer();
        for(Customer c : list){
            String msg = "尊敬的用户，您好：<br/>" +
                    "这里是优家，有你就有家青年租房管理系统，系统监测到您有订单本月需要支付租金，请您尽快点击下方链接进行支付<br/>" +
                    "<a href=\"http://39.101.200.9:8080/\">点击跳转</a><br/>" +
                    "感谢您的配合<br/>" +
                    "（系统邮件，不需要回复）<br/>";
            String sub = "优家，有你就有家";
            sendMail(c.getEmail(),msg,sub);
        }
    }

    /**
     * 示例：sendMail("shizhelun20000820@126.com","友情提醒：您的各种大作业要写不完了","U-home");
     * sendMail("1029247665@qq.com","皇家赌场邀请您","U-home");
     */
    private static boolean sendMail(String to, String emailMsg,String subject) {
        try {

            String emailUser = "1029247665@qq.com";
            String emailPwd = "";
            String emailHost = "smtp.qq.com";
            String emailAuth = "true";
            String emailProtocol = "smtp";
            int emailPort = 25;

            //获取系统环境信息
            Properties props = System.getProperties();
            //设置邮件服务器
            props.setProperty("mail.smtp.host", emailHost);
            //设置密码认证
            props.setProperty("mail.smtp.auth", emailAuth);
            //设置传输协议
            props.setProperty("mail.transport.protocol", emailProtocol);
            //创建session对象
            Session session = Session.getInstance(props);
            //设置输出日志
            session.setDebug(true);

            //邮件发送对象
            MimeMessage message = new MimeMessage(session);
            //设置发件人
            message.setFrom(new InternetAddress(emailUser));
            //设置邮件主题
            message.setSubject(subject);
            //设置邮件内容
            //message.setText("Welcome to JavaMail World!");
            //如果带网页内容使用Content发送
            message.setContent((emailMsg),"text/html;charset=utf-8");

            //获取邮件发送管道
            Transport transport=session.getTransport();
            //连接管道
            transport.connect(emailHost,emailPort, emailUser, emailPwd);
            //发送邮件
            transport.sendMessage(message,new Address[]{new InternetAddress(to)});
            //关闭管道
            transport.close();
            return true;
        }
        catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

}
